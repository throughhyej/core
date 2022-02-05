package com.core.scan;

public class ConflictTest {
    /* bean은 클래스명으로 등록이 되는데, name키워드로 임의의 이름을 부여할 수 있다. 이는 수동 bean 등록이 된다.
     * 수동 bean과 자동 bean의 이름이 같아 충돌이 생길 경우, 수동 bean이 우선권을 가져 override 된다.
     * (Overriding bean definition for bean 'name' with a different definition: replacing)
     * 개발 시 문제를 발생시킬 확률이 크다.
     *
     * 따라서 현재 spring은 default로 중복된 이름을 가진 bean이 있으면 ConflictingBeanDefinitionException 에러를 발생시킨다.
     * (Consider renaming one of the beans or enabling overriding
     *  by setting spring.main.allow-bean-definition-overriding=true)
     **/
}
