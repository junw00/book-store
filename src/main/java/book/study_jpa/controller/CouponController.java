package book.study_jpa.controller;

import book.study_jpa.domain.Coupon;
import book.study_jpa.domain.Member;
import book.study_jpa.repository.MemberRepository;
import book.study_jpa.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CouponController {

    private final MemberRepository memberRepository;
    private final CouponService couponService;

    @PostMapping("/coupon")
    public String create(@RequestParam String action, @RequestParam Long memberId) {
        log.info("act:{}", action);
        Member findMember = memberRepository.findOne(memberId);
        Coupon coupon = Coupon.createCoupon(action, findMember);

        couponService.submit(coupon);

        return "redirect:/";
    }
}
