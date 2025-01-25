package scm.SmartContactManager.services;

import scm.SmartContactManager.Entities.User;

import java.util.List;
import java.util.Optional;

public interface user_service_interface {

    User saveUser(User u);
    Optional<User> getUserbyId(String userid);
    Optional<User> updateUser(User u);
    void deleteUser(String userid);
    boolean isUserExist(String userid);
    boolean isUserExistByUsername(String email);
    List<User> getAllUser();
    User findByEmail(String email);


}
