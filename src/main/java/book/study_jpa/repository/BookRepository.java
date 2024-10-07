package book.study_jpa.repository;

import book.study_jpa.domain.Book;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final EntityManager em;

    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    public Book findOne(Long bookId) {
        return em.find(Book.class, bookId);
    }
}
