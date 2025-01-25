package scm.SmartContactManager.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import scm.SmartContactManager.Entities.Contact;
import scm.SmartContactManager.Entities.User;

import java.util.List;

@Repository
public interface ContactRrpository extends JpaRepository<Contact,String> {
    //find contacts by user
    Page<Contact> findByUser(User u, Pageable pageable);

    @Query("select c from Contact c where c.user.userid= :userid")
    List<Contact> findbyUserid(@Param("userid") String userid);

    Page<Contact> findByUserAndNameContaining(User user, String namekeyword, Pageable pageable);

    Page<Contact> findByUserAndEmailContaining(User user, String emailkeyword, Pageable pageable);

    Page<Contact> findByUserAndPhonenumberContaining(User user, String phonekeyword, Pageable pageable);



}
