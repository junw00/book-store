package book.study_jpa.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestCartItemDto {
    private Long memberId;
    private int quantity;
    private Long bookId;
}
