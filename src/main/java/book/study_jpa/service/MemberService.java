package book.study_jpa.service;

import book.study_jpa.domain.Member;
import book.study_jpa.dto.MemberRequestDto;
import book.study_jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public void join(MemberRequestDto memberRequestDto) throws Exception {
        Member member = memberRequestDto.toEntity();
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    /**
     * 로그인
     */
    public Optional<Member> signin(MemberRequestDto memberRequestDto) throws Exception {
        Optional<Member> member = memberRepository.findByUsername(memberRequestDto.getUsername())
                .filter(m -> memberRequestDto.getPassword().equals(m.getPassword()));
        log.info("member: {}", member.stream().findAny());
        return member.stream().findAny();
    }

    private void validateDuplicateMember(Member member) throws Exception {
        memberRepository.findByUsername(member.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }
        );
    }
}
