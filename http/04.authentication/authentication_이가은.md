# [0403_study] 인증

생성일: 2024년 4월 3일 오후 1:43
Course: study
Keyword: Authorization

## 1️⃣ 인증 or 인가

### 인증? 인가?

- 사용자가 계정과 비밀번호를 입력해서 로그인
- **⇒ 인증이 적용된 개념**

- 인증을 마친 사용자는 게시글을 추가하거나, 수정하거나, 삭제할 수 있음
- 하지만, 다른 사람의 글을 수정하거나 삭제하는 것은 불가능
- **⇒ 인가가 적용된 개념**

### 인증, 인가의 구현 전 알아야 할 사항

- 클라이언트와 서버는 HTTP라는 라는 통신 규약에 의거하여 요청과 응답을 주고 받음
- http는 stateless(무상태성) 이라는 특징을 가짐
- 이는 HTTP로 통신되는 클라이언트의 요청이 서버에 **독립적**으로 처리되고,
  서버가 클라이언트에 대한 정보를 **유지하거나 기억하지 않는다**는 것을 의미 - _따라서 다음과 같은 상황이 발생할 수 있음_ - “안녕 난 가은” ”가은 반가워!” (응답)  
   = 인증이 성공한 응답을 보내줌 - “내 글 삭제할래”
  = 인증이 되었으니 인가가 필요한 절차인 글 삭제 요청 - “너 누구인데?” 마치 치매가 걸린 것 같은 응답
  = 서버는 클라이언트의 이전의 요청에 대한 상태를 전혀 관리하지 않고 있음, 사용자가 누구인지 모름
  ⇒ 인증이 유지되지 않고 다시 인증을 해야만 하는 상황 발생

### 그럼 어떻게 인증을 유지할 수 있을까? 👇🏻

## 2️⃣ 세션 vs 토큰

### 인증을 유지하기 위한 방법: 세션, 토큰

- 세션
  : 서버가 클라이언트와 상호작용을 유지하기 위해 서버 내에 클라이언트의 상태 정보를 저장하는 방식 - 세션 방식 동작 과정
  ![img-18](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/f58243a6-c306-467e-b8af-1e3993128031)

          ![img-1](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/75b058e4-117e-45a3-af32-3e207b7dfb37)

          1. 클라이언트: 계정과 비밀번호를 서버에 보내서 로그인 요청
          2. 서버가 DB를 찔러봐서 인증이 마무리됨
          3. 사용자가 인증이 된 사용자라는 것을 기록해둠
              - 이때 사용자마다 ‘SessionId’라는 고유한 식별자를 발급해서 클라이언트에게 보내줌
          4. 클라이언트는 이 ‘SessionId’를 기록해두었다가 이후 인가가 필요한 다른 절차에서 SessionId를 함께 보냄
              - 서버는 아까처럼 DB를 찌를 필요 없이 이미 기록해두었던 sessionid를 비교함으로써 이 사람이 이미 인증이 완료된 사람이라는 것을 알고 인가 절차를 밟음

