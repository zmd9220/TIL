# 오전



## 어제 과제 풀이 및 복습



- [addattribute](https://www.google.com/search?q=addattribute+%ED%95%98%EB%82%98%EB%A7%8C&sca_esv=cc91aa7b516a412e&ei=2SgIaJ6cGdG3vr0PzYjYGA&ved=0ahUKEwje7L2K6OyMAxXRm68BHU0EFgMQ4dUDCBA&uact=5&oq=addattribute+%ED%95%98%EB%82%98%EB%A7%8C&gs_lp=Egxnd3Mtd2l6LXNlcnAiFmFkZGF0dHJpYnV0ZSDtlZjrgpjrp4wyChAAGLADGNYEGEcyChAAGLADGNYEGEcyChAAGLADGNYEGEcyChAAGLADGNYEGEcyChAAGLADGNYEGEcyChAAGLADGNYEGEcyChAAGLADGNYEGEcyChAAGLADGNYEGEcyChAAGLADGNYEGEcyChAAGLADGNYEGEdIgAJQAFgAcAF4AZABAJgBAKABAKoBALgBA8gBAJgCAaACB5gDAIgGAZAGCpIHATGgBwCyBwC4BwA&sclient=gws-wiz-serp)

- [빈 여러개](https://www.google.com/search?q=%EB%B9%88+%EC%97%AC%EB%9F%AC%EA%B0%9C&oq=%EB%B9%88+%EC%97%AC%EB%9F%AC%EA%B0%9C&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIGCAEQRRg90gEHMzQ1ajBqOagCALACAQ&sourceid=chrome&ie=UTF-8)

- [[JPA] hibernate의 ddl-auto 속성의 종류와 주의해야할 점](https://colabear754.tistory.com/136)

- [Spring Boot SQL 보기 옵션 총 정리](https://lannstark.tistory.com/14)

- [스프링 의존성 주입이 의존성을 낮추는 이유](https://www.google.com/search?q=%EC%8A%A4%ED%94%84%EB%A7%81+%EC%9D%98%EC%A1%B4%EC%84%B1+%EC%A3%BC%EC%9E%85%EC%9D%B4+%EC%9D%98%EC%A1%B4%EC%84%B1%EC%9D%84+%EB%82%AE%EC%B6%94%EB%8A%94+%EC%9D%B4%EC%9C%A0&sca_esv=6a2525f9667c9ba5&ei=OCUHaI3jONOp2roPzdC7mQU&ved=0ahUKEwiN39-98OqMAxXTlFYBHU3oLlMQ4dUDCBI&uact=5&oq=%EC%8A%A4%ED%94%84%EB%A7%81+%EC%9D%98%EC%A1%B4%EC%84%B1+%EC%A3%BC%EC%9E%85%EC%9D%B4+%EC%9D%98%EC%A1%B4%EC%84%B1%EC%9D%84+%EB%82%AE%EC%B6%94%EB%8A%94+%EC%9D%B4%EC%9C%A0&gs_lp=Egxnd3Mtd2l6LXNlcnAiO-yKpO2UhOungSDsnZjsobTshLEg7KO87J6F7J20IOydmOyhtOyEseydhCDrgq7stpTripQg7J207JygMgUQABjvBTIFEAAY7wUyCBAAGIAEGKIEMggQABiiBBiJBTIIEAAYogQYiQVIsCtQhgxYlCpwCngAkAEDmAGkAaAB3yOqAQQwLjM3uAEDyAEA-AEBmAIcoAKEEsICChAAGLADGNYEGEfCAgUQABiABMICBBAAGB7CAgYQABgIGB7CAgYQABgFGB7CAgcQABiABBgNwgIGEAAYDRgewgIIEAAYCBgNGB7CAggQABgFGA0YHsICBRAhGKABmAMAiAYBkAYKkgcFMTAuMTigB96oAbIHBDAuMTi4B-QR&sclient=gws-wiz-serp)

- [@autowired 생성자](https://www.google.com/search?q=@autowired+%EC%83%9D%EC%84%B1%EC%9E%90&sca_esv=ad45493ffd201e24&ei=ousGaIvSOeek2roP2Z27uQ0&start=10&sa=N&sstk=Af40H4Uf-XPnFCqkGMQ6-o6qUXKSP1IHFMPbbr07i-84BG2k3mB6rTMN7ktKusCoiYEqsPFk7aWAHx9ujWr9If6CW9xGyhjjrrhukQ&ved=2ahUKEwiLq5zIueqMAxVnklYBHdnOLtcQ8tMDegQIJhAE&biw=929&bih=865&dpr=1)



- 과제에서 어려웠던 점 (JS 활용이 어려웠음-각 기능 클릭마다 링크 변경하여 iframe에 적용, CSS 적용이 어려웠음)



- RESTful API 개념 재정리 (자원-resource(핵심 데이터), 행위-(get,post,put,delete))
  - URL은 명사(자원), 동작은 메소드(http)



- RestController (API 통신으로 json 데이터만 넘겨주겠다)
- Controller - 데이터



#### @controller 랑 @restconroller의 차이는 뭐야?

`@Controller`와 `@RestController`는 모두 Spring에서 컨트롤러 역할을 수행하지만, 동작 방식과 주된 용도가 약간 다릅니다. 차이를 간단히 정리하면 다음과 같습니다:

1. **기본 목적**



- `@Controller`: 주로 **뷰를 반환**하기 위해 사용됩니다. 템플릿 엔진(Thymeleaf, JSP 등)과 함께 사용되어 HTML이나 다른 뷰를 생성합니다.
- `@RestController`: **RESTful 웹 서비스**를 위한 컨트롤러로, JSON, XML 등 데이터를 반환합니다. 주로 API 서버 개발에 사용됩니다.



2. **내부 구현 차이**



- `@Controller`: 뷰 리졸버(View Resolver)를 통해 반환값을 뷰 이름으로 처리합니다. 즉, 리턴 값이 HTML이나 JSP 같은 뷰의 경로로 해석됩니다.

  

  ```java
  @Controller
  public class ViewController {
      @GetMapping("/home")
      public String home() {
          return "home"; // "home.html" 같은 뷰 이름 반환
      }
  }
  ```

  

- `@RestController`: `@Controller`에 `@ResponseBody`가 추가된 것입니다. 즉, 메서드의 반환값이 그대로 HTTP 응답 본문(body)에 작성됩니다.

  

  ```java
  @RestController
  public class RestApiController {
      @GetMapping("/api")
      public String api() {
          return "Hello, REST!"; // 본문에 "Hello, REST!" 출력
      }
  }
  ```



3. **사용 방식**



- **`@Controller`**는 뷰 템플릿을 사용하는 MVC 애플리케이션에 적합합니다.
- **`@RestController`**는 데이터(JSON/XML)만 제공하는 RESTful API 애플리케이션에 적합합니다.



요약



- **HTML 뷰 반환** → `@Controller` 사용
- **JSON/XML 반환** → `@RestController` 사용



필요한 상황에 따라 적합한 어노테이션을 선택하면 됩니다. 혹시 더 궁금한 점이 있으면 알려주세요! 



알겠습니다! 이전에 작성했던 내용을 포함해서, `XML 메시지 컨버터 설정`과 `Controller에서 XML 데이터 처리` 부분을 Markdown 형식으로 전체적으로 정리해드릴게요.

------

# Spring RESTful API에서 XML 처리 방법

스프링에서 RESTful API를 유지하면서 XML 데이터를 처리하려면 몇 가지 설정과 구현이 필요합니다.

---

## 1. XML 데이터 바인딩을 위한 라이브러리 추가
XML 데이터를 처리하려면 `Jackson`의 XML 모듈을 사용해야 합니다.
```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
    <version>2.15.0</version>
</dependency>
```



------

## 2. XML 메시지 컨버터 설정

`MappingJackson2XmlHttpMessageConverter`를 사용하여 XML 데이터를 처리하도록 Spring에 메시지 컨버터를 추가합니다.

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2XmlHttpMessageConverter());
    }
}
```

------

## 3. Controller에서 XML 데이터 처리

Spring REST 컨트롤러는 XML 데이터도 `@RequestBody`와 `@ResponseBody`를 통해 받을 수 있습니다.

```java
@RestController
@RequestMapping("/api")
public class ExampleController {
    @PostMapping(value = "/xml", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public MyXmlObject handleXmlData(@RequestBody MyXmlObject xmlObject) {
        return xmlObject;
    }
}
```

------

# @Controller와 @RestController의 차이점

Spring에서 `@Controller`와 `@RestController`의 주요 차이점은 다음과 같습니다:

| **어노테이션**    | **기능 및 목적**                                             |
| ----------------- | ------------------------------------------------------------ |
| `@Controller`     | **뷰 반환** - HTML 템플릿 엔진(Thymeleaf, JSP 등)과 함께 사용됩니다. |
| `@RestController` | **데이터 반환** - JSON/XML 데이터 반환에 적합하며 RESTful API 개발에 사용됩니다. |

------

# JSP와 Thymeleaf 템플릿 엔진의 차이점

Spring에서 서버 사이드 렌더링에 사용되는 템플릿 엔진인 JSP와 Thymeleaf의 차이점은 다음과 같습니다:

| **기준**             | **JSP**                        | **Thymeleaf**              |
| -------------------- | ------------------------------ | -------------------------- |
| **HTML 형식**        | Java 코드 포함 가능            | 순수 HTML 유지 가능        |
| **사용성**           | Java EE와 강하게 연결          | Spring과 자연스러운 통합   |
| **템플릿 처리 방식** | 런타임에서 처리                | 서버에서 미리 처리 후 반환 |
| **가독성/유지보수**  | 복잡한 코드로 어려움 발생 가능 | 깔끔한 HTML 구조 유지      |

------

# 기존 Spring 프로젝트에 Spring Boot 도입하기

Spring 레거시 프로젝트에 Spring Boot를 추가하는 단계는 다음과 같습니다:

## 주요 단계

1. **Spring Boot 의존성 추가**

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter</artifactId>
       <version>2.x.x</version>
   </dependency>
   ```

2. **메인 클래스 수정**

   ```java
   @SpringBootApplication
   public class MySpringBootApplication {
       public static void main(String[] args) {
           SpringApplication.run(MySpringBootApplication.class, args);
       }
   }
   ```

3. **설정 파일 변경** (`application.properties` 또는 `application.yml`로 변경)

   ```yaml
   server:
     port: 8080
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/mydb
       username: root
       password: secret
   ```

4. **기존 설정 점검 및 리팩토링**

   - `web.xml` 제거.
   - 기존 Bean 설정을 `@Configuration`으로 전환.

------

# `res`와 `data`의 차이 (Ajax 통신에서)

JavaScript `fetch`를 사용할 때 `res`와 `data`의 차이점은 다음과 같습니다:

| **변수** | **역할**                                        |
| -------- | ----------------------------------------------- |
| `res`    | HTTP 응답 객체(헤더, 상태코드 포함).            |
| `data`   | `res.json()`으로 변환된 JSON 데이터(본문 내용). |

------

## 코드 흐름 예제

```js
fetch("/api/persons")
    .then(res => res.json()) // JSON 객체로 변환
    .then(data => console.log(data)); // 변환된 데이터를 출력
```

------

이제 전체 내용이 Markdown 형식으로 잘 정리되었을 거예요. 복사해서 `.md` 파일에 붙여넣으시면 됩니다. 혹시 문제가 지속되거나 더 필요한 부분이 있다면 말씀해 주세요! 😊

```

```



## 성능 최적화

- CSR을 통한 서버 부하 감소(서버 경량화) - ajax
- JPA를 통한 쿼리 단순화 및 유지보수 용이 (간단한 쿼리)
- Redis - 응답속도 향상, 서버 부하 분산
- k6, JMeter - 부하 테스트 / 응답시간 분석





## RESTful API 어제에 이어서

- /api/persons/{id} 추가
- ![image-20250423103548884](API성능최적화_3일차.assets/image-20250423103548884.png)





## JPA 성능 최적화

- fetch join
- ![image-20250423112537055](API성능최적화_3일차.assets/image-20250423112537055.png)



- 외래키, join 구조 추가

- ```java
  package com.example.resttest.model;
  
  import java.util.List;
  
  import jakarta.persistence.Entity;
  import jakarta.persistence.GeneratedValue;
  import jakarta.persistence.GenerationType;
  import jakarta.persistence.Id;
  import jakarta.persistence.ManyToOne;
  import jakarta.persistence.Table;
  import lombok.AllArgsConstructor;
  import lombok.Getter;
  import lombok.NoArgsConstructor;
  import lombok.Setter;
  
  @Entity
  @Table(name="orders") // Order 테이블은 중복될 수 있어서 이름 바꿈
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public class Order {
  	// 행을 구분하는 PK 반드시 필요 (테이블 생성을 위해) 별도 구분자가 없을 경우 row 갯수 마다 자동 증가하는 ID를 키로 쓰면 됨	
  	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;
  	
  	private String productName;
  	private double price;
  	
  	@ManyToOne // 여러 주문은 한 사람에게 적용 될 수 있음
  	private Person person;
  }
  ```

- ```java
  package com.example.resttest.model;
  
  import java.util.ArrayList;
  import java.util.List;
  
  import jakarta.persistence.Entity;
  import jakarta.persistence.GeneratedValue;
  import jakarta.persistence.GenerationType;
  import jakarta.persistence.Id;
  import jakarta.persistence.OneToMany;
  import lombok.AllArgsConstructor;
  import lombok.Getter;
  import lombok.NoArgsConstructor;
  import lombok.Setter;
  
  // Entity 테이블과 바로 매핑 (없으면 자동 생성)
  @Entity
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public class Person {
  	// 행을 구분하는 PK 반드시 필요 (테이블 생성을 위해) 별도 구분자가 없을 경우 row 갯수 마다 자동 증가하는 ID를 키로 쓰면 됨	
  	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;
  	
  	private String name;
  	private int age;
  	
  	@OneToMany(mappedBy = "person") // 1대다 관계 사람 한 명에 여러 개 주문이 있을 수 있음
  	private List<Order> orders = new ArrayList<Order>();
  }
  ```

- 지연 로딩

- ![image-20250423113919591](API성능최적화_3일차.assets/image-20250423113919591.png)



- N+1문제
- ![image-20250423113932493](API성능최적화_3일차.assets/image-20250423113932493.png)
- ![image-20250423113954540](API성능최적화_3일차.assets/image-20250423113954540.png)
- ![image-20250423114055341](API성능최적화_3일차.assets/image-20250423114055341.png)
- ![image-20250423114102082](API성능최적화_3일차.assets/image-20250423114102082.png)

![스크린샷 2025-04-22 111558](API성능최적화_3일차.assets/스크린샷 2025-04-22 111558.png)

![스크린샷 2025-04-22 150917](API성능최적화_3일차.assets/스크린샷 2025-04-22 150917.png)

![스크린샷 2025-04-22 161613](API성능최적화_3일차.assets/스크린샷 2025-04-22 161613.png)

![스크린샷 2025-04-22 161859](API성능최적화_3일차.assets/스크린샷 2025-04-22 161859.png)

![스크린샷 2025-04-22 164735](API성능최적화_3일차.assets/스크린샷 2025-04-22 164735.png)

![스크린샷 2025-04-23 103548](API성능최적화_3일차.assets/스크린샷 2025-04-23 103548.png)

![스크린샷 2025-04-23 112535](API성능최적화_3일차.assets/스크린샷 2025-04-23 112535.png)

![스크린샷 2025-04-23 113916](API성능최적화_3일차.assets/스크린샷 2025-04-23 113916.png)

![스크린샷 2025-04-23 113931](API성능최적화_3일차.assets/스크린샷 2025-04-23 113931-1745382626696-7.png)

![스크린샷 2025-04-23 113947](API성능최적화_3일차.assets/스크린샷 2025-04-23 113947-1745382626696-8.png)

![스크린샷 2025-04-23 114050](API성능최적화_3일차.assets/스크린샷 2025-04-23 114050-1745382626696-9.png)

![스크린샷 2025-04-23 114059](API성능최적화_3일차.assets/스크린샷 2025-04-23 114059-1745382626696-10.png)

![스크린샷 2025-04-22 103155](API성능최적화_3일차.assets/스크린샷 2025-04-22 103155.png)

![스크린샷 2025-04-22 103546](API성능최적화_3일차.assets/스크린샷 2025-04-22 103546.png)

![스크린샷 2025-04-22 103812](API성능최적화_3일차.assets/스크린샷 2025-04-22 103812.png)

![스크린샷 2025-04-22 104900](API성능최적화_3일차.assets/스크린샷 2025-04-22 104900.png)





# 오후



- 