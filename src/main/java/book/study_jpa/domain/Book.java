package book.study_jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookName;

    private int stock;

    private int price;

    public void removeStock(int quantity) {
        int restStock = this.stock - quantity;
        if(restStock < 0) {
            throw new IllegalStateException("재고가 부족합니다.");
        }else {
            this.stock = restStock;
        }
    }
}
