package book.study_jpa.repository;

import book.study_jpa.domain.Book;
import book.study_jpa.domain.CartItem;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartItemRepository {

    private final EntityManager em;

//    public CartItem findCartItemByCart (Long bookId) {
////        em.createQuery("select ")
//    }
}
