package org.legenkiy.lesson40.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping()
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok(
                Map.of(
                        "message", "ok"
                )
        );
    }

}
