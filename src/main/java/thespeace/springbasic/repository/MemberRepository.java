package thespeace.springbasic.repository;

import thespeace.springbasic.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional이란? 자바 8버전에서 생겨난 기능인데, 간단하게 말하자면 null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 저장된 모든 회원 리스트 반환.
}
