package com.example.movie.controller;

import com.example.movie.domain.Member;
import com.example.movie.domain.Ticket;
import com.example.movie.dto.EmailPostDto;
import com.example.movie.dto.MemberForm;
import com.example.movie.dto.MemberInfoForm;
import com.example.movie.dto.ResetPwForm;
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
import java.security.Principal;
import java.util.List;

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
    public String findPw1Form(Model model) {
        model.addAttribute("emailPostDto", new EmailPostDto());
        return "member/findPw_1";
    }

    @PostMapping("/find-pw2")
    public String findPw2(String email, Model model) {
        model.addAttribute("resetPwForm", new ResetPwForm());
        model.addAttribute("email", email);
        return "member/resetPw";
    }

    @PostMapping("/reset-pw")
    public String resetPw(@Valid ResetPwForm form, BindingResult result) {
        if(result.hasErrors()) {
            return "member/resetPw";
        }
        Member member = Member.createUser(form, passwordEncoder);
        memberService.changePassword(member);
        return "redirect:/";
    }

    @PostMapping("/change-pw")
    public String chagngePw(Principal principal, Model model) {
        model.addAttribute("resetPwForm", new ResetPwForm());
        model.addAttribute("email", principal.getName());
        return "member/resetPw";
    }

    @RequestMapping("/mypage")
    public String mypage(Principal principal, Model model) {
        MemberInfoForm memberInfoForm = memberService.loadMemberInfo(principal.getName());
        model.addAttribute("memberInfoForm", memberInfoForm);
        return "member/mypage";
    }

    @RequestMapping("/myticket")
    public String myticket(Principal principal, Model model) {
        List<Ticket> ticketList = memberService.loadMyticket(principal.getName());
        model.addAttribute("ticketList", ticketList);
        return "member/myticket";
    }

    @GetMapping("/cancel")
    public String cancel(Principal principal) {
        memberService.cancelAccount(principal.getName());
        return "redirect:/logout";
    }
}
