package book.study_jpa.service;

import book.study_jpa.domain.*;
import book.study_jpa.repository.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void addCartItem(int quantity, Cart cart, Long bookId) {
//        List<CartItem> findCartItem = cartRepository.findCartItem(bookId);
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getBook().getBookId() == bookId) {
                cartItem.updateCartItem(quantity);
                return;
            }
        }
        Book book = bookRepository.findOne(bookId);
        cart.addCartItem(CartItem.createCartItem(book, quantity));
    }


    @Transactional
    public Cart findCart(Long memberId) {
        Member findMember = memberRepository.findOne(memberId);
        log.info("findCart: {}", findMember);
        Cart cart = findMember.getCart();
        log.info("findCart: {}", cart);
        if(cart == null) {
            findMember.setCart(Cart.createCart(findMember));
        }
        log.info("findCart1: {}", findMember.getCart());
        return findMember.getCart();
    }

    @Transactional
    public void orderCart(Long memberId) {
        List<CartItem> cartItems = memberRepository.findOne(memberId).getCart().getCartItems();
        Member findMember = memberRepository.findOne(memberId);
        Order order = Order.createOrder(findMember, cartItems);
        cartItems.clear();
        orderRepository.save(order);
    }

}
