package book.study_jpa.repository;

import book.study_jpa.domain.Coupon;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CouponRepository {

    private final EntityManager em;

    public void save(Coupon coupon) {
        em.persist(coupon);
    }

    public List<Coupon> findAll(Long memberId) {
        return em.createQuery("select c from Coupon c where c.member.id = :memberId and c.useDate is null")
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public Coupon findOne(Long couponId) {
        return em.find(Coupon.class, couponId);
    }
}
