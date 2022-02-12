package com.core.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    /** Provider를 사용하지 않으면 스프링 컨테이너 생성 시 에러가 발생한다.
     ** 스프링 컨테이너 생성 시 DI로 인스턴스 주입이 발생하는데,
     ** request scope의 MyLogger.java를 주입되지 않기 때문이다.
     **
     ** 스프링 실행 시에는 request가 발생하지 않기 때문에
     ** request scope의 MyLogger.java 인스턴스가 없어 에러가 발생한다.
     **
     ** 해결 방법 1.
     ** spring의 ObjectProvider 혹은 javax의 Provider 사용
     ** 다만, DI 해주는 소스에 보두 추가해야해서 번거롭다.
     **
     ** 해결 방법 2.
     ** MyLogger.java에 @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) 사용
     ** 해당 클래스에 어노테이션 옵선을 추가해주면, Provider로 인한 지연 없이 사용 가능하다.
     ** (Spring이 CGLIB 바이트코드 조작 라이브러리로 MyLogger.java의 dummy를 만들어 컨테이너 생성 시 주입시킨다.
     ** 추후 logger가 호출될 때, 진짜 MyLogger의 멤버를 호출하는 프록시 역할을 한다.)
     **
     **/

    // private final MyLogger myLogger;
    private final Provider<MyLogger> loggerProvider;
    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    @ResponseBody
    public void logDemo(HttpServletRequest request) throws InterruptedException {
        MyLogger myLogger = loggerProvider.get();
        myLogger.setRequestURL(request.getRequestURL().toString());
        myLogger.log("controller test");
        Thread.sleep(1000); // thread로 request마다 sleep을 걸어 운영 환경과 비슷하게 만들 수 있다. uuid로 요청 구분 가능
        logDemoService.logic("testId");
    }

    /** result
     *
     * [f2df9fdf-ec44-4b36-a4df-043b6f8149a3] request scope bean created. com.core.common.MyLogger@19338a2e
     * [f2df9fdf-ec44-4b36-a4df-043b6f8149a3] [http://localhost:8080/log-demo] controller test
     * [f2df9fdf-ec44-4b36-a4df-043b6f8149a3] [http://localhost:8080/log-demo] service id testId
     * [f2df9fdf-ec44-4b36-a4df-043b6f8149a3] request scope bean closed. com.core.common.MyLogger@19338a2e
     * [1d26d078-bef0-4e6e-8ba9-66197ee46d3b] request scope bean created. com.core.common.MyLogger@404c160b
     * [1d26d078-bef0-4e6e-8ba9-66197ee46d3b] [http://localhost:8080/log-demo] controller test
     * [1d26d078-bef0-4e6e-8ba9-66197ee46d3b] [http://localhost:8080/log-demo] service id testId
     * [1d26d078-bef0-4e6e-8ba9-66197ee46d3b] request scope bean closed. com.core.common.MyLogger@404c160b
     */

}
