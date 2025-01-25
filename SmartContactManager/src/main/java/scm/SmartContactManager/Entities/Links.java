package scm.SmartContactManager.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "WebLinks")
public class Links {
    @Id
    private long id;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getLink() {
//        return link;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public String getLinkName() {
//        return linkName;
//    }
//
//    public void setLinkName(String linkName) {
//        this.linkName = linkName;
//    }
//
//    public Contact getContacts() {
//        return contacts;
//    }
//
//    public void setContacts(Contact contacts) {
//        this.contacts = contacts;
//    }

    private String link;
    private String linkName;

   @ManyToOne
    private Contact contacts;
}
