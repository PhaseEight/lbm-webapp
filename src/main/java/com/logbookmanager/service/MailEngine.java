package com.logbookmanager.service;

import java.io.Serializable;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Class for sending e-mail messages based on Velocity templates or with
 * attachments.
 * 
 * <p>
 * <a href="MailEngine.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author Peter Neil
 */
public class MailEngine {
	protected static final Log log = LogFactory.getLog(MailEngine.class);

	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Send a simple mailMessage based on a Velocity template.
	 * 
	 * @param msg
	 * @param templateName
	 * @param model
	 */
	public void sendMessage(SimpleMailMessage msg, String templateName,
			Map<String, Serializable> model) {
		String result = null;
		msg.setText(result);
		send(msg);
	}

	/**
	 * Send a simple mailMessage with pre-populated values.
	 * 
	 * @param msg
	 */
	public void send(SimpleMailMessage msg) {
		try {
			mailSender.send(msg);
		} catch (MailException ex) {
			// log it and go on
			log.error(ex.getMessage());
		}
	}

	/**
	 * Convenience method for sending messages with attachments.
	 * 
	 * @param emailAddresses
	 * @param resource
	 * @param bodyText
	 * @param subject
	 * @param attachmentName
	 * @throws MessagingException
	 * @author Ben Gill
	 */
	public void sendMessage(String[] emailAddresses,
			ClassPathResource resource, String bodyText, String subject,
			String attachmentName) throws MessagingException {
		MimeMessage message = ((JavaMailSenderImpl) mailSender)
				.createMimeMessage();

		// use the true flag to indicate you need a multipart mailMessage
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(emailAddresses);
		helper.setText(bodyText);
		helper.setSubject(subject);

		helper.addAttachment(attachmentName, resource);

		((JavaMailSenderImpl) mailSender).send(message);
	}
}
