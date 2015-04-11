package pt.iscte.sida.registo.database;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Admin on 13-03-2015.
 */
public class ServicoEmail {

    public ServicoEmail(){

    }


    public void sendMessage(String origem, String destino, String subject, String body){
        final String username = "dapcn@iscte-iul.pt";
        final String password = "DCiscte31.";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(origem));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);



        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
