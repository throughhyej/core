package com.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan (
        basePackages = {},
        includeFilters = {},
        /** 현재까지 작성된 예제 코드들로부터 영향을 받지 않기 위해 스캔 대상 제외 **/
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppconfig {

    /* 자동 bean 등록 */

    /* @ComponentScan: @Componant가 부여된 [구현 클래스]를 bean으로 등록하기 위해 찾는데, 찾는 범위를 지정한다.
     *                 default: @ComponentScan이 붙은 패키지의 하위 <-> basePackages: 지정한 범위
     *                 따라서, basc package 바로 하위에 설정 파일을 위치시키는 게 좋다.
     *
     *                 includeFilters: 찾는 범위에 포함되어 있지 않아도 포함하는 설정
     *                 excludeFilters: 찾는 범위에 포함되어도 제외하는 설정
     *
     * @Componant는 구현 클래스에 부여해야 하며, DI 부분에 @Autowired 키워드를 붙여주어야 한다.
     * AppConfig.java 를 통해 직접 구현할 때에는 로직에서 DI를 처리해주었지만, @Componant키워드 만으로 DI 관계를 파악하지 못하기 떄문이다.
     *
     * 아래 어노테이션은 @Component를 포함하고 있어 Bean 등록이 됨
     * @Controller: 스프링 MVC 컨트롤러로 인식
     * @Service: 특벽한 의미 없음, 개발자들에게 핵심 비즈니스 로직이 포함되어 있는 비즈니스 계층을 인식시킴
     * @Repository: 스프링 데이터 접근 계층으로 인식. 데이터 계층의 예외를 스프링 예외로 변환
     * @Configuration: 스프링 설정 정보로 인식. 스프링 bean이 싱글톤을 유지시킴
     *
     *
     *
     **/

}
