package com.ubagroup.switchemv.api;

import com.ubagroup.switchemv.constant.SystemMessage;
import com.ubagroup.switchemv.dto.RegisterRequest;
import com.ubagroup.switchemv.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final AppUserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        userService.register(req);
        return ResponseEntity.ok(SystemMessage.SUCCESSFUL_REGISTRATION);
    }
}
