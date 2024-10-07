package book.study_jpa.controller;

import book.study_jpa.domain.Member;
import book.study_jpa.dto.MemberRequestDto;
import book.study_jpa.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String signupForm(Model model) {
        model.addAttribute("member", new MemberRequestDto());
        return "auth/signup";
    }

    @PostMapping("/members/new")
    public String signup(@Valid MemberRequestDto memberRequestDto, BindingResult result) {
        if(result.hasErrors()) {
            return "redirect:/members/new";
        }
        try {
            memberService.join(memberRequestDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @GetMapping("/members")
    public String signinForm(Model model) {
        model.addAttribute("member", new MemberRequestDto());
        return "auth/signin";
    }

    @PostMapping("/members")
    public String signin(MemberRequestDto memberRequestDto, HttpSession session, Model model) {
        try {
            Member member = memberService.signin(memberRequestDto).get();
            session.setAttribute("member", member);
            model.addAttribute("member", member);
        } catch (Exception e) {
            return "redirect:/members";
        }
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
