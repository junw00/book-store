package book.study_jpa.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CartListForm {

    List<RequestCartItemDto> cartItemDtoList;
}
