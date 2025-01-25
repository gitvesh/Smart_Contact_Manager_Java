package scm.SmartContactManager.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import scm.SmartContactManager.Entities.User;
import scm.SmartContactManager.Repository.user_repository;
import scm.SmartContactManager.helper.helper;
import scm.SmartContactManager.services.user_services;
@ControllerAdvice
public class RootController {
    @Autowired
    private user_services us;
    @Autowired
    user_repository userrepo;
    Logger logger= LoggerFactory.getLogger(RootController.class);

    @ModelAttribute
    public  void AddLoggedInUser(Authentication authentication, Model model)
    {
        if(authentication==null) {
        return;
    }
        String username= helper.getEmailOfLoggedinUser(authentication);
        logger.info("username is>>>>>>>>>>>>>>>> "+username);
        User user=us.findByEmail(username);
      //  System.out.println(user.getEmail());

          //  model.addAttribute("loggedUser",null);



            System.out.println(username);
          //  logger.info("!!!111111!---------------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + username);
            model.addAttribute("loggedUser", user);
            model.addAttribute("name",user.getname());

    }
}
