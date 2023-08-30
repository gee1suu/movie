package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    @GetMapping("/find-pw1")
    public String findPw1Form() {
        return "member/findPw_1";
    }

    @GetMapping("/find-pw2")
    public String findPw2Form() {
        return "member/findPw_2";
    }

    @GetMapping("/reset-pw")
    public String resetPwForm() {
        return "member/resetPw";
    }
}
