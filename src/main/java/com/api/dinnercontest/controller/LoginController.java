package com.api.dinnercontest.controller;

import com.api.dinnercontest.model.LoginModel;
import com.api.dinnercontest.model.TokenModel;
import com.api.dinnercontest.model.UserTokenModel;
import com.api.dinnercontest.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity login(HttpServletRequest request, @RequestBody LoginModel loginModel) {
        //log.info("[REQUEST RECEIVED    -    POST    /login    {}]", loginModel.getName());
        if (loginService.accessSuccesful(loginModel)) {
            TokenModel tokenModel = new TokenModel();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri());
            tokenModel.setToken(loginService.setToken(loginModel.getAccessName()));
            return new ResponseEntity<>(tokenModel, httpHeaders, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/check-token")
    public ResponseEntity checkToken(HttpServletRequest request, @RequestBody UserTokenModel userTokenModel) {
        //log.info("[REQUEST RECEIVED    -    POST    /user    {}]", userModel.getName());
        if (loginService.checkToken(userTokenModel)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
