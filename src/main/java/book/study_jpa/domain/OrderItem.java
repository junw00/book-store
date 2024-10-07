package book.study_jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity @Setter @Getter
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "bookId")
    private Book book;

    private int price;

    private int quantity;

    public static OrderItem createOrderItem(Book book, int quantity, int price) {
        OrderItem orderItem = new OrderItem();
        orderItem.setBook(book);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(quantity * price);
        book.removeStock(quantity);
        return orderItem;
    }
}
