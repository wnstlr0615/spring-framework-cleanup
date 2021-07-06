# 스프링 프레임 워크 정리

해당 프로젝트는 스프링 프레임워크에 핵심기술들을 정리하기 위해 작성 되었으며 

백기선님의 [스프링 프레임 워크 핵심 기술]( https://www.inflearn.com/course/spring-framework_core/dashboard )을 기반으로 개인 정리용으로 작성

## 목차
+ IoC
  + BeanFactory
  + Autowired

## ApplicationContext 
스프링 애플리케이션 전반에 걸쳐 모든 구성요소의 제어 작업을 담당하는 IoC 엔진

### ApplicationContext 의 기능
+ 의존성 관리(BeanFactory)
+ 다국어 처리(MessageResource,  i18n)
+ 이벤트 처리(EventPublisher)
+ 리소스 로딩(Resource)
+ 프로퍼티 관리(Environment)


## IoC 컨테이너
자동으로 의존 관계를 설정 해주며 빈을 관리하는 컨테이너

### 빈
스프링 IoC가 관리하는 객체로 default 로 싱글 톤의 성격을 가지고 있다.

+ 빈 등록 시 장점
  +  의존성 관리를 IoC 컨테이너가 자동으로 해준다.
  +  라이프 사이클 인터페이스를 사용할 수 있다.
  +  스코프를 싱글톤으로 자동 설정(프로포토 타입으로 변경 가능)
    


이러한 기능 들을 관리하는  __ApplicationContext__ 는 스프링을 사용 시 필수 적으로 등록 해주어야 한다.

ApplicationContext 등록방법
+ AnnotationConfigApplicationContext() // 자바 코드를 통한 등록 방법
+ ClassPathXmlApplicationContext() // 미리 작성한 xml 파일을 이용한 방법(거의 사용x)

다음과 같은 방법들로 ApplicationContext 는 등록해주어 하지만 스프링 부트 사용 시 @SpringBootApplication 에
@ComponentScan 이 포함 되어 있어 Application 클래스가 포함된 하위 패키지에 어노테이션으로 등록한 빈들을 IoC 컨테이너에 등록해준다.

###  테스트
__package com.joon.springframeworkcleanup.ioc.beanfactory 참조__

## @Autowired
@Autowired 는 자동으로 클래스에 해당 객체를 주입해준다(빈으로 등록 된)


빈 등록x <br>
Book book=new Book();

빈 등록 <br>
@Autowired
Book book;

이렇게 빈으로 등록된 객체를 의존성 관리를 해주는데  주입 될 수 있는 빈이 두개 이상인 경우 
어떤 것을 등록해주어야하는지 설정 해주어야한다.

예시<br>
Book 인터페이스를 구현한 BookA와 BookB가 있을 경우<br>
<pre><code>
@Autowired
Book book;
</code></pre>


1. @Qualifier 사용 하는 경우
<pre><code>
@Qualifier("bookA")
    @Autowired
    Book book;
</code></pre>
2. @Primary를 사용하는 경우
<pre><code>
@Autowired
Book book;
----------------------------
@Component
@Primary
public class BookB implements Book{}
</code></pre>

다음과 같은 방법으로 동시에 주입될 수 있는 빈이 있는 경우 선택해 줄 수 있다.


@Autowired(required=false) 로 설정 시 오류 x 사용 시 오류
### 테스트
__package com.joon.springframeworkcleanup.ioc.autowired 참조__


### 빈 Scope

빈은 싱글톤과 프로토 타입으로 설정 가능하며 싱글톤 적용 시 등록 된 빈은 항상 같은 객체이며
프로토 타입으로 적용된 빈은 항상 다른 객체이다.

**프로토 타입 설정방법**
@Scope(value="prototype") 적용


싱글톤 객체에서 프로토 타입 객체를 생성 시 항상 같은 객체가 생성 되는 문제가 생긴다.

이 경우  @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
프록시 모드를 적용하여 해결

**싱글톤 객체 사용 시 주의점**
+ 프로퍼티가 공유됨
+ 초기 구동 시 생성됨

### 테스트
__package com.joon.springframeworkcleanup.ioc.scope 참조__



### Environment
프로파일과 프로퍼티를 관리하는 객체이다

ApplicationContext에 상속 되어 있으므로 getEnvironment로 받아 사용하거나
그냥 Autowired를 통해 빈주입

1. ApplicationContext를 통한 생성방법
<pre><code>
@Autowired
ApplicationContext context;
----------------------------------
Environment environment=context.getEnvironment();
</code></pre>
2. 바로 생성 방법
<pre><code>
@Autowired
Environment environment;

</code></pre>

###프로퍼티 

environment.getProperty(key) 메소드를 사용하여 등록된 값을 읽어 올 수 있다.

**프로 퍼티 추가 등록 방법** <br>
Application 클래스에 @PropertySource 를 통하여 등록 할 수 있다.
<pre><code>
@SpringBootApplication
@PropertySource("classpath:/app.properties")
public class SpringFrameworkCleanupApplication {...}
</code></pre>


### 프로파일 관리 

클래스나 메소드에 @Profile()를 등록하여 사용

프로파일로 등록하면 해당 프로파일명이 활성활 될 경우에만 빈으로 등록된다.

**프로파일 활성화방법**
vm옵션에 -Dspring.profiles.active="test" 등록
또는 구성편집에서 등록


###테스트
__package com.joon.springframeworkcleanup.ioc.environment 참조__


### MessageSource
Messages.properties에 등록된 프로퍼티로 메시지 다국화 기능 지원(i18n)

MessageSource 의 빈을 입력받아서 사용

###테스트
__package com.joon.springframeworkcleanup.ioc.messagesource 참조__


