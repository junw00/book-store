package book.study_jpa.controller;

import book.study_jpa.domain.Address;
import book.study_jpa.domain.Book;
import book.study_jpa.domain.Cart;
import book.study_jpa.domain.Member;
import book.study_jpa.repository.BookRepository;
import book.study_jpa.service.CartService;
import book.study_jpa.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookRepository bookRepository;
    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping("/items")
    public String booksForm(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "item/items";
    }
    @GetMapping("/items/{bookId}")
    public String bookForm(Model model, @PathVariable Long bookId) {
        Book book = bookRepository.findOne(bookId);
        model.addAttribute("book", book);

        return "item/detail";
    }

    @PostMapping("/items/{bookId}")
    public String order(@PathVariable Long bookId,
                    @RequestParam(required = false) int quantity,
                    @RequestParam(required = false) int price,
                    @RequestParam(required = false) String action,
                    @RequestParam(required = false) String postalCode,
                    @RequestParam(required = false) String basicAddress,
                    @RequestParam(required = false) String detailAddress,
                    @RequestParam(required = false) String couponOpt,
                    @RequestParam(required = false) Long couponId,
                    HttpSession session) {

        log.info("couponId: {}", couponId);
        Address address = new Address(postalCode, basicAddress, detailAddress);
        Member member = (Member) session.getAttribute("member");
        if(action.equals("buy")) {
            orderService.order(bookId, quantity, member.getId(), address, price, couponOpt, couponId);
            return "redirect:/";
        }else {
            Cart cart = cartService.findCart(member.getId());
            log.info("Cart: {}", cart);
            cartService.addCartItem(quantity, cart, bookId);
        }
        return "redirect:/";
    }
}
