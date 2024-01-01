package thespeace.springbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thespeace.springbasic.domain.Member;

import java.util.Optional;

// interface만 만들어 놓으면 Spring Jpa가 자동으로 구현체를 만들어서 스프링 bean에 등록을 해준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL : select m from Member m where m.name = ?
    //인터페이스 이름만으로 개발이 끝, 메서드 네임이랑 반환 타입, 파라미터 등을 리플렉션 기술로 읽어서 풀어내는 마법.
    @Override
    Optional<Member> findByName(String name);
}
