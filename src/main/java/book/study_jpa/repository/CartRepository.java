package book.study_jpa.repository;

import book.study_jpa.domain.Cart;
import book.study_jpa.domain.CartItem;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    private final EntityManager em;

    public void saveCart(Cart cart) {
        em.persist(cart);
    }

}
