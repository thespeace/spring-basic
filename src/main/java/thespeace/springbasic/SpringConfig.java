package thespeace.springbasic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import thespeace.springbasic.repository.MemberRepository;
import thespeace.springbasic.repository.MemoryMemberRepository;
import thespeace.springbasic.service.MemberService;

@Configuration // 스프링이 동작할때 해당 설정파일을 먼저 인식하고 Bean에 등록해준다.
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
