package thespeace.springbasic.repository;

import org.springframework.stereotype.Repository;
import thespeace.springbasic.domain.Member;

import java.util.*;

@Repository // bean 등록
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 동시성 문제가 있을 수 있어서 공유되는 변수일때는 ConcurrentHashMap 써야하는데, 예제임으로 단순히 HashMap 사용.
    private static long sequence = 0L; // 실무에서는 동시성 문제를 고려해서 AtomicLong을 사용해야 한다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);           // member의 id값 세팅
        store.put(member.getId(), member);  // map에 저장
        return member;                      // 저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()                           // lambda식 사용, stream() : 반복자
                .filter(member -> member.getName().equals(name)) // filter() : 스트림내 요소에 대해서 필터링하는 작업
                .findAny();                                      // findAny() : 조건에 일치하는 요소 1개를 리턴
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 멤버들(store.values()) 반환
    }

    public void clearStore() {
        store.clear(); // 리스트의 객체를 모두 삭제하는데 사용되는 메소드
    }
}
