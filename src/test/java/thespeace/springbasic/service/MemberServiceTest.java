package thespeace.springbasic.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import thespeace.springbasic.domain.Member;
import thespeace.springbasic.repository.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    /**
     *  테스트 코드는 실제 동작하는 코드와 무관하기에 한글로 적어도 무관하다.
     *
     *  주석(given-when-then)을 해놓고 패턴을 익히면서 테스트코드를 작성하면 조금 더 수월하다.
     */

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given(어떠한 상황이 주어졌을때-)
        Member member =new Member();
        member.setName("spring");

        //when(실행했을때-)
        Long saveId = memberService.join(member);

        //then(결과가 나와야한다.)
        Member findmember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findmember.getName());
    }

    //테스트는 정상플로우도 중요하지만 예외플로우가 더 중요하다.
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*
        try { // 예외 처리(try-catch)
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch(IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then

    }
}