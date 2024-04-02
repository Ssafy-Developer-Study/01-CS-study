# 𝒜𝓊𝓉𝒽𝑒𝓃𝓉𝒾𝒸𝒶𝓉𝒾𝑜𝓃

## 출처

### [Auth - simulacre(Velog)](https://velog.io/@parkirae/series/Auth)

### [Servlet Authentication Filter](https://www.javatpoint.com/authentication-filter)

### [Spring Security (Velog)](https://velog.io/@dh1010a/Spring-Spring-Security를-이용한-로그인-구현-스프링부트-3.X-버전-1)

# 1. 인증의 기본 개념 이해하기

## Basic auth

> 상태가 없는(Stateless) 웹 애플리케이션에서 인증을 구현할 수 있는 방법은 무엇일까요? 가장 단순한 방법은 모든 HTTP Request에 ID와 PW를 같이 보내는 것입니다.

- Basic Auth는 최초 로그인 후, `request header`에 Base64 인코딩을 해서 문자열을 보낸다
  - ID와 PW를 column`:`으로 이어붙이고 Base64로 인코딩 한 후 Basic 문자열을 붙여서 내보냄

```
Authorization: Basic dlrjsdPtldlqslek==
```

서버의 동작

1. 해당 요청을 수신하면 Base64로 인코딩된 문자열을 디코딩
2. DB의 레코드와 비교
3. 일치하면 요청 수행, 아니면 거부.

### 단점

1. MITM(Manipulator in the Middle Attack)에 취약: `ID와 PW가 탈취됨`

- HTTP에서 사용 불가, HTTPS에서만 사용 가능

2. 로그아웃 기능 제공 불가

- ID와 PW를 들고 있는 세션은 항상 로그인 됨

4. DB 부담 증가

- 매번 비교하기 때문에 과부화 확률이 올라감

5. 인증 서버가 단일 장애점 (Single Point of Failure)이 됨

- 이거 하나만 망가져도 전체 시스템이 작동 불가가 됨

## Bearer auth

> 최초 로그인 시 서버가 만들어 줍니다. 서버가 토큰을 만들어 클라이언트에게 반환하면 클라이언트는 이후 요청에 아이디와 비밀번호 대신 토큰을 넘겨 자신의 인증 여부를 증명합니다. 토큰 기반 인증은 Request Header에 <TOKEN>을 명시합니다. Bearer Token은 아래처럼 생겼습니다.

```
Authorization: Bearer qpdjfjxhRmsRKwldhkTekdlwprhewpdltmsxhzmsdmfgkftndlTek
```

### 장점

1. Basic Auth에 비해 안정성 ↑
   Basic Auth는 ID와 PW를 모든 요청 헤더에 넣어 전달합니다. 물론 Bearer Auth도 모든 요청 헤더에 넣어 전달하는데 보안 측면에서 조금 더 안전합니다.

2. 유효기간 관리
   Basic Auth는 유효기간을 정할 수 없지만, Bearer Auth는 관리 가능. -> `모든 디바이스 로그아웃` 기능 구현 가능

### 단점

1. DB 과부화 이슈 여전히 남아있음

- 여전히 DB에서 대조한다

6. 인증 서버가 단일 장애점 (Single Point of Failure)이 됨

- 여전히 해결되지 않았다

## JWT `Json Web Token`

- 스케일 문제를 해결할 수 어렵다는 단점 해결

```
Authorization: Bearer asdfgqwerty. qpdjfjxhRmsRKwldhkTekdlwprhewpdltmsxhzmsdmfgkftndlTek.qqqqqqqqqqqqqqqq.
```

- JWT는 **전자 서명 (Digital Signature)된** 토큰

  - JSON 형태로 이뤄져 있음
  - {header}, {payload}, {signature}로 구성

- 전자 서명이 뭔데?

  - 서명하고 싶은 메시지를 해싱하고 개인키로 암호화 했을 때 나오는 값

- ## JWT디코딩

```javascript
{ // {header}
  "typ": "JWT",
  "alg": "HS512"
},
{ // {payload}
  "sub": "546546574869465465",
  "iss": "demo app",
  "iat": "54564654",
  "exp": "54564654"
}.
qqqqqqqqqqqqqqqq
// {signature}
```

- header - `typ: 토큰 타입(JWT)`, `alg: 해싱 알고리즘`
- Payload - `sub - subject 토큰의 주인`, `iss - issuer 발행 주체`, `iat - issued at 발행 시간`, `exp - expire 만료 시간`
- signature - 유효성 검사에 사용

### JWT 인증 방식

![image](https://gist.github.com/assets/39848764/dc32b1ec-01e2-46cd-bc05-2c71033e568e)

1. 클라이언트 - 로그인 요청
2. 서버 - 유저 정보 받아서 `header`, `payload` 작성
3. 서버 - `2`의 값을 secret key로 전자 서명
4. 서버 - header, payload를 base64로 인코딩, 클라이언트에 반환
5. 클라이언트 - 재요청 (`4`에서 받은 토큰 들고있음)
6. 서버 - 디코딩한 JSON을 `header, payload, signature`로 나눔
7. 서버 - header, payload, secret key로 전자서명 생성
8. 서버 - 방금 만든 서명과 signatrue를 비교해 유효성 검사
9. {signature}가 일치한다면 위조된 토큰이 아님 -> 유효성 검사 끝냄

## OAuth 2.0

### 용어 정리

- 서드파티 / 클라이언트
  - 소셜 로그인을 사용하려는 애플리케이션 (ex. 내가 만든 사이트라던가)
- 리소스
  - 서드파티 애플리케이션이 접근하려고 하는 자원 (구글 계정? or auth service)
- 리소스 오너
  - 리소스를 가지고 있는 것 (사용자)
- 리소스 서버
  - 리소스를 가지는 서버
- 인가 서버
  - 인가만 담당하는 서버 (인가 서버와 리소스 서버는 다를수도, 같을 수도 있음)
- 제한된 액세스
  - 서드파티 애플리케이션이 가지는 `리소스 오너가 허락한 리소스에만 접근할 수 있도록 하겠다`는 뜻의 속성

### 플로우

![image](https://gist.github.com/assets/39848764/acb58444-6a69-460d-aa36-984593199b71)

1. 사용자가 소셜 로그인 하기 요청을 보냅니다.
2. 백엔드 서버가 요청을 받습니다.
3. 백엔드 서버는 해당 요청을 인가 페이지로 리다이렉트합니다.
4. 사용자가 인가 페이지를 보게 됩니다.
5. 사용자가 소셜 로그인을 요청을 보냅니다.
6. 사용자가 인가 서버에 로그인됩니다.
7. 인가 서버가 접근 권한을 부여하는 페이지로 리다이렉트합니다.
8. 사용자가 접근 인가 버튼을 클릭합니다.
9. 인가 서버가 사용자를 서드 파티 애플리케이션(콜백 URL)으로 리다이렉트합니다.
10. 서드 파티 애플리케이션은 인가 서버를 받은 토큰을 사용자에게 전달합니다.
11. 토큰을 받은 사용자는 토큰을 이용해 사용자의 정보에 접근합니다.

> 서드 파티 애플리케이션은 인가 페이지와 서드 파티 애플리케이션 페이지(콜백 URL)를 인가 서버에 알려주어야 합니다.

# 2. 서블릿에서의 적용 이해하기

> "서블릿에서 어떻게 해야 하는지 알아야 스프링에서 감동이 생긴다" - 걍 내 생각

-

# 3. 스프링에서는 어떻게 사용하나

> 지금 당장 스프링 공부할 건 아니니까 대충 느낌만 보자구요?
