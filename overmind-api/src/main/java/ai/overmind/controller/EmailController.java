package ai.overmind.controller;

import ai.overmind.dto.EmailDTO;
import ai.overmind.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(path = "/send")
    public boolean sendEmail(@RequestBody EmailDTO emailDTO) {
        return emailService.sendEmail(emailDTO);
    }
}
