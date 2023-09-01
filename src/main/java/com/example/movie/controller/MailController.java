package com.example.movie.controller;

import com.example.movie.domain.EmailMessage;
import com.example.movie.dto.EmailPostDto;
import com.example.movie.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/send-mail")
@Controller
@RequiredArgsConstructor
public class MailController {
    private final EmailService emailService;

    @PostMapping("/email")
    public String sendJoinMail(@Valid EmailPostDto emailPostDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "/member/findPw_1";
        }

        EmailMessage emailMessage = EmailMessage.builder()
                .to(emailPostDto.getEmail())
                .subject("[헬로시네마] 이메일 인증을 위한 인증번호 발송")
                .build();

        String code = emailService.sendMail(emailMessage, "email");
        model.addAttribute("code", code);
        model.addAttribute("email", emailPostDto.getEmail());

        return "/member/findPw_2";
    }
}
