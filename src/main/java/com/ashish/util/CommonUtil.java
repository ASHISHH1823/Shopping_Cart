package com.ashish.util;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ashish.entity.productOrder;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class CommonUtil {
	@Autowired
	private JavaMailSender mailSender;

	public Boolean sendemail(String url, String recipientemail)
			throws UnsupportedEncodingException, MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper Helper = new MimeMessageHelper(mimeMessage);

		Helper.setFrom("munamohakud1234@gmail.com", "Shooping cart");
		Helper.setTo(recipientemail);
		
		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + url
				+ "\">Change my password</a></p>";
		Helper.setSubject("Password Reset");
		Helper.setText(content, true);
		mailSender.send(mimeMessage);
		return true;

	}

	public String generateUrl(HttpServletRequest request) {
		return ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
	}
	
	public Boolean sendMailforproductorder(productOrder order,String status) throws UnsupportedEncodingException, MessagingException {
		 String messageContent = "<p>Hello " + order.getOrderAdress().getFirstname() + ",</p>"
	                + "<p>Thank you for your order. Your order status is: <b>" + status + "</b>.</p>"
	                + "<p><b>Product Details:</b></p>"
	                + "<p>Name: " + order.getProduct().getTitle() + "</p>"
	                + "<p>Category: " + order.getProduct().getCategory() + "</p>"
	                + "<p>Quantity: " + order.getQuantity() + "</p>"
	                + "<p>Price: " + order.getPrice() + "</p>"
	                + "<p>Payment Type: " + order.getPaymentType() + "</p>";
		 MimeMessage mimeMessage = mailSender.createMimeMessage();
		 MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
		 helper.setFrom("munamohakud1234@gmail.com", "Shooping cart");
		 helper.setTo(order.getOrderAdress().getEmail());
		 helper.setSubject("Product OrderStatus");
		 helper.setText(messageContent,true);
		 mailSender.send(mimeMessage);
		return true;
	}

	
}
