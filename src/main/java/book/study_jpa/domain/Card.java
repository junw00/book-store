package book.study_jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Card {

    @Id
    private String cardNum;

    private String cardValid;

    private CardCompany cardCompany; //국민은행, 카카오뱅크, 신한은행, 부산은행

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Member member;
}
