package thespeace.springbasic.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import thespeace.springbasic.domain.Member;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest { // 관례 : 테스트할 클래스 뒤에 `Test`를 붙여 테스트 클래스 생성

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     *  @AfterEach : 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 이렇게 되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다.
     *                @AfterEach 를 사용하면 각 테스트가 종료될 때마다 이 기능을 실행한다. 여기서는 메모리 DB에 저장된 데이터를 삭제한다.
     *
     *  모든 테스트의 메서드 동작의 순서는 보장이 안된다. 모든 테스트는 순서랑 상관없이 메서드별로 따로 동작하게 설계를 해야한다(의존관계 X).
     *  테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다
     */
    @AfterEach // 메서드들의 실행이 끝날때마다 동작을 하는 콜백메서드라 보면된다
    public void afterEach(){ // 테스트가 끝날때마다 repository를 깔끔하게 지워주는 코드
        repository.clearStore(); // 테스트가 실행되고 끝날때마다 한번씩 저장소를 비운다. (**중요!!**)
    }

    @Test //해당 메서드를 실행할 수가 있다. main 메서드 쓰듯이 사용 가능.
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // Optional이기 때문에 .get()을 사용(비추)
        System.out.println("result = " + (result == member));

        // junit.jupiter | assertEquals(실제값, 기대값) : 두 값을 비교할 때 사용
//        Assertions.assertEquals(member, result); // OK
//        Assertions.assertEquals(member, null); // Error,

        // assertj : junit.jupiter보다 좀 더 편하게 사용가능.
//        Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result); // static import하면 Assertions 생략 가능.
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1); // Ok
//        assertThat(result).isEqualTo(member2); // Error
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2); // Ok
//        assertThat(result.size()).isEqualTo(3); // Error
    }

}
