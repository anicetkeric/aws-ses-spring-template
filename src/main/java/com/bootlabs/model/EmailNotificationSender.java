package com.bootlabs.model;

import com.bootlabs.enums.ProcessType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.File;
import java.util.List;
import java.util.Map;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationSender {

    @NotNull
    @NotEmpty
    private List<String> mailTo;

    private List<String> mailCc;

    private List<String> mailBcc;

    @NotNull
    private ProcessType processType = ProcessType.NEWSLETTER;

    @NotNull
    private String from;

    private File[] attachments;

    @NotNull
    private Map<String, String> templateData;
}
