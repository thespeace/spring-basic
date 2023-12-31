package thespeace.springbasic.repository;

import jakarta.persistence.EntityManager;
import thespeace.springbasic.domain.Member;

import java.util.List;
import java.util.Optional;

/**
 *  - JPA
 *    JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다
 *    JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환을 할 수 있다.
 *    JPA를 사용하면 개발 생산성을 크게 높일 수 있다.
 *
 *    참고: JPA도 스프링 만큼 성숙한 기술이고, 학습해야 할 분량도 방대하며 실무(조단위 거래의 은행 등등)에서도 잘 사용되고 있는 기술이다.
 * */

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // 스프링 부트가 자동으로 생성, Jpa는 EntityManeger로 모든 동작을 한다. injection만 하면 된다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);

    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    /**
     *  - jpql
     *    보통 쿼리는 테이블을 대상으로 하는데 jpql은 객체를 대상으로 쿼리를 날린다.
     *    정확히는 entity를 대상으로 쿼리를 날리는 건데, 위 쿼리를 보면 객체 자체를 select한다.
     *    단건이나 pk기반이 아닌 나머지(findByName, findAll) 여러 개의 리스트를 가지고 돌릴 때는 jpql을 작성해주어야 한다.
     * */
}
