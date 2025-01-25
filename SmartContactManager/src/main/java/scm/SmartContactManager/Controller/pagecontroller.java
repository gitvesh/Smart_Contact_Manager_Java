package scm.SmartContactManager.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import scm.SmartContactManager.Entities.*;
//import scm.SmartContactManager.services.user_services;
import scm.SmartContactManager.services.user_services;

import java.util.UUID;


@Controller
public class pagecontroller {
@Autowired
    private user_services us;

    @RequestMapping("base")
    public String getBase(Model model)
    {
        model.addAttribute("name","bhavesh");
        return "base";
    }

    @GetMapping("register")
    public String signup(Model model)
    {model.addAttribute("form_obj",new UserDetails());
        return "register";
    }
    @GetMapping(value = "login")
    public String loginForm(Model model)
    {model.addAttribute("login_obj",new UserDetails());
        return "login";
    }


@RequestMapping( value = "/register-details",method = RequestMethod.POST)
public String register(@Valid @ModelAttribute UserDetails userDetails, HttpSession session, BindingResult bindingResult)
{
    if(bindingResult.hasErrors())
    {
        return "/register";
    }
    User u=new User();



    u.setUsername(userDetails.getName());
    u.setEmail(userDetails.getEmail());
    u.setPassword(userDetails.getPassword());
    u.setAbout(userDetails.getAbout());
    u.setPhone(userDetails.getPhone_number());
    u.setEmailverified(false);
    u.setEnabled(false);

    u.setProfile("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHzTnWhKVoSLwhx3nheJu8mE1W5W3mR3xynQ&usqp=CAU");
    User savedUser=us.saveUser(u);
    message msg=new message();
    msg.setContent("Registration Succesfull !");
    //=========================================================
    msg.setType(messagetype.red);
    session.setAttribute("message",msg);
    return "redirect:/register";
}

}
