package book.study_jpa.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Embeddable @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String postalCode;

    private String basicAddress;

    private String detailAddress;

}
