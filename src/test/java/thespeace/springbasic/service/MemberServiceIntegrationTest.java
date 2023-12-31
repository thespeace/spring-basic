package thespeace.springbasic.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import thespeace.springbasic.domain.Member;
import thespeace.springbasic.repository.MemberRepository;
import thespeace.springbasic.repository.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *  - 통합 테스트
 *    @SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행한다. <-> MemberServiceTest.java, 순수 자바 코드로 최소한의 단위로 테스트를 하는 것은 "단위 테스트"라 한다.
 *    @Transactional : 테스트 케이스에 이 어노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다.
 *                     이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
 * */

@SpringBootTest
@Transactional // 데이터베이스에 insert를 하고 테스트가 끝난 후 rollback처리를 해준다.(실제 데이터베이스에 반영이 안된다)
class MemberServiceIntegrationTest {

    @Autowired // 테스트 케이스는 필드 기반으로 편하게 사용.
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
//    @Commit  // @Transactional이 있어도 DB에 반영.
    void 회원가입() {
        //given
        Member member =new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findmember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findmember.getName());
    }

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
    }

}