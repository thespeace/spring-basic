package thespeace.springbasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// controller : 웹 어플리케이션의 첫번째 진입점
@Controller
public class HelloController {

    @GetMapping("hello")                                                    // 해당 url진입시 해당 메서드 호출.
    public String hello(Model model) {                                         // mvc의 m을 담당하고 있는 model, 나머지는 view, controller.
        model.addAttribute("data", "thespeace!!!");    // 스프링이 지원하는 기능으로써, key와 value로 이루어져있는 HashMap.
        return "hello";                                                        // 스프링은 기본적으로 리턴 값으로 문자를 반환하면 뷰 리졸버('viewResolver')가 화면을 찾아서 처리한다.
    }                                                                          // 스프링 부트 템플릿엔진 기본 viewName 매핑 :  `resources/templates/` + {ViewName} + `.html`

    // 참고 : `spring-boot-devtools` 라이브러리를 추가하면, `html`파일을 컴파일만 해주면 서버 재시작 없이 View파일 변경이 가능하다. 적용 : 인텔리J Recompile 검색.

    @GetMapping("build")
    public String hello() {
        return "build";
    }
}
