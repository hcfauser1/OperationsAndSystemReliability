package gov.hhs.cms.pricer.v9109.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @RequestMapping("/health")
    public ResponseEntity<String> home(){

        return ResponseEntity.ok()
                .body("web service is active");
    }
}
