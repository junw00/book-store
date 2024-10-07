package book.study_jpa.service;

import book.study_jpa.domain.Coupon;
import book.study_jpa.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public void submit(Coupon coupon) {
        couponRepository.save(coupon);
    }
}
