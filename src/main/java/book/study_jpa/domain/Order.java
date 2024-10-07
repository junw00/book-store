package book.study_jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Setter @Getter
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private int price;

    private String cardNum;
    private CardCompany cardCompany;

    private String cardValid;

    @Embedded
    private Address address;

    private LocalDateTime orderDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void useCoupon(Coupon coupon) {
        coupon.setUseDate(LocalDateTime.now());
    }

    public static Order createOrder(Member member, OrderItem orderItem, Address address) {
        Order order = new Order();
        order.setMember(member);
        order.addOrderItem(orderItem);
        order.setAddress(address);
        order.setPrice(orderItem.getPrice());
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public static Order createOrder(Member member, List<CartItem> cartItems) {
        Order order = new Order();
        int totalPrice = 0;
        for(CartItem cartItem : cartItems) {
            OrderItem orderItem = OrderItem.createOrderItem(cartItem.getBook(), cartItem.getQuantity(), cartItem.getPrice());
            order.addOrderItem(orderItem);
            totalPrice += cartItem.getPrice();
        }
        order.setMember(member);
        order.setPrice(totalPrice);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
}
