package book.study_jpa.controller;

import book.study_jpa.domain.Member;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        model.addAttribute("member", member);
        log.info("Member ID: {}", member != null ? member.getId() : "No member found");
        return "home";
    }
}
