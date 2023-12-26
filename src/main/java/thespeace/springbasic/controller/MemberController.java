package thespeace.springbasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import thespeace.springbasic.domain.Member;
import thespeace.springbasic.service.MemberService;

import java.util.List;

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

/*    @Autowired
    private MemberService memberService; // field injection, 해당 주입은 중간에 바꿀수있는 요소가 없기때문에 좋지 않다.*/

/*    @Autowired
    public void setMemberService(MemberService memberService) { // setter injection 방식은 어플리케이션 로딩시점에서 한번 세팅이 되고나면 바꿀일이 없기때문에 잘쓰지않는다.
        this.memberService = memberService;
    }*/

    @GetMapping("/members/new")
    public String creatForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());
        
        memberService.join(member);

        return "redirect:/home";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
