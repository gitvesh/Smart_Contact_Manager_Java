package scm.SmartContactManager.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import scm.SmartContactManager.Entities.User;

public class helper {
    static Logger logger= LoggerFactory.getLogger(helper.class);
    public static String getEmailOfLoggedinUser(Authentication authentication)
    { String username="";
      //  AuthenticatedPrincipal authenticatedPrincipal=(AuthenticatedPrincipal) authentication.getPrincipal();
        if(authentication instanceof OAuth2AuthenticationToken)
        {

            var OAuth2AuthenticationToken=( OAuth2AuthenticationToken) authentication;
            var cliendID=OAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var auth2user=(OAuth2User) authentication.getPrincipal();


            if(cliendID.equalsIgnoreCase("google"))
            {
                username=auth2user.getAttribute("email").toString();

                logger.info("username is "+username);
                return username;


            }
            return "";

        }
        else
        {logger.info("logged username - "+authentication.getName());
            return authentication.getName();
        }
    }
    public static  String getEmailVerificationLink(String token)
    {
        System.out.println("==============================>>> "+token);
        String link="http://localhost:8080/api/v1/auth/verify-email?token="+token;
   return link;
    }
}
