package scm.SmartContactManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import scm.SmartContactManager.Entities.Contact;
import scm.SmartContactManager.Entities.User;
import scm.SmartContactManager.Repository.ContactRrpository;
import scm.SmartContactManager.helper.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;
@Service
public class ContactService implements Contact_Service_Interface{

    @Autowired
    ContactRrpository contactRrpository;
    @Override
    public Contact Save(Contact contact) {
        String contactid= UUID.randomUUID().toString();
        contact.setId(contactid);
        return contactRrpository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        var contactOld = contactRrpository.findById(contact.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
        contactOld.setName(contact.getName());
        contactOld.setEmail(contact.getEmail());
        contactOld.setPhonenumber(contact.getPhonenumber());
        contactOld.setAddress(contact.getAddress());
        contactOld.setDiscription(contact.getDiscription());
       // contactOld.setPicture(contact.getPicture());
        contactOld.setFavourite(contact.isFavourite());
        contactOld.setWeblink(contact.getWeblink());
        contactOld.setLinkedinLink(contact.getLinkedinLink());
      //  contactOld.setCloudinaryImagePublicId(contact.getCloudinaryImagePublicId());

        return contactRrpository.save(contactOld);
    }

    @Override
    public List<Contact> getAll() {
        return contactRrpository.findAll();
    }

    @Override
    public Contact getbyid(String id) {
        return contactRrpository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact Not Found"+id));
    }

    @Override
    public void delete(String id) {
        contactRrpository.deleteById(id);

    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRrpository.findbyUserid(userId);

    }

    @Override
    public Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return contactRrpository.findByUser(user, pageable);

    }

    @Override
    public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user) {

        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRrpository.findByUserAndNameContaining(user, nameKeyword, pageable);
    }

    @Override
    public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order,
                                       User user) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRrpository.findByUserAndEmailContaining(user, emailKeyword, pageable);
    }

    @Override
    public Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy,
                                             String order, User user) {

        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRrpository.findByUserAndEmailContaining(user, phoneNumberKeyword, pageable);
    }

}
