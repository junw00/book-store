package book.study_jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Coupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long couponId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    private String opt;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "use_date")
    private LocalDateTime useDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public static Coupon createCoupon(String option, Member member) {
        Coupon coupon = new Coupon();
        coupon.setOpt(option);
        coupon.setEndDate(LocalDateTime.now());
        coupon.setStartDate(LocalDateTime.now());
        coupon.setMember(member);
        return coupon;
    }
}
