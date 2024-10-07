package book.study_jpa.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @OneToOne(fetch = LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Coupon> couponList = new ArrayList<>();

    public void addCoupon(Coupon coupon) {
        couponList.add(coupon);
        coupon.setMember(this);
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setMember(this);
    }
}
