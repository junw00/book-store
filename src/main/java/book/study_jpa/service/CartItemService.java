package book.study_jpa.service;

import book.study_jpa.domain.Book;
import book.study_jpa.domain.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemService {


    public void update(List<CartItem> cartItems, Long bookId, int quantity) {

        for(CartItem cartItem : cartItems) {
            Book findBook = cartItem.getBook();
            if (findBook.getBookId() == bookId) {
                cartItem.changeCartItem(quantity);
                break;
            }
        }
    }

}
