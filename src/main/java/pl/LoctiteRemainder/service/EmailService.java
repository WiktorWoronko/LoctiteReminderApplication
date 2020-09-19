package pl.LoctiteRemainder.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.LoctiteRemainder.model.Email;
import pl.LoctiteRemainder.repository.EmailRepository;
import java.util.List;

@Service
public class EmailService {
    private final JavaMailSender emailSender;

    private EmailRepository emailRepository;


    public EmailService(JavaMailSender emailSender, EmailRepository emailRepository) {
        this.emailSender = emailSender;
        this.emailRepository = emailRepository;

    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public Email saveEmail(Email email) {
        return emailRepository.save(email);
    }

    private List<Email> getEmailFromDataBase() {
        return emailRepository.findAll();
    }

    public String chooseYourEmail() {
        List<Email> emails = getEmailFromDataBase();
        String yourEmail = emails.get(0).getEmail();
        updateEmailsFromDataBase();

        return yourEmail;
    }

    private void updateEmailsFromDataBase() {
        emailRepository.deleteAll();
    }

}
