package thespeace.springbasic.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // Aspect: 해당 어노테이션이 있어야 aop사용가능.
@Component // 이렇게 빈에 등록할 수도 있지만 스프링 빈에 직접 등록하는게 좋다.
public class TimeTraceAop {

    @Around("execution(* thespeace.springbasic..*(..))*") // @Around: 공통 관심사항을 타겟팅 해줄 수 있다. 검색 - Around
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString()); // joinPoint로 메소드를 호출 할 때마다 중간에 인터셉트로 확인 할 요소 확인 가능. 검색 - joinPoint
        try {
            return joinPoint.proceed();// 다음 메소드 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
