package br.com.cursomc.services;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void senderOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendHtmlEmail(MimeMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
