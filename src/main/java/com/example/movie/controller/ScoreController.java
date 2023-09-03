package com.example.movie.controller;

import com.example.movie.domain.Score;
import com.example.movie.dto.ScoreForm;
import com.example.movie.service.MemberService;
import com.example.movie.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;
    private final MemberService memberService;

    @RequestMapping("/myscore")
    public String myscore(Principal principal, Model model) {
        List<Score> scoreList = scoreService.loadMyscore(principal.getName());
        model.addAttribute("scoreList", scoreList);
        return "member/myscore";
    }

    @PostMapping("/modifyScore")
    public String modifyScore(float score, Long id) {
        scoreService.modifyScore(score, id);
        return "redirect:/myscore";
    }

    @PostMapping("/deleteScore")
    public String deleteScore(Long id) {
        scoreService.deleteScore(id);
        return "redirect:/myscore";
    }

    @PostMapping("/addScore")
    public String addScore(ScoreForm scoreForm, Principal principal) {
        scoreForm.setMemberId(memberService.findIdByEmail(principal.getName()));
        Score score = Score.createScore(scoreForm);
        scoreService.addScore(score);
        return "redirect:/myscore";
    }
}
