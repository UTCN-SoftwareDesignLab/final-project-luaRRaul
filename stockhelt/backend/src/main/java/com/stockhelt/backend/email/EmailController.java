package com.stockhelt.backend.email;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.stockhelt.backend.UrlMapping.API_PATH;
import static com.stockhelt.backend.UrlMapping.ENTITY;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(API_PATH)
@RequiredArgsConstructor
public class EmailController {
   // remove this email controller and use the service where it is needed

   private final EmailService emailService;

   @RequestMapping(value = "/sendemail"+ENTITY, method = RequestMethod.GET)
   public void sendEmail(@PathVariable Long id) throws MessagingException, IOException, javax.mail.MessagingException {
      emailService.sendmail(id);
   }

}
