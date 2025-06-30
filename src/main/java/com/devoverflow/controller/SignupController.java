package com.devoverflow.controller;

import com.devoverflow.dto.SignupRequestDto;
import com.devoverflow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }
}
