package com.bootlabs.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.bootlabs.exception.BadRequestException;
import com.bootlabs.model.EmailNotificationSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service for sending emails.
 * <p>
 * We use the {@link Async} annotation to send emails asynchronously.
 * @author ekouame
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class MailService {

   private final AmazonSimpleEmailService amazonSimpleEmailService;

   @Async
    public void sendEmailTemplate(EmailNotificationSender notificationSender) {

       var templateData =  modelDataSerializer(notificationSender.getTemplateData());

        var destination = new Destination();
        destination.setToAddresses(notificationSender.getMailTo());
        destination.setBccAddresses(notificationSender.getMailBcc());
        destination.setCcAddresses(notificationSender.getMailCc());

        var templatedEmailRequest = new SendTemplatedEmailRequest();
        templatedEmailRequest.withDestination(destination);
        templatedEmailRequest.withTemplate(notificationSender.getProcessType().getTemplateName());
        templatedEmailRequest.withTemplateData(templateData);
        templatedEmailRequest.withSource(notificationSender.getFrom());
        amazonSimpleEmailService.sendTemplatedEmail(templatedEmailRequest);
    }

    private String modelDataSerializer(Map<String, String> templateData) {
        ObjectMapper mapperObj = new ObjectMapper();
        try {
          return mapperObj.writeValueAsString(templateData);
        } catch (JsonProcessingException e) {
            log.error("Error parsing templateData");
            throw new BadRequestException("Template Date error");
        }
    }
}
