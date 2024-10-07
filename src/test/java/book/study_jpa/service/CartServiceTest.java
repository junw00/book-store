package book.study_jpa.service;

import book.study_jpa.domain.Member;
import book.study_jpa.repository.CartRepository;
import book.study_jpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired CartRepository cartRepository;
    @Autowired CartService cartService;


}