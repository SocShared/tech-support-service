package ml.socshared.service.support.domain.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageRequest {
    private String text;
    private String subject;
    private String fromEmail;
    private List<String> toEmails;
}
