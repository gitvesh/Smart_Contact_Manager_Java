package scm.SmartContactManager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import scm.SmartContactManager.Entities.User;
import scm.SmartContactManager.Repository.user_repository;

@Controller
public class AuthController {

    @Autowired
    private user_repository userRepository;


    @GetMapping("api/v1/auth/verify-email")
    public String verifyEmail(@RequestParam("token") String token) {
        User user = userRepository.findByToken(token);

        if (user == null) {
            System.out.println("$$$$$$$$$$$$$$$$$>>>>>>>>>>>>>>>>>>>> Token not found in the database: " + token);
            return "error";
        }

        System.out.println("Token from request: " + token);
        System.out.println("Token from database: " + user.getToken());

        if (token.equals(user.getToken())) {
            System.out.println("Tokens match. Verifying email...");
            user.setEmailverified(true);
            user.setEnabled(true);
            userRepository.save(user);
            System.out.println("Email verified and user enabled successfully.");
            return "success";
        } else {
            System.out.println("((((************************************************************Tokens do not match!");
        }

        return "error";
    }
}
