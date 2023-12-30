package thespeace.springbasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import thespeace.springbasic.repository.JdbcMemberRepository;
import thespeace.springbasic.repository.JdbcTemplateMemberRepository;
import thespeace.springbasic.repository.MemberRepository;
import thespeace.springbasic.repository.MemoryMemberRepository;
import thespeace.springbasic.service.MemberService;

import javax.sql.DataSource;

@Configuration // 스프링이 동작할때 해당 설정파일을 먼저 인식하고 Bean에 등록해준다.
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) { // DataSource는 데이터베이스 커넥션을 획득할 때 사용하는 객체다. 스프링 부트는 데이터베이스 커넥션 정보를
        this.dataSource = dataSource;            // 바탕으로 DataSource를 생성하고 스프링 빈으로 만들어둔다. 그래서 DI를 받을 수 있다.
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
