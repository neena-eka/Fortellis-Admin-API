package com.cdk.dc.hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/admin")
    public ResponseEntity home() {
        String greeting = "Hi";
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
