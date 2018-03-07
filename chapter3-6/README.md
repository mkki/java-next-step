# 요구사항
이 저장소는 박재성님의 저서인 **자바 웹 프로그래밍 NEXT STEP** [예제 코드][https://github.com/slipp/web-application-server]의
구현 코드를 포함하고 있습니다.

### 요구사항 1 - http://localhost:8080/index.html로 접속시 응답
* `InputStreamReader`는 보조 스트림으로 파라미터로 사용된 바이트 기반 노드 스트림인 `inputStream`을
문자 기반 스트림으로 변환 시켜준다. 또한, 구현 코드에서 두 번째 파라미터로 인코딩 타입인 `UTF-8`을 가진다.
* `BufferedReader`도 보조 스트림으로 `InputStreamReader`에 버퍼 기능을 추가해준다.
* `DataOutputStream`은 자바 기본 자료형 데이터를 출력할 수 있다.
* 로깅 라이브러리인 `SLF4J`는 5단계의 로그 레벨을 갖는다. 대표적으로 `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`가 있다.
* 로깅 메시지는 `log.debug("Port: {}", connection.getPort())`와 같은 형식으로 구현하며, 인자를 전달하는 비용을 줄여 성능을 높여준다.

### 요구사항 2 - get 방식으로 회원가입
* 쿼리스트링이 url로 전달 `GET /user/create?userId=a&password=b&name=c&email=d@gmail.com HTTP/1.1`

### 요구사항 3 - post 방식으로 회원가입
* `POST /user/create HTTP/1.1` 쿼리스트링은 요청 본문에 저장되어 전달
Content-Length 값을 받아오고, 길이만큼 입력 스트림으로 받아온다.
GET은 서버에 존재하는 자원을 가져오는 것이고, POST는 서버에 요청을 보내 데이터 추가, 수정, 삭제 작업을 실행할 때 사용된다.

### 요구사항 4 - redirect 방식으로 이동
* `3XX` 리다이렉트 코드를 전송하는 별도의 메서드 구현, 리다이렉트시 요청/응답이 2번
* 

### 요구사항 5 - 로그인 구현
* 로그인 성공 시 `Set-Cookie` 헤더에 `logined=true` 값 적용, 이후 이 값으로 판별
* HTTP는 무상태(Stateless) 프로토콜이라 각 요청 간에 상태 데이터를 공유하지 못한다.

### 요구사항 6 - 사용자 리스트 출력
* 로그인 유무를 판단하기 위해 `isLogin()` 메서드 구현, 쿠키로 판단

### 요구사항 7 - stylesheet 적용
* 기존 애플리케이션은 모든 응답을 `text/html` 타입으로 보내서 `CSS` 파일이 동작하지 않았다.
* `Content-Type` 헤더 값을 `text/css`로 바꾸어 응답하여 이를 해결

### heroku 서버에 배포 후
* 