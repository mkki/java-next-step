# 요구사항
이 저장소는 박재성님의 저서인 **자바 웹 프로그래밍 NEXT STEP** [예제 코드][https://github.com/slipp/web-application-server]의
구현 코드를 포함하고 있습니다.

### 요구사항 1 - http://localhost:8080/index.html로 접속시 응답
* `InputStreamReader`는 보조 스트림으로 파라미터로 사용된 바이트 기반 노드 스트림인 `inputStream`을
문자 기반 스트림으로 변환 시켜준다. 또한, 구현 코드에서 두 번째 파라미터로 인코딩 타입인 `UTF-8`을 가진다.
* `BufferedReader`도 보조 스트림으로 `InputStreamReader`에 버퍼 기능을 추가해준다.
* 로깅 라이브러리인 `SLF4J`는 5단계의 로그 레벨을 갖는다. 대표적으로 `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`가 있다.
* 로깅 메시지는 `log.debug("Port: {}", connection.getPort())`와 같은 형식으로 구현하며, 인자를 전달하는 비용을 줄여 성능을 높여준다.

### 요구사항 2 - get 방식으로 회원가입
* 

### 요구사항 3 - post 방식으로 회원가입
* 

### 요구사항 4 - redirect 방식으로 이동
* 

### 요구사항 5 - cookie
* 

### 요구사항 6 - stylesheet 적용
* 

### heroku 서버에 배포 후
* 