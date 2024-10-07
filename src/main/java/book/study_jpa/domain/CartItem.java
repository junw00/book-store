package book.study_jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class CartItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItem_id")
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private int quantity;

    private int price;

    public void updateCartItem(int quantity) {
        this.quantity += quantity;
        price = this.quantity * book.getPrice();
    }

    public void changeCartItem(int quantity) {
        this.quantity = quantity;
        price = this.quantity * book.getPrice();
    }

    public static CartItem createCartItem(Book book, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(book.getPrice() * quantity);
        return cartItem;
    }
}
