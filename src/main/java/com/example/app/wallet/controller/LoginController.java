package com.example.app.wallet.controller;

import com.example.app.wallet.dto.LoginDTO;
import com.example.app.wallet.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private LoginService loginService;


    public LoginController(BCryptPasswordEncoder bCryptPasswordEncoder, LoginService loginService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.loginService = loginService;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody LoginDTO loginDTO) {
        loginDTO.setPassword(bCryptPasswordEncoder.encode(loginDTO.getPassword()));
        loginService.save(loginDTO);
    }

}
