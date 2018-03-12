이 저장소는 박재성님의 저서인 **자바 웹 프로그래밍 NEXT STEP** [예제 코드][https://github.com/slipp/web-application-server]의
구현 코드를 포함하고 있습니다.

# 요구사항
### 요구사항 1 - http://localhost:8080/index.html로 접속시 응답
* 응답 본문에 출력 스트림으로 `./webapp/index.html`을 추가하여 보낸다.
* `InputStreamReader`는 보조 스트림으로 파라미터로 사용된 바이트 기반 노드 스트림인 `inputStream`을
문자 기반 스트림으로 변환 시켜준다. 또한, 구현 코드에서 두 번째 파라미터로 인코딩 타입인 `UTF-8`을 가진다.
* `BufferedReader`도 보조 스트림으로 `InputStreamReader`에 버퍼 기능을 추가해준다.
* `DataOutputStream`은 자바 기본 자료형 데이터를 출력할 수 있다.
* 로깅 라이브러리인 `SLF4J`는 5단계의 로그 레벨을 갖는다. 대표적으로 `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`가 있다.
* 로깅 메시지는 `log.debug("Port: {}", connection.getPort())`와 같은 형식으로 구현하며, 인자를 전달하는 비용을 줄여 성능을 높여준다.

### 요구사항 2 - get 방식으로 회원가입
* `GET` 메서드로 요청을 전송하면 HTTP 메시지 Request Line은 
`GET /user/create?userId=a&password=b&name=c&email=d@gmail.com HTTP/1.1`와 같은 형식이다.
* 전달된 요청 URL을 초기에 구현된 Util API로 쿼리스트링을 파싱하여 `User` 객체로 저장한다.

### 요구사항 3 - post 방식으로 회원가입
* `POST` 메서드로 전달된 요청 라인은 `POST /user/create HTTP/1.1`와 같은 형식이다.
* `GET` 메서드와 다르게 쿼리스트링은 요청 본문에 저장되어 전달된다.
* `GET`은 서버에 존재하는 자원을 가져오는 것이고, `POST`는 서버에 요청을 보내 데이터 추가, 수정, 삭제 작업을 실행할 때 사용된다.
* 요청 라인 이후 이어지는 요청 헤더에서 `Content-Length` 값을 찾은 후, 길이 만큼의 데이터를 입력 스트림으로 받아온다.

### 요구사항 4 - redirect 방식으로 이동
* `3XX` 리다이렉트 코드를 전송하는 `response302Header()` 메서드를 구현한다.
* 리다이렉트시 요청과 응답이 2번씩 발생한다.

### 요구사항 5 - 로그인 구현
* `HTTP`는 무상태(Stateless) 프로토콜이라 각 요청 간에 상태 데이터를 공유하지 못한다. 
이를 보완하고자 `Cookie`를 사용하여 로그인을 구현한다.
* 로그인 성공 시 `Set-Cookie` 헤더에 `logined=true` 값을 담아 전송하는 `response302LoginSuccessHeader()` 메서드를 구현한다.
* `Set-Cookie` 헤더는 다음 요구사항에서 로그인 유무를 판단하기 위해 사용된다.


### 요구사항 6 - 사용자 리스트 출력
* 로그인 유무를 판단하기 위해 `isLogin()` 메서드 구현한다.
* 요청 라인 URL이 `/user/list`와 일치하면 모든 사용자의 리스트를 응답 본문에 담는다.

### 요구사항 7 - stylesheet 적용
* 기존 애플리케이션은 모든 컨텐츠를 `text/html` 타입으로 보내서 `CSS` 파일이 동작하지 않았다.
* 요청 라인 URL이 `.css`로 끝나면 `Content-Type` 헤더 값을 `text/css`로 바꾸어 응답한다.

# 리팩토링
리팩토링에서 중요한 점은 로직의 복잡도를 최대한 줄이는 것이다. 
생성한 객체를 최대한 사용하고, 복잡도와 책임이 늘어날 때 새로운 객체를 추가하는 연습이 필요하다.

### 요구사항 1. 요청과 응답 데이터 처리 로직을 별도의 클래스로 분리
1. 요청 로직 분리
* 요청 데이터를 담고 있는 `InputStream`을 생성자로 받아 메서드, URL, 헤더, 본문을 분리하는
`HttpRequest` 클래스를 만든다.
* 요청 라인을 처리하는 `processRequestLine()` 메서드를 `RequestLine` 클래스로 분리하여 복잡도를 줄인다.
* `GET`, `POST` 상수 값을 `enum`으로 리팩토링 한다.
> 독립적인 상수 값이 아닌 연관성을 가지는 상수는 enum을 사용하기 적합하다.  
* 이동욱님의 [Java Enum 활용기][http://woowabros.github.io/tools/2017/07/10/java-enum-uses.html]에서
보다 많은 예시를 학습할 수 있었다.

2. 응답 로직 분리
* `RequestHandler` 클래스의 응답 데이터 처리 중복을 줄이고자 `HttpResponse` 클래스를 생성해 응답 데이터 처리를 위임한다.
* `HTML`, `CSS`, `JavaScript` 파일을 직접 읽어 처리하는 `forward()` 메서드, 특정 URL로 리다이렉트하는 `sendRedirect()` 메서드를 구현한다.
* 응답 헤더 정보는 `Map<String, String> headers`에 저장되며, `processHeaders()` 메서드로 출력스트림에 적용시킨다.
