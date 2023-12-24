package thespeace.springbasic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thespeace.springbasic.domain.Member;
import thespeace.springbasic.repository.MemberRepository;
import thespeace.springbasic.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;


//@Service //어노테이션이 없으면 순수한 자바클래스, Bean에 등록해서 공유하여 사용하자.
public class MemberService {

    /**
     *  - 클래스의 Role에 맞게 메서드명을 작성하자.
     *      Repository는 단순히 저장소에 넣었다 뺐다 하는 느낌의 메서드명을 사용하지만, Service Class는 비즈니스에 가까운 용어를 사용 권장.
     *      때문에 Service 비즈니스에 의존적으로 설계를 하고, Repository는 Repository를 잘 드러내는 메서드명을 사용해서 설계하면 된다.
     */


//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 테스트 코드와 같은 인스턴스를 사용하기 위해 변경.

    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) { // new로 인스턴스 생성이 아니라 외부에서 주입되는 식으로 변경.(DI(디펜던시 인젝션) : 의존성 주입)
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.orElseGet(); // optional이 아니라면 null처리 할때 사용가능.
//        result.ifPresent(m -> { // ifPresent : 값이 null이 아니면 동작. Optional타입이기에 가능.
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        memberRepository.findByName(member.getName())
                .ifPresent(m -> { 
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조희
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
