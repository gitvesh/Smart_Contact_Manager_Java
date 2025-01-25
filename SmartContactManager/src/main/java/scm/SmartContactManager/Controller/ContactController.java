//remaining : http session


package scm.SmartContactManager.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import scm.SmartContactManager.Entities.*;
import scm.SmartContactManager.helper.helper;
import scm.SmartContactManager.services.ContactService;
import scm.SmartContactManager.services.Image_service;
import scm.SmartContactManager.services.user_services;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("user/contact")
public class ContactController {
    @Autowired
    ContactService contactService;
    @Autowired
    Image_service imageService;
    @Autowired
    user_services userServices;
    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);



    @RequestMapping("/add")
    public String addContact(Model model)
    {
       model.addAttribute("contactForm",new ContactForm());
        return "user/addContact";
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String addContactForm(@Valid  @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication, HttpSession httpSession) throws IOException {

        String filename= UUID.randomUUID().toString();


        if(result.hasErrors())
        {
            return "user/addContact";
        }
        String username= helper.getEmailOfLoggedinUser(authentication);

        //contact picture upload


        User user=userServices.findByEmail(username);

        Contact contact=new Contact();
        contact.setName(contactForm.getName());

        contact.setPhonenumber(contactForm.getPhone());

        contact.setWeblink(contactForm.getWeblink());
        System.out.println("**************************************************************************************************************************************************************************************************"+contactForm.getisFav());
        contact.setFavourite(contactForm.getisFav());

        contact.setEmail(contactForm.getEmail());
        contact.setU(user);
        contact.setLinkedinLink(contactForm.getLinkedinLink());
        contact.setDiscription(contactForm.getDescription());
        contact.setCloudinaryImagePublicId(filename);


httpSession.setAttribute("message",null);
        //set contact
        String imageurl=imageService.uploadImage(contactForm.getProfile(),filename);
        contact.setPicture(imageurl);

        contactService.Save(contact);

        return "redirect:/user/contact/add";
    }

    @RequestMapping("/myContacts")
    public String contacts(

            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value="psize",defaultValue = "4") int psize,
            @RequestParam(value="sortby",defaultValue = "name") String sortby,
            @RequestParam(value="direction",defaultValue = "asc") String direction,
            Model model,Authentication authentication)
    {
        String username=helper.getEmailOfLoggedinUser(authentication);

        User user=userServices.findByEmail(username);

        Page<Contact> contacts=contactService.getByUser(user,page,psize,sortby,direction);


        model.addAttribute("contactSearchForm",new ContactSearchForm());

        model.addAttribute("contacts",contacts);
        model.addAttribute("psize",psize);

        return "/user/contacts";
    }
    @RequestMapping("myContacts/search")
    public String searchHandler(

            @ModelAttribute ContactSearchForm contactSearchForm,
            @RequestParam(value = "size", defaultValue = "4") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model,
            Authentication authentication) {

       // logger.info("field {} keyword {}", contactSearchForm.getField(), contactSearchForm.getValue());

        if (contactSearchForm.getField() == null) {
            contactSearchForm.setField("");
        }
        if (contactSearchForm.getValue() == null) {
            contactSearchForm.setValue("");
        }

        var user = userServices.findByEmail(helper.getEmailOfLoggedinUser(authentication));

        Page<Contact> pageContact = null;
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pageContact = contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
            pageContact = contactService.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("phone")) {
            pageContact = contactService.searchByPhoneNumber(contactSearchForm.getValue(), size, page, sortBy,
                    direction, user);
        }

      //  logger.info("pageContact {}", pageContact);

        model.addAttribute("contactSearchForm",contactSearchForm);

        model.addAttribute("pageContact", pageContact);

        model.addAttribute("pageSize", size);

        return "user/search";
    }

    @RequestMapping("/profile/{cid}")
    public String profile(@PathVariable String cid, Model model)
    {
        Contact contact=contactService.getbyid(cid);
        model.addAttribute("contact",contact);
        return "user/UserProfile";
    }

    @RequestMapping(value = "/view/{contactId}", method = RequestMethod.POST)
    public String updateContact(
            @PathVariable("contactId") String contactId,
            @Valid @ModelAttribute ContactForm contactForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // Add error feedback for the user
            model.addAttribute("errorMessage", "Please correct the errors in the form.");
            return "user/update_contact_view";
        }

        Contact con = new Contact();
        con.setId(contactId);
        con.setName(contactForm.getName());
        con.setEmail(contactForm.getEmail());
        con.setPhonenumber(contactForm.getPhone());
        con.setAddress(contactForm.getAddress());
        con.setDiscription(contactForm.getDescription());
        con.setFavourite(contactForm.getisFav());
        con.setWeblink(contactForm.getWeblink());
        con.setLinkedinLink(contactForm.getLinkedinLink());

        // Process image upload
//        try {
//            if (contactForm.getProfile() != null && !contactForm.getProfile().isEmpty()) {
//                String fileName = UUID.randomUUID().toString();
//                String imageUrl = imageService.uploadImage(contactForm.getProfile(), fileName);
//                con.setCloudinaryImagePublicId(fileName);
//                con.setPicture(imageUrl);
//            }
//        } catch (IOException e) {
//            logger.error("Image upload failed for contactId: {}", contactId, e);
//            model.addAttribute("errorMessage", "Failed to upload profile image. Please try again.");
//            return "user/update_contact_view";
//        }

        // Update contact
        Contact updatedContact = contactService.update(con);
        model.addAttribute("contact", updatedContact);

        logger.info("Updated contact: {}", updatedContact);

        return "redirect:/user/contact/profile/" + contactId;
    }


    @RequestMapping("/delete/{contactId}")
    public String deleteContact(
            @PathVariable("contactId") String contactId,
            HttpSession session) {
        contactService.delete(contactId);
        logger.info("contactId {} deleted", contactId);

//        session.setAttribute("message",
//                message.builder()
//                        .content("Contact is Deleted successfully !! ")
//                        .type(MessageType.green)
//                        .build()

//        );

        return "redirect:/user/contact/myContacts";
    }


    @RequestMapping("/view/{contactId}")
    public String updateView(@PathVariable String contactId, Model model)
    {
        ContactForm cf=new ContactForm();
        Contact c=contactService.getbyid(contactId);
        cf.setCid(c.getId());
        cf.setName(c.getName());
        cf.setEmail(c.getEmail());
       // cf.setPicture(c.getPicture());
        cf.setFav(c.isFavourite());
        cf.setAddress(c.getAddress());
        cf.setLinkedinLink(c.getLinkedinLink());
        cf.setWeblink(c.getWeblink());
        cf.setDescription(c.getDiscription());
        cf.setPhone(c.getPhonenumber());
        model.addAttribute("contact",(cf));
        return "user/updateForm";
    }

}
