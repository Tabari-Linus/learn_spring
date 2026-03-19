package com.mrlii.authwithpermissions.notification.emailservice.impl;

import com.mrlii.authwithpermissions.configuration.propertiesconfig.EmailPropertiesConfig;
import com.mrlii.authwithpermissions.notification.emailservice.EmailDetails;
import com.mrlii.authwithpermissions.notification.emailservice.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;


import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final EmailPropertiesConfig emailPropertiesConfig;

    @Override
    public void sendEmailNotification(EmailDetails emailDetails) {
        sendEmail(emailDetails);
    }

    private Context getContext(EmailDetails emailDetails) {
        Map<String, Object> properties = new HashMap<>();

        properties.put("recipient", emailDetails.recipient());

    }

    private void sendEmail(EmailDetails emailDetails) {
        try {
            String templateName = emailDetails.template().name().toLowerCase();

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MULTIPART_MODE_MIXED,
                    UTF_8.name()
            );
            Context context = getContext(emailDetails);

            helper.setFrom(emailPropertiesConfig.from());
            helper.setTo(emailDetails.email());
            helper.setSubject(emailDetails.template().getSubject());

            String template = templateEngine.process(templateName, context);
            helper.setText(template, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailSendException("Failed to send email", e);
        }
    }
}
