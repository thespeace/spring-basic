package thespeace.springbasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// controller : 웹 어플리케이션의 첫번째 진입점
@Controller
public class HelloController {

    @GetMapping("thymeleaf")                                                // 해당 url진입시 해당 메서드 호출.
    public String thymeleaf(Model model) {                                     // mvc의 m을 담당하고 있는 model, 나머지는 view, controller.
        model.addAttribute("data", "thespeace!!!");    // 스프링이 지원하는 기능으로써, key와 value로 이루어져있는 HashMap.
        return "thymeleaf";                                                    // 스프링은 기본적으로 리턴 값으로 문자를 반환하면 뷰 리졸버('viewResolver')가 화면을 찾아서 처리한다.
    }                                                                          // 스프링 부트 템플릿엔진 기본 viewName 매핑 :  `resources/templates/` + {ViewName} + `.html`

    // 참고 : `spring-boot-devtools` 라이브러리를 추가하면, `html`파일을 컴파일만 해주면 서버 재시작 없이 View파일 변경이 가능하다. 적용 : 인텔리J Recompile 검색.

    @GetMapping("build")
    public String hello() {
        return "build";
    }

    @GetMapping("mvc")
    public String mvc(@RequestParam("name") String name, Model model){ // 외부에서 url parameter로 name을 받아서- (required = false로 하면 무조건 안넘겨도 된다.)
        model.addAttribute("name", name);                  // model에 name으로 담아 화면으로 전송.
        return "mvc";                                                  // + 꿀팁 : `ctrl + p` -> Check parameter info.
    }

    /**
     *  @ResponseBody 사용.
     *  - HTTP의 BODY에 문자 내용을 직접 반환.
     *  - `viewResolver` 대신에 `HttpMessageConverter`가 동작
     *  - 기본 문자처리 : `StringHttpMessageConverter`
     *  - 기본 객체처리 : `MappingJackson2HttpMessageConverter` (Jackson : 객체를 json으로 바꾸어주는 유명 라이브러리. (그밖의 Gson..))
     *  - byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있다.
     *
     *  @ResponseBody 사용 원리
     *  - 웹 브라우저 -> URL -> 내장 톰캣 서버 -> controller -> @ResponseBody -> HttpMessageConverter(반환타입에 맞는 converter 동작) -> 반환타입 반환
     *
     *  + 참고
     *  - 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보, 둘을 조합해서 `HttpMessageConverter`가 선택된다.
     *    ex) xml라이브러리를 입력시켜놓고 json타입도 xml도 반환이 된다.
     */

    @GetMapping("api-string")
    @ResponseBody                                                // viewResolver를 사용하지 않음, 대신 http프로토콜의 body부분에 데이터를 직접 보내겠다는 뜻.
    public String apiString(@RequestParam("name") String name){
        return "데이터를 바로 받는 " + name;                        // 데이터 String 반환.
    }

    @GetMapping("api-json")
    @ResponseBody
    public Api apiJson(@RequestParam("name") String name){
        Api api = new Api();
        api.setName(name);
        return api;                                     //객체 반환(json).
    }                                                   //json과 xml(ex, html)이 격돌 하다가 json방식으로 통일되었다. 사실 @ResponseBody 어노테이션을 사용하면 default 반환 타입이 json이다.

    static class Api {
        private String name;

        public String getName(){ // java bean 규약(property 접근 방식), private 접근제어자의 객체를 해당 메서드를 통해서 접근.
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }



    @GetMapping("backend-requirements")
    public String backendRequirements() {
        return "backend-requirements";
    }

    @GetMapping("test-case")
    public String testCase() {
        return "test-case";
    }

    @GetMapping("component-scan-auto-dependency-setup")
    public String beanAndDI1() { return "component-scan-auto-dependency-setup"; }

    @GetMapping("registering-bean-java")
    public String beanAndDI2() { return "registering-bean-java"; }
}
