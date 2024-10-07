package book.study_jpa.dto;

import book.study_jpa.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberRequestDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    public Member toEntity() {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setName(name);
        return member;
    }
}
