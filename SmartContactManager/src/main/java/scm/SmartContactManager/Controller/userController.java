package scm.SmartContactManager.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import scm.SmartContactManager.Entities.User;
import scm.SmartContactManager.helper.helper;
import scm.SmartContactManager.services.user_services;

import java.security.Principal;

@Controller
@RequestMapping(value = "user")
public class userController {

    @Autowired
    user_services userServices;


    @RequestMapping(value = "Dashboard")
    public String userDashboard(Authentication authentication,Model model)
    {
        String email=helper.getEmailOfLoggedinUser(authentication);

        User user=userServices.findByEmail(email);

        model.addAttribute("user",user);

        return "user/dashboard";
    }
Logger logger=LoggerFactory.getLogger(userController.class);
    @RequestMapping(value = "profile")
    public String userProfile (Authentication authentication,Model model)
    {

        return "user/profile";
    }
}
