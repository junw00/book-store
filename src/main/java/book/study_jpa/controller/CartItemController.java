package book.study_jpa.controller;

import book.study_jpa.domain.Book;
import book.study_jpa.domain.CartItem;
import book.study_jpa.domain.Member;
import book.study_jpa.dto.RequestCartItemDto;
import book.study_jpa.repository.MemberRepository;
import book.study_jpa.service.CartItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CartItemController {

    private final MemberRepository memberRepository;
    private final CartItemService cartItemService;

    @PostMapping("/cartItems")
    public String changeCartItem(@RequestBody RequestCartItemDto requestCartItemDto) {

        log.info("Request: {}", requestCartItemDto.toString());
        Member findMember = memberRepository.findOne(requestCartItemDto.getMemberId());
        List<CartItem> cartItems = findMember.getCart().getCartItems();

        cartItemService.update(cartItems, requestCartItemDto.getBookId(), requestCartItemDto.getQuantity());

        return "redirect:/carts";
    }
}
