package scm.SmartContactManager.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import scm.SmartContactManager.Entities.User;
import scm.SmartContactManager.Repository.user_repository;
import scm.SmartContactManager.helper.ResourceNotFoundException;
import scm.SmartContactManager.helper.helper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class user_services implements user_service_interface {
    @Autowired
   private user_repository userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
            EmailService emailService;

   Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public User saveUser(User u) {
String userid=UUID.randomUUID().toString();
        u.setUserid(userid);
        u.setPassword(passwordEncoder.encode(u.getPassword()));
u.setRolelist(List.of("Role_User"));

String token=UUID.randomUUID().toString();
String link= helper.getEmailVerificationLink(token);

emailService.sendEmail(u.getEmail(),"Email Verification Link :  Bhavesh Jadhav's Project",link);
u.setToken(token);
        logger.info(u.getProvider().toString());
        return userRepo.save(u);
    }

    @Override
    public Optional<User> getUserbyId(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User u) {
//()-> new ResourceNotFoundException("")
         User user2=userRepo.findById(u.getUserid()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
         user2.setUsername(u.getname());
         user2.setEmail(u.getEmail());
         user2.setPassword(u.getPassword());
         user2.setAbout(u.getAbout());
         user2.setPhone(u.getPhone());
         user2.setProfile(u.getProfile());
         user2.setEnabled(u.isEnabled());
         user2.setEmailverified(u.isEmailverified());
         user2.setPhoneverified(u.isPhoneverified());
         user2.setProvider(u.getProvider());
         user2.setProviderId(u.getProviderId());
         User save=userRepo.save(user2);
         return Optional.ofNullable(save);
    }




    @Override
    public void deleteUser(String id) {
User user1=userRepo.findById(id).orElseThrow();
userRepo.delete(user1);

    }

    @Override
    public boolean isUserExist(String id) {
        User u=userRepo.findById(id).orElseThrow(null);
        return u !=null ? true : false;
    }

    @Override
    public boolean isUserExistByUsername(String email) {
        User u=userRepo.findByEmail(email).orElseThrow(null);
        return u !=null ? true : false;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("user not found"));
    }



}
