package thespeace.springbasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import thespeace.springbasic.service.MemberService;

@Controller // Spring이 컨테이너에 해당 컨트롤러 객체를 생성해서 컨테이너에 넣어두고 관리를 한다. 이를 스프링 컨테이너에서 스프링 Bean을 관리된다라고 표현한다.
public class MemberController {

//    private final MemberService memberService = new MemberService();
    // 이와 같이 생성해서 사용할 수도 있지만, 스프링이 관리를 하게 되면 스프링에 등록하여 받아서 쓰는 것으로 바꾸는 것이 좋다.
    // 여러개를 생성해서 쓸 필요없이 하나만 생성하여 공유하기 위함.

    /**
     * 스프링 자바의 정형화된 패턴 : Controller(외부 요청) - Service(비즈니스 로직) - Repository(데이터 저장)
     * */

    private final MemberService memberService;

    @Autowired // 생성자에 @Autowired가 붙으면, 스프링 컨테이너에서 memberService를 연결을 시켜준다. 즉 스프링 컨테이너에 있는 스프링 빈을 주입(DI, 의존성 주입)받고자 할 때 사용하는 애노테이션.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}