package book.study_jpa.service;

import book.study_jpa.domain.*;
import book.study_jpa.repository.BookRepository;
import book.study_jpa.repository.CouponRepository;
import book.study_jpa.repository.MemberRepository;
import book.study_jpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;

    @Transactional
    public void order(Long bookId, int quantity, Long memberId, Address address, int price, String opt, Long couponId) {
        Book findBook = bookRepository.findOne(bookId);
        Member findMember = memberRepository.findOne(memberId);
        OrderItem orderItem = OrderItem.createOrderItem(findBook, quantity, price);
        Coupon findCoupon = couponRepository.findOne(couponId);
        findCoupon.setUseDate(LocalDateTime.now());
        Order order = Order.createOrder(findMember, orderItem, address);
        if(findCoupon.getOpt().equals("10%")) {
            order.setPrice(orderItem.getPrice() - (orderItem.getPrice() * 10 / 100));
        }else if(findCoupon.getOpt().equals("1000")) {
            order.setPrice(orderItem.getPrice() - 1000);
        }
        order.setCoupon(findCoupon);
        orderRepository.save(order);
    }
}
