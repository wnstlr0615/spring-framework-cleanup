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


### EventPublisher
이벤트를 처리하는 객체로 
이벤트를 발생 시켜준다

**이벤트 처리**  
1. 이벤트 객체를 생성 후(MyEvent)
2. publishEvent(new MyEvent())메소드를 이용하여 이벤트 발생
3. EventHandler 를 생성하여 @EventListener 를 통하여 이벤트 를 받아 처리

###테스트
__package com.joon.springframeworkcleanup.ioc.evnetpulisher 참조__

### Resourece
리소스를 읽어오는 기능을 하는 인터페이스로  
파일 시스템 , 클래스 패스, URL, 상대/절대 경로 등으로 읽어 올 수 있다.
+ ClassPathXmlApplicationContext   -> ClassPathResource 
+ FileSystemXmlApplicationContext  -> FileSystemResource
+ WebApplicationContext            -> ServletContextResource

다음 과 같은 종류가 있으며 통상 classpath: 를 사용 하거나 file:///를 사용 하여 파일에 접근한다.
###테스트
__package com.joon.springframeworkcleanup.ioc.resourceloader 참조__

### Validator
객체 검증용 인터페이스로
객체에 올바른 값이 입력 되었는지 검사하는 기능을 제공해준다  
직접 등록할 경우 Validator를 구현 하여 구현해주면 된다.
 
Jpa에 사용 시 @NotNull, @Min, @Max, 등에 어노테이션을 사용하여 Validator 등록 할 수 있다.
 
모든 계층에서 사용 가능 하다라는 장점이 있다

__package com.joon.springframeworkcleanup.ioc.validation 참조__

### Converter,  Formatter

**Converter**  
 데이터 바인딩 추상화 객체 
특정 데이터 입력 시 원하는 데이터로 변환 해준다.
쓰레드-세이프하여 안전하게 사용할 수 있다.

**Formatter**
웹에 특화된 데이터 바인딩 추상화 객체로 다국어 기능을 지원한다.

<pre><code>
public class EventFormatter implements Formatter< Event >{}
public class EventConverter implements Converter< String , Event > {}
</code></pre>

컨버터와 포메터는 WebMvcConfigurer를 구현 하여 추가로 등록 할 수 있으나
빈으로 등록시 자동으로 등록된다.
<pre><code>
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) { 
        registry.addConverter(new EventConverter());
        WebMvcConfigurer.super.addFormatters(registry);
    }
}
</code></pre>

**ConversionService**
컨버터와 포메터를 관리하는 서비스로  등록된 컨버터와 포메터를 확인 할 수 있다.

###테스트
__package com.joon.springframeworkcleanup.converter 참조__

### SpringExpressionLanguage
스프링에서 사용 하는 언어로 메서드 호출과 문자열 템플릿 기능을 제공한다.
#{"표현식"}
${"프로퍼티"}
처럼 사용 할 수 있다
자세한 사항은 [래퍼런스](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions) 참조

<pre><code>
        ExpressionParser parser=new SpelExpressionParser();
        Expression expression = parser.parseExpression("2+100");
</code></pre>
ExpressionParser로 spel 을 읽어 올 수 있다.

**Spel 지원 기능**


+ 리터럴 표현식 (Literal Expression)
+ Boolean과 관계연산자 (Boolean and Relational Operator)
+ 정규 표현식 (Regular Expression)
+ 클래스 표현식 (Class Expression)
+ 프로퍼티, 배열, 리스트, 맵에 대한 접근 지원 (Accessing properties, arrays, lists, maps)
+ 메서드 호출 (Method Invocation)
+ 관계연산자 (Relational Operator)
+ 할당 (Assignment)
+ 생성자 호출 (Calling Constructors)
+ Bean 참조 (Bean References)
+ 배열 생성 (Array Contruction)
+ 인라인 리스트/맵 (Inline List/Map)
+ 삼항 연산자 (Ternary Operator)
+ 변수 (Variables)
+ 사용자 정의 함수 (User defined functions)
+ Collections Projection
+ Collections Selection
+ Templated expression


### AOP

관점 지향 프로그래밍(Aspect Oriented Programming)에 약자로 소스코드의 여기저기 흩어져있는 횐단 관심사를 중심으로 설계와 구현을 하는 프로그래밍 기법이다

**횡단관심사**

핵심 비즈니스에서 다소 벗어나있고 여러 모듈에서 공통적 반복적으로 나타나는 내용


### AOP 관련 용어
**Aspect**    : AOP의 단위가 되는 횡단 관심사에 해당  
**JoinPoint** : 횐단 관심사가 실행될 지점이나 시점  
**Advice**    : 특정 조인 포인트에서 실행되는 코드
**PointCut**  : 어드바이스를 적용할 곳을 나타내는 표현식


**자원유형**
+ Before          : 실행 전
+ After Returning : 실행포인트가 정상적으로 종료된 후 실행
+ After Throwing  : 예외가 발생했을 경우 실행
+ After : 조인 포이튼 처리가 완료된 후 실행
+ Around : 조인 포인트 전후에 실행

**의존성추가**
<pre><code>
    implementation 'org.springframework.boot:spring-boot-starter-aop'
</code></pre>
###테스트
__package com.joon.springframeworkcleanup.aop 참조__

