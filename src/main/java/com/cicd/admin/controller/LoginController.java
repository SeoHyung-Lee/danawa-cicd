package com.cicd.admin.controller;

import com.cicd.admin.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(value = "/check")
    public ResponseEntity<Boolean> isAdminLoginUser(@RequestParam("id") String id, @RequestParam("pwd") String pwd, HttpServletResponse response) {
        Boolean isLoginUser = loginService.isAdminLoginUser(id, pwd);
        if (isLoginUser) {
            Cookie cookie = new Cookie("isLoginUser", id);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        return ResponseEntity.status(HttpStatus.OK).body(isLoginUser);
    }
}
