package book.study_jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setCreateDate(LocalDateTime.now());
        return cart;
    }
}
