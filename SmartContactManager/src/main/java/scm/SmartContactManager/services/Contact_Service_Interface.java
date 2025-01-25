package scm.SmartContactManager.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import scm.SmartContactManager.Entities.Contact;
import scm.SmartContactManager.Entities.User;

import java.util.List;

public interface Contact_Service_Interface {
    Contact Save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    Contact getbyid(String id);
    void delete(String id);

  //  List<Contact> getbyuserid(String id);



    // search contact
    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order,
                                      User user);

    // get contacts by userId
    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection);


}
