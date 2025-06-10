package com.ac.dha.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mock-dha")
public class MockDhaWebhookController {

    @PostMapping(value = "/uploadERxRequest", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> handleERxRequest(@RequestBody String body) {
        int randomReference = (int) (100000 + Math.random() * 900000); // 6-digit number

        String mockResponse = String.format("""
            <?xml version="1.0" encoding="UTF-8"?>
            <UploadERxRequestResponse>
                <ERxReferenceNo>%d</ERxReferenceNo>
                <ErrorMessage>Success</ErrorMessage>
                <ErrorReport></ErrorReport>
            </UploadERxRequestResponse>
            """, randomReference);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(mockResponse);
    }
}
