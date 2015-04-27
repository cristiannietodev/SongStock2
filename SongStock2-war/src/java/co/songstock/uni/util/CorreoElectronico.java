/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.songstock.uni.util;

import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author cnieto
 */
public class CorreoElectronico {

    public void enviar(String correo, String contrasena, String asunto, String contenido, String... destinatarios) throws AddressException, MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.user", correo);

        Session mailSession = Session.getDefaultInstance(prop);
        mailSession.setDebug(true);
        MimeMessage mensaje = new MimeMessage(mailSession);

        mensaje.setFrom(new InternetAddress(correo));

        for (String destinatario : destinatarios) {
            mensaje.addRecipient(RecipientType.TO, new InternetAddress(destinatario));
        }

        mensaje.setSubject(asunto, "UTF-8");
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(contenido, "UTF-8", "html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        mensaje.setContent(multipart);
        Transport transporte = mailSession.getTransport("smtp");
        transporte.connect(correo, contrasena);
        transporte.sendMessage(mensaje, mensaje.getAllRecipients());
        transporte.close();

    }

}
