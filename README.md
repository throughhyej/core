# core
https://www.inflearn.com/course/스프링-핵심-원리-기본편


- ConcurrentHashMap  
    Multi Thread 환경에서 사용할 수 있게 생성된 클래스  
    get()에는 synchronized 키워드 존재 X, put()에는 synchronized 키워드 존재 O  
    읽기 작업에는 여러 쓰레드가 동시에 읽을 수 있지만 쓰기 작업에는 특정 세그먼트나 버킷에 대해 lock 사용  
      
      
- static import  
    상단에 import를 하면 코드 작성 시 패키지명을 생략할 수 있는데,  
    이처럼 import static 키워드를 통해 코드 내에서 해당 static 멤버를 간편하게 사용할 수 있다.  
      
      
- final  
    무조건 값이 할당되어야 하는 키워드 (대입하거나 생성자를 통한 할당)  
      
      
- 프레임워크 vs 라이브러리  
    - 프레임워크: 내가 작성한 코드를 프레임워크가 대신 실행하고 제어한다.  
    - 라이브러리: 내가 작성한 코드 내에서 코드로 흐름을 주도하고 담당한다.  
      
      
- Assertions.isEqualsTo() vs Assertions.isSameAs()  
    equal: 기본형은 객체가 아닌 변수이기 때문에 기본형에 대해서는  사용 불가, 결과값 자체를 비교  
    same: == 메모리 위치 비교 (참조값)
      
      
- threadLocal  
    thread의 지역변수. 오직 한 thread에 의해 읽고 쓰일 수 있는 변수를 생성하는 자바 클래스
        
      
      
#
** 비즈니스 로직에서 @Autowired Repository를 추가하여 개발하는 경우가 있는데,  
** 생성자 주입 방식 변경한다면.. 추가될 때마다 생성자 코드를 수정하는 방법 밖에 없을까  
** @Autowired Repository 추가 방식은 뭐가 잘못되어서 이렇게 사용하고 있었던 거지  
** 각기 다른 Repository를 DI하는 현 프로젝트 로직은 설계가 잘못된 건가?   
** 일반 메서드 주입 방식으로 했어야 했나? **  
  
-> 만약 생성자 주입 방식으로 변경한다면, 추가될 때마다 생성자 코드 수정하기 귀찮으니까  
	 lombok의 @RequiredArgsConstructor 사용 (final 이 붙은 멤버변수에 대해 생성자 자동 생성)  
-> 근본적으로 설계가 잘못...? 다른 시스템은 어떻게 설계하는지 궁금하다.  
-> 해당 비즈니스 로직과 관계가 없는 로직에 대해서는 수정자 주입 방식 사용

