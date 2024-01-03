package thespeace.springbasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import thespeace.springbasic.aop.TimeTraceAop;
import thespeace.springbasic.repository.*;
import thespeace.springbasic.service.MemberService;

@Configuration // 스프링이 동작할때 해당 설정파일을 먼저 인식하고 Bean에 등록해준다.
public class SpringConfig {

/*    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) { // DataSource는 데이터베이스 커넥션을 획득할 때 사용하는 객체다. 스프링 부트는 데이터베이스 커넥션 정보를
        this.dataSource = dataSource;            // 바탕으로 DataSource를 생성하고 스프링 빈으로 만들어둔다. 그래서 DI를 받을 수 있다.
    }*/

/*    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

/*    @Bean // 정형화되어 있는 클래스가 아니기 때문에 AOP라는 걸 인지할 수 있도록 직접 bean에 등록.
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/
//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
