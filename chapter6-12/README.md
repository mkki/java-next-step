이 저장소는 박재성님의 저서인 **자바 웹 프로그래밍 NEXT STEP** [예제 코드](https://github.com/slipp/web-application-server)의
구현 코드를 포함하고 있습니다.

# Servlet/JSP, Servlet Container를 활용한 동적 웹 애플리케이션 개발
* `Servlet`이란, 앞서 **chapter3-6**에서 구현한 웹 서버의 `Controller`, `HttpServletRequest`, `HttpServletResponse`를 추상화한 인터페이스 표준이다.
다시 말해, `HTTP`의 클라이언트 요청과 응답에 대한 표준을 정해놓은 것이다.
* `Servlet Container`는 이 서블릿 표준에 대한 **구현**을 담당하고 있으며, 앞서 구현한 웹 서버가 이 역할을 한다고 볼 수 있다.
* HTTP 웹 서버는 서버 시작 시점에서 `Controller Instance`를 생성하고 `요청 URL`과 매핑시킨다. 
이후, 클라이언트 요청이 오면 `요청 URL`에 해당하는 `Controller(Servlet)`를 찾아 작업을 위임한다.
* `Tomcat`, `Jetty`, `JBoss` 는 서블릿을 지원하는 **서블릿 컨테이너 구현체**이다.
* `JSP`는 정적인 `HTML`은 그대로 두고 동적으로 변경되는 부분만 `JSTL`, `EL`을 활용해 자바 구문을 구현한다.

## 실습
### 요구사항 1. 개인정보 수정 기능
* `UpdateUserServlet`에 개인정보 수정 기능을 추가한다.
* 구현에 앞서 파라미터 값으로 받은 `userId`가 유효한지 검사해줘야 한다. 이때 `Session`이 활용된다.

### 요구사항2. 로그인/로그아웃 기능
* `LoginServlet/LogoutServlet`에 로그인/로그아웃 기능을 추가한다.
* `DB`에 존재하는 값이면 세션에 저장 후 리다이렉트하고, 아니면 요청 파라미터에 `loginedFailed` 값을 전송한다.
* 메뉴는 `loginedFailed` 값에 따라 동적으로 변화하도록 `JSTL` if/else 분기문 처리 해준다.

### 요구사항2. 회원정보 조회 보안 강화
* 세션 데이터를 가져와 회원이 아니면 `login` 페이지로 리다이렉트한다.