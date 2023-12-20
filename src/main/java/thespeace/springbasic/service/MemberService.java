package thespeace.springbasic.service;

import thespeace.springbasic.domain.Member;
import thespeace.springbasic.repository.MemberRepository;
import thespeace.springbasic.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    /**
     *  - 클래스의 Role에 맞게 메서드명을 작성하자.
     *      Repository는 단순히 저장소에 넣었다 뺐다 하는 느낌의 메서드명을 사용하지만, Service Class는 비즈니스에 가까운 용어를 사용 권장.
     *      때문에 Service 비즈니스에 의존적으로 설계를 하고, Repository는 Repository를 잘 드러내는 메서드명을 사용해서 설계하면 된다.
     */

    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