- 토큰
  : 사용자 인증에 대한 정보가 담긴 징표 - ex. Bearer 토큰 - OAuth 프레임워크에서 액세스 토큰으로 사용하는 토큰의 유형 - Bearer 토큰은 불투명한(Opaque) 문자열 (= Base64로 인코딩) - 토큰의 형태: 16진수의 문자열, JSON Web Token(JWT) - JWT (디코딩된 형태)
  `java
{ // header
  "typ": "JWT",  //Type. 토큰의 타입
  "alg": "HS512" //Algorithm. 토큰의 서명을 발행하기 위해 사용된 해시 알고리듬 종류
},
{ // payload
  "sub": "546546574869465465", //Subject. 토큰의 주인. 유일한 식별자.
  "iss": "demo app",           //Issuer. 토큰을 발행한 주체.
  "iat": "54564654",           //issued at. 토큰이 발행된 날짜와 시간.
  "exp": "54564654"            //expiration. 토큰이 만료되는 시간
}.
qqqqqqqqqqqqqqqq // signature. 토큰을 발행한 주체(Issuer)가 발행한 서명. 유효성 검사에 사용.
`

                                                - Bearer 토큰은 클라이언트가 해석할 수 없는 형태여야 하고, 사용자의 정보를 전달하면 안됨.
                                                - 서버에서 클라이언트의 권한을 확인할 수 있는 메타데이터가 토큰에 인코딩 되어있어야 함 (복잡한 알고리즘을 사용해서 발급)
                                            - 토큰 방식 동작 과정


                                                ![img-2](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/69d14a35-dac2-4ea0-9b39-f2faf37a07f7)

                                                ![img-3](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/f4d07e7c-22c8-4a29-8087-2861d1a5dddd)

                                                1. 클라이언트: 계정과 비밀번호를 서버에 보내서 로그인 요청
                                                2. 서버가 DB를 찔러봐서 인증 완료
                                                3. 서버가
                                                1) 사용자가 인증이 완료 되었다는 것을 확인 2) 사용자의 정보도 알고 있음
                                                → 자신의 비밀키를 통해 토큰을 발행
                                                → 발행한 토큰을 클라이언트에게 전해줌
                                                4. 클라이언트는 이 토큰을 가지고 있다가, 차후 다른 요청을 보낼 때 이것을 포함해서 보냄
                                                5. 서버는 이 토큰을 가지고 비밀키를 사용해서 이 토큰을 풀어본 다음,
                                                1) 변조가 되지 않았다는 것을 확인하고, 2) 어떤 사용자인지도 확인해서
                                                → 인가 절차를 밟음

### 확장성 (세션 vs 토큰)

**[세션]**

- flow
  ![img-4](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/066e3d5b-575e-4acd-91fb-d647bf5bb82b)
  1. 로그인 요청이 들어옴
     로드 밸런서가 하단에 있는 서버에 이 업무를 분담함
  2. 해당 서버에서 SessionId가 발급되었고, 이 SessionId가 클라이언트에게 전달됨
     ![img-5](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/1729abd2-9458-4d94-888c-a0c5e6f3fa33)
  3. 클라이언트가 이 SessionId로 다시 요청을 보내옴
  4. 아래에 있는 서버가 아닌 중간에 있는 서버에 이 업무를 분담했다고 가정
  5. 이 서버(중간)는 아래에 있는 서버와 같이 SessionId를 갖고 있지 않기 때문에 지금의 클라이언트가 이미 인증이 완료된 사용자라는 것을 알지 못함
  6. 따라서, 인증이 안되었다는 응답을 보냄
- 세션은 확장적인 측면에 있어서, 이전과 같이 동작하지 못하고 추가적인 조치를 필요로 함
- 세션은 확장에 불리한 구조

**[토큰]**

- flow
  ![img-6](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/41dd37fe-42db-4fe9-acb7-6c816006513a)
  1. 임의의 서버에서 토큰이 발행이 되어서 클라이언트에 간 상황이라고 가정
     ![img-7](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/dbde7ad3-a311-4371-b313-4f6e9c40a0e7)
  1. 이 토큰을 사용해서 클라이언트가 서버에 다시 요청을 보냄
  1. 이 때, 로드밸런서가 어떤 서버에 업무를 분담하는지 상관 없음
  1. 모두 동일한 Secret Key를 가지고 있어서 토큰을 열어보고,
  1) 변조가 되었는지
  2) 사용자가 누구인지만 확인하면 됨
- 토큰이 확장성에 있어선 더 유리한 구조다

### 보안 (세션 vs 토큰)

**[세션]**

- flow
  ![img-8](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/06b8dddb-0b06-4690-a1e2-26aacce12c75)
  - 해커가 SessionId를 탈취
    ![img-9](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/aa34a893-6daa-414b-bd74-48078452ac47)
  - 해커는 SessionId를 통해 자신이 인증된 사용자인양 서버에 접근하려 시도함
- 서버는 탈취된 SessionId를 삭제할 수 있음
  = 더 이상 탈취된 SessionId를 사용해서는 서버에 접근이 불가능함
- 보안 관련 측면에서는 세션이 토큰 보다 더 안정적임.

**[토큰]**

- flow
  ![img-10](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/cc13c4f6-b34c-43b6-93f8-909b2d29456d)
  - 해커가 token을 탈취
  - 해커는 token을 통해서 서버에 당연히 접근할 수 있음
