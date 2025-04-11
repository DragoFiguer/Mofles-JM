/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a.sistemataller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

public class Recupera {

    // Configuración de base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/taller";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root123";

    // Configuración del correo
    private static final String EMAIL_SENDER = "jrr0167@gmail.com";
    private static final String EMAIL_PASSWORD = "gmrs wshy hheq jszr";

    public static void recoverPassword(String userEmail) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Consulta para obtener la contraseña
            String query = "SELECT password FROM usuarios WHERE correo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Recupera la contraseña y envía el correo
                String userPassword = resultSet.getString("password");
                sendEmail(userEmail, userPassword);
                System.out.println("Correo enviado exitosamente.");
            } else {
                System.out.println("El correo proporcionado no está registrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendEmail(String recipientEmail, String password) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_SENDER, EMAIL_PASSWORD);
            }
        });

        try {
            // Configura y envía el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_SENDER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Recuperación de Contraseña");
            message.setText("Tu contraseña es: " + password);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}