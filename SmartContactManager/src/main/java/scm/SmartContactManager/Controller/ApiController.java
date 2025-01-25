package scm.SmartContactManager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scm.SmartContactManager.Entities.Contact;
import scm.SmartContactManager.services.ContactService;

@RestController
@RequestMapping("api")
public class ApiController {
  @Autowired
    ContactService service;
  @GetMapping("/contact/{cid}")
  public Contact getContact(@PathVariable String cid)
  {
      return service.getbyid(cid);
  }

}