- 서버는 이를 막을 방법이 당장은 없음
- 서버가 토큰에 대한 정보를 전혀 관리하고 있지 않기 때문
  - ⇒ refresh token & access token

## 3️⃣ Google OAuth2.0 (소셜로그인)

<aside>
💡 **OAuth**는 
인터넷 사용자들이 비밀번호를 제공하지 않고 
다른 웹사이트 상의 자신들의 정보에 대해 웹사이트나 애플리케이션의 접근 권한을 부여할 수 있는 공통적인 수단으로서 사용되는, 
접근위임을 위한 개방형 표준.

</aside>

- 개인의 민감한 정보를 타서비스가 관리하는 것이 아닌,
  구글, 네이버, 카카오 등에 개인정보 관리 책임을 위임하여 보안 및 관리적인 측면에서 유리

## Google OAuth 흐름도

<img width="404" alt="img-11" src="https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/9873cd48-c1b9-4e67-b6f9-37ad3618a9cb">

- _Google Servers — 리소스 소유자 - 인증 서버 - 리소스 서버 (위부터 차례로) 인 듯_
  ![img-12](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/2f7e1c4b-b97d-42ff-ae3e-6ca8f4b92ba0)

### 1. 구글 로그인 진행

1. [Google Cloud](https://console.cloud.google.com/apis/credentials/consent?hl=ko&project=boomarble)에서 OAuth 클라이언트 생성
2. 생성 후, 발급받은 Client ID와 Client Secret Key를 활용하여 소셜로그인 url을 완성
   - 이 url은
     ClientId, Secret Key, 로그인이 끝난후 리다이렉션할 uri, response type, 정보 공개 범위인 scope 등의 정보를 포함

`application-aws.yml`

```java
oauth2:
  google:
    client-id: CLIENT_ID
    client-secret: CLIENT_SECRET
    redirect-uri: http://localhost:8080/login/oauth2/code/google
    token-uri: https://oauth2.googleapis.com/token
    resource-uri: https://www.googleapis.com/oauth2/v2/userinfo
```

> https://accounts.google.com/o/oauth2/auth?client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&response_type=code&scope=https://www.googleapis.com/auth/userinfo.emailhttps://www.googleapis.com/auth/userinfo.profile

<img width="483" alt="img-13" src="https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/eaf001f6-c7f0-45d8-9c13-eb760b2114b9">

- Google Cloud에서 OAuth 클라이언트 생성 후,
  받은 Client ID와 Client Secret Key를 발급 받아 위 링크에 넣어서 접속하면
  옆과 같은 화면이 뜸

### 2. 로그인 성공 시, Google 승인서버(Authorization Server)에서 **Authorization Code를 제공**

- 해당 로그인 완료 시 Google 승인 서버에서 Authorization code를 제공
  (이 코드는 로그인을 성공했다는 의미의 인증코드로 토큰은 아님)

![img-14](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/cbc9e75c-ff38-408a-aa10-1093aa11660a)

### 3. Google 승인서버(Authorization Server)에 **Authorization Code로 Access Token과 교환**

- 앞서 받은 Authorization code를 Google 승인 서버에 보내면 승인서버가 Access token을 제공

![img-15](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/461fa5c5-00d0-4e61-a3d7-1179081eb70c)

### 4. Access Token으로 Google 자원서버(Resource Server)에 유저자원(유저정보) 요청

- Access token을 Google 자원서버에 보냄으로서 유저 정보를 요청 가능
  (자원서버는 유저 정보를 안전하게 저장하고 있는 서버)

![img-16](https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/b560672f-a950-42e5-9453-b304dd83820a)

- OAuth에 Resource Server 라는 곳이 있는데,
  이 곳은 유저 정보를 안전하게 저장하고 있는 서버

### 5. 유저자원(유저정보) 반환

<img width="1357" alt="img-17" src="https://github.com/Ssafy-Developer-Study/CS-study/assets/48985475/454b37a4-490c-48e8-a515-e3bfc8a3a1b8">

_Basic 인증 →> Bearer 인증 → 토큰 기반 인증 → OAuth2.0 인증_
