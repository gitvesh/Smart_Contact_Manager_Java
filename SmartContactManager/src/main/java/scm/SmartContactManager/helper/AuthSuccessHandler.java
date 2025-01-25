package scm.SmartContactManager.helper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import scm.SmartContactManager.Entities.Provider;
import scm.SmartContactManager.Entities.User;
import scm.SmartContactManager.Repository.user_repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
@Autowired
user_repository ur;
    Logger logger= LoggerFactory.getLogger(AuthSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {



    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("AuthSuccessHandler");

        DefaultOAuth2User user=(DefaultOAuth2User) authentication.getPrincipal();
        logger.info(user.getName());
        user.getAttributes().forEach((key,value)->
        {
            logger.info("{} => {}",key,value);
        });

        logger.info(user.getAuthorities().toString());
        ///
        ///
        String name=user.getAttribute("name").toString();
        String email=user.getAttribute("email").toString();
        String photo=user.getAttribute("picture").toString();
        //=====================

        User user1 =new User();
        user1.setUsername(name);
        user1.setEmail(email);
        user1.setProfile(photo);
        user1.setUserid(UUID.randomUUID().toString());
        user1.setProvider(Provider.GOOGLE);
        user1.setEmailverified(true);
        user1.setRolelist(List.of("Role_USER"));

        Optional<User> user2=ur.findByEmail(email);
        System.out.println("start to save------------------------------");
        if(user2.isEmpty()) {
            System.out.println("saving............");
            ur.save(user1);
            logger.info("saved");
        }

        System.out.println("End--------------------------------");
        ///
        ///
        DefaultRedirectStrategy defaultRedirectStrategy=new DefaultRedirectStrategy();
        defaultRedirectStrategy.sendRedirect(request,response,"/user/Dashboard");

    }


}
