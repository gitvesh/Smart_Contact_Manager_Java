package scm.SmartContactManager.services;

public interface email_service {

    public void sendEmail(String to,String subject,String body);
    public void sendEmailwithHtml();
    public void sendEmailwithAttachment();

}
