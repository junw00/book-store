package book.study_jpa.controller;

import book.study_jpa.domain.Book;
import book.study_jpa.domain.Coupon;
import book.study_jpa.domain.Member;
import book.study_jpa.dto.RequestOrderDto;
import book.study_jpa.repository.BookRepository;
import book.study_jpa.repository.CouponRepository;
import book.study_jpa.repository.MemberRepository;
import book.study_jpa.service.BookService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final BookRepository bookRepository;
    private final CouponRepository couponRepository;

    @GetMapping("/orders/{bookId}")
    public String orderForm(Model model,
                            @RequestParam int quantity,
                            @PathVariable Long bookId,
                            HttpSession session) {

        Member member = (Member) session.getAttribute("member");
        List<Coupon> coupons = couponRepository.findAll(member.getId());
        Book book = bookRepository.findOne(bookId);
        model.addAttribute("book", book);
        model.addAttribute("coupons", coupons);
        model.addAttribute("quantity", quantity);
        model.addAttribute("totalPrice", book.getPrice() * quantity);
        model.addAttribute("member", member);

        return "orders/order";
    }
}
