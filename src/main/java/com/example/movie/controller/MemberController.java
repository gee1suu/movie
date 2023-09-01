package com.example.movie.controller;

import com.example.movie.domain.Member;
import com.example.movie.dto.MemberForm;
import com.example.movie.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "/member/login";
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@Valid MemberForm form, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "member/join";
        }

        try {
            Member member = Member.createUser(form, passwordEncoder);
            memberService.join(member);
        } catch(IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/join";
        }

        return "redirect:/";
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

    @RequestMapping("/mypage")
    public String mypage() {
        return "member/mypage";
    }
}
