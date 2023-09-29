package com.bootlabs.controller;

import com.bootlabs.model.EmailNotificationSender;
import com.bootlabs.service.SenderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@Validated
@RestController
public class SenderController {
    private final SenderService senderService;

    @PostMapping("/email")
    public ResponseEntity<String> sendEmailNotification(@RequestBody @Valid EmailNotificationSender emailNotificationSender) {

        senderService.sendEmail(emailNotificationSender);

        return new ResponseEntity<>("email sent", HttpStatus.OK);
    }

}
