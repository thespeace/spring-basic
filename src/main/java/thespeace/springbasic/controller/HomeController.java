package thespeace.springbasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /*@GetMapping("/") // 웰컴 페이지 설정, 컨트롤러가 정적 파일보다 우선순위가 높다는 걸 알 수 있다.
    public String index() {
        return "index";
    }*/

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
