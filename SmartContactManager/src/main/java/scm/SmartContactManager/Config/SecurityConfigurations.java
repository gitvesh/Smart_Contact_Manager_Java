package scm.SmartContactManager.Config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import scm.SmartContactManager.helper.AuthSuccessHandler;

@Configuration
public class SecurityConfigurations {
    @Autowired
private SecurityCustomServiceDetail securityCustomServiceDetail;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

@Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(securityCustomServiceDetail);

        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       //configurations
    httpSecurity.authorizeHttpRequests(authorize->{
//        authorize.requestMatchers("/register").permitAll();
        authorize.requestMatchers("/user/**").authenticated();
        authorize.anyRequest().permitAll();


        try {
            httpSecurity.formLogin(formLogin->
            {
                formLogin.loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .successForwardUrl("/user/profile");

               // formLogin.failureForwardUrl("/login?error=true")
               formLogin .passwordParameter("password")
                .usernameParameter("email");


              // formLogin.failureHandler(new AuthFailure());
            });
            //logout working
            httpSecurity.csrf(AbstractHttpConfigurer::disable);
            httpSecurity.logout(logout->
            {
                logout.logoutUrl("/logout");
                logout.logoutSuccessUrl("/login?logout=true");
            });

            //google------->>>>>>>>>>>>>>
            httpSecurity.oauth2Login(auth->
            {
                auth.loginPage("/login");
                auth.successHandler(authSuccessHandler);

            });




        } catch (Exception e) {
            System.out.println("exception occurred"+e);
        }

    });
    //logout--------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>


    return httpSecurity.build();
    }

@Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();    }
}
