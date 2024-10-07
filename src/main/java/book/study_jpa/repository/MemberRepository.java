package book.study_jpa.repository;

import book.study_jpa.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findByUsername(String username) throws Exception{
        List<Member> findMember = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
        return findMember.stream().findAny();
    }

    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }


}
