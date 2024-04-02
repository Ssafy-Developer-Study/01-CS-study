# HTTP의 헤더!

[HTTP 헤더 - HTTP | MDN](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers)

[HTTP 헤더 | 토스페이먼츠 개발자센터](https://docs.tosspayments.com/resources/glossary/http-header)

# **Section 1. HTTP 헤더1 - 일반 헤더**

## **1. HTTP 헤더 개요**

<aside>
📦 **[HTTP 헤더](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers)**는 **클라이언트와 서버 간의 HTTP 요청과 응답에서 추가 정보를 전달하는 데 사용돼요**.

이 정보에는 요청의 유형(`Content-Type`), 응답의 유형(`Accept`), 인증 정보(`Authorization`), 캐싱 제어(`Cache-Control`, `ETag`, `Last-Modified`) 등 다양한 메타데이터가 포함되죠.

HTTP 헤더는 이름-값 쌍 형식이라 `{HTTP_HEADER_KEY}: {HTTP_HEADER_VALUE}` 형식으로 씁니다.

</aside>

```bash
curl "http://70.12.60.160/?mode=jquery" ^
  -H "Accept: */*" ^
  -H "Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7" ^
  -H "Connection: keep-alive" ^
  -H "Cookie: HFS_SID_=0.273450492881238" ^
  -H ^"Referer: http://70.12.60.160/^%^EB^%^AF^%^BC^%^EA^%^B7^%^9C^%^EC^%^8C^%^A4^%^EC^%^84^%^9C^%^EB^%^B2^%^84/^" ^
  -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Whale/3.25.232.19 Safari/537.36" ^
  --insecure
```

여기서 -H 부분이 헤더임.

<aside>
📦 **HTTP 헤더는 이름-값 쌍 형식이라 `{HTTP_HEADER_KEY}: {HTTP_HEADER_VALUE}` 형식으로 씁니다.**

- 헤더는 컨텍스트에 따라 그룹핑될 수 있습니다: - [General header](https://developer.mozilla.org/ko/docs/Glossary/General_header): 요청과 응답 모두에 적용되지만 바디에서 최종적으로 전송되는 데이터와는 관련이 없는 헤더. - [Request header](https://developer.mozilla.org/ko/docs/Glossary/Request_header): 페치될 리소스나 클라이언트 자체에 대한 자세한 정보를 포함하는 헤더. - [Response header](https://developer.mozilla.org/ko/docs/Glossary/Response_header): 위치 또는 서버 자체에 대한 정보(이름, 버전 등)와 같이 응답에 대한 부가적인 정보를 갖는 헤더. - [Entity header](https://developer.mozilla.org/ko/docs/Glossary/Entity_header): 컨텐츠 길이나 MIME 타입과 같이 엔티티 바디에 대한 자세한 정보를 포함하는 헤더.
</aside>

<aside>
📦 **커스텀 등록 헤더는 'X-'를 앞에 붙여 추가될 수 있지만,**
이 관례는 [RFC 6648](https://tools.ietf.org/html/rfc6648)에서 비표준 필드가 표준이 되었을때 불편함을 유발하는 이유로 2012년 6월에 폐기되었습니다.

</aside>

## **2. 표현**

[표현 헤더 (Representation header) - MDN Web Docs 용어 사전: 웹 용어 정의 | MDN](https://developer.mozilla.org/ko/docs/Glossary/Representation_header)

<aside>
📦 표현 헤더는 `[Content-Type](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Content-Type)`, `[Content-Encoding](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Content-Encoding)`, `[Content-Language](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Content-Language)` 및 `[Content-Location](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Content-Location)`를 포함합니다.

</aside>

1. **Content-Type**: 이 헤더는 전송되는 데이터의 종류를 나타냅니다. 예를 들어, 텍스트, 이미지, HTML 문서 등이 있으며, 각각 **`text/plain`**, **`image/png`**, **`text/html`** 등의 미디어 타입으로 표현됩니다.
2. **Content-Encoding**: 데이터가 어떻게 압축되었는지를 나타내는 헤더입니다. 예를 들어, gzip이나 deflate 압축 방식을 사용할 수 있습니다.
3. **Content-Language**: 문서가 어떤 언어로 작성되었는지를 나타내는 헤더로, 예를 들어 **`en-US`**는 미국 영어를 의미합니다.
4. **Content-Disposition**: 클라이언트가 응답을 어떻게 처리해야 하는지를 알려주는 헤더입니다. 예를 들어, 파일을 다운로드 할 때 **`attachment; filename="filename.jpg"`**와 같이 사용됩니다.
5. **Content-Location**: 헤더는 HTTP 응답에서 사용되며, 요청된 자원의 대체 위치를 나타냅니다. 이 헤더가 있는 경우, 클라이언트는 헤더가 가리키는 위치에서 실제로 제공되는 자원을 다운로드하거나 참조할 수 있습니다.

## **3. 콘텐츠 협상**

[콘텐츠 협상 - HTTP | MDN](https://developer.mozilla.org/ko/docs/Web/HTTP/Content_negotiation)

<aside>
📦 [HTTP](https://developer.mozilla.org/ko/docs/Glossary/HTTP)에서 콘텐츠 협상(Content negotiation)이란 **동일한 URI에서 리소스의 서로 다른 버전을 제공**하기 위해 사용하는 메커니즘으로, 사용자 에이전트가 사용자에게 제일 잘 맞는 것이 무엇인지(예를 들어, 문서의 언어, 이미지 포맷 혹은 컨텐츠 인코딩에 있어 어떤 것이 적절한지)를 명시할 수 있습니다.

</aside>

<aside>
📦 콘텐츠 협상의 원칙

**특정 문서를 *리소스*라고 부릅니다.** 클라이언트가 리소스를 내려받길 원할 경우, 그것의 URL을 사용하여 요청합니다. 서버는 리소스가 제공하는 **여러 변형들 중 하나를 선택**하기 위해 이런 URL을 사용하며 (각각의 변형을 *프레젠테이션*이라고 부릅니다) 클라이언트에게 해당 리소스의 **특정 프레젠테이션을 반환**합니다

</aside>

<aside>
📦 **서버 주도 협상 `server-driven/proactive negotiation`**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/fbdf10b7-0f2f-4c77-bccf-e89901d5ba51/Untitled.png)

대충 서버한테 줄 정보 다 주면 서버가 **오마카세 해오는거**

</aside>

<aside>
📦 **에이전트 주도 협상 `agent-driven/reactive negotiation`**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/8a3f5071-14b1-47cb-b461-8c3cb0cbd1cc/Untitled.png)

대충 서버가 **메뉴판 갖다주면** 클라이언트가 옵션 정해서 주는거

`300 Multiple Choices` 응답코드를 준다

> 불행하게도, HTTP 표준은 그 과정을 쉽게 자동화하는 것을 막는, 사용 가능한 리소스 중에서 선택하도록 허용하는 **페이지의 형식을 명시하지 않고 있습니다.** 게다가 서버 주도 협상으로의 회귀로, 이 방법은 스크립팅, 특히 **JavaScript 리다이렉션**과 함께 거의 항상 사용됩니다.

</aside>

## **4. 전송 방식**

[[네트워크] | HTTP 헤더 - 전송방식](https://velog.io/@alkwen0996/네트워크-HTTP-헤더-전송방식)

1. **Content-Encoding**: 데이터가 어떻게 압축되었는지를 나타내는 헤더입니다. 예를 들어, gzip이나 deflate 압축 방식을 사용할 수 있습니다.
2. **Transfer-Encoding**: 데이터 옮기는 법

   `Transfer-Encoding: chunked`

   `Transfer-Encoding: compress`

   `Transfer-Encoding: deflate`

   `Transfer-Encoding: gzip`

3. **Range : `Range`** HTTP 요청 헤더는 서버에게 문서의 일부분만 돌려주어야 한다는 것을 알려줍니다. `Range` 헤더를 통해 여러 부분을 한번에 요청할 수 있으며, 서버는 이러한 범위에 대해 문서의 여러 부분을 돌려보내줄 것입니다.

## **5. 일반 정보**

- `From` : 이메일 정보 (잘 안씀
- `Referer` : 이전 웹페이지 주소
  - 유입 경로 분석 목적
- `User-Agent` : 유저 에이전트 app 정보
  - 클라이언트 정보
    [User-agent 정확하게 해석하기](https://velog.io/@ggong/User-agent-정확하게-해석하기)
- `Server` : (프록시 서버 말고) Origin 서버 소프트웨어의 정보
  - `Server: Apache/2.2.22 (Debian)`
  - `Server: nginx`
- `Date` : 메시지 발생 시간
  - `Date: Tue, 15 Nov 1994 08:12:31 GMT`

## **6. 특별한 정보**

- `Host` : 요청한 호스트 정보 (도메인)
  - 하나의 서버에 여러 도메인이 적용되어 있을 때 처리
  - **`Host: www.google.com`**
- `Location` : “**3xx 응답의 결과에 Location 헤더**가 있으면, Location 위치로 자동 이동"
- `Allow` : 허용 가능한 HTTP 메서드
  - 405일때 응답에 포함해야 함
  - `Allow: GET, HEAD, PUT`
- `Retry-After` : 서비스가 언제까지 불능인지 알려줌

---

**여기까지~!**

---

## **7. 인증**

[[Auth] Basic Auth](https://velog.io/@parkirae/Auth-Basic-Auth)

HTTP 인증 정보는 아래와 같이 두 가지 속성이 있음:

- **Authorization**: 클라이언트 인증 정보를 서버에 전달
- **WWW-Authenticate**: 리소스 접근시 필요한 인증 방법 정의

### Authorization

- **Authorization: 클라이언트 인증 정보를 서버에 전달하는 것**
- Authorization: Basic xxxxxxx 형태
- 인증 방식마다 들어가는 값은 모두 다름. 하지만, http authorization 헤더는 어떤 인증인지 상관없지 값을 제공하는 것

### WWW-Authenticate

- **WWW-Authenticate: 리소스 접근시 필요한 인증 방법 정의**
- 만약 접근했는데 인증에 문제가 있다면, 401 Unauthorized 응답을 남기고 함께 사용
- ex) WWW-Authenticate: Newauth realm="apps", type=1,title="Login to \"apps\"", Basic realm="simple"
- ex 해석) 위 정보들을 참고해서 인증을 제대로 해라~

## **8. 쿠키**

- **쿠키란?** 웹 서버에서 생성하여, 브라우저에 전송 & 저장되는 작은 텍스트 파일
- 쿠키를 사용할 때는 set-cookie, cookie 두 가지를 사용함
  - Set-Cookie: 서버가 클라이언트로 쿠키 전달 (응답)
  - Cookie: 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP 요청시 서버로 전달

# **Section 2. HTTP 헤더2 - 캐시와 조건부 요청**

## **1. 캐시 기본 동작**

## **2. 검증 헤더와 조건부 요청**

## **3. 캐시와 조건부 요청 헤더**

## **4. 프록시 캐시**

## **5. 캐시 무효화**
