package book.study_jpa.controller;

import book.study_jpa.domain.*;
import book.study_jpa.dto.CartListForm;
import book.study_jpa.dto.RequestCartItemDto;
import book.study_jpa.repository.MemberRepository;
import book.study_jpa.repository.OrderRepository;
import book.study_jpa.service.CartService;
import book.study_jpa.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final MemberRepository memberRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping("/carts")
    public String cartForm(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        Member findMember = memberRepository.findOne(member.getId());
        List<CartItem> cartItems = findMember.getCart().getCartItems();
        model.addAttribute("cartItems", cartItems);

        return "carts/cart";
    }

    @PostMapping("/carts/order")
    public String cartOrder(@ModelAttribute CartListForm cartListForm,
//                            @RequestParam(required = false) String postalCode,
//                            @RequestParam(required = false) String basicAddress,
//                            @RequestParam(required = false) String detailAddress,
                            HttpSession session) {
        List<RequestCartItemDto> cartItemDtoList = cartListForm.getCartItemDtoList();
        Long memberId = ((Member) session.getAttribute("member")).getId();
        cartService.orderCart(memberId);

        return "redirect:/";
    }

}
