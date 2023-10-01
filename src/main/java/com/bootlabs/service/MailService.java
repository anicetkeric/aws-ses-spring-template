package com.bootlabs.service;

import com.bootlabs.exception.BadRequestException;
import com.bootlabs.model.EmailNotificationSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.SendEmailRequest;

import java.util.Map;

/**
 * Service for sending emails.
 * <p>
 * We use the {@link Async} annotation to send emails asynchronously.
 *
 * @author bootlabs
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class MailService {

   private final SesV2Client sesV2Client;

   @Async
    public void sendEmailTemplate(EmailNotificationSender notificationSender) {

       var templateData =  modelDataSerializer(notificationSender.getTemplateData());

       SendEmailRequest emailRequest = SendEmailRequest.builder()
               .destination(d -> d.toAddresses(notificationSender.getMailTo()).bccAddresses(notificationSender.getMailBcc()).ccAddresses(notificationSender.getMailCc()))
               .content(c -> c.template(t -> t.templateName(notificationSender.getProcessType().getTemplateName()).templateData(templateData)))
               .fromEmailAddress(notificationSender.getFrom())
               .build();

      var response = sesV2Client.sendEmail(emailRequest);
      log.info("email based on a template was sent. Response {}", response.toString());
    }

    /**
     * @param templateData Map template data
     * @return String json template data
     */
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
