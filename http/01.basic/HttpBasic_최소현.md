## 01. HTTP 란?
### HTTP 의 정의
* HTML (Hyper Text Markup Language) 는 초월 텍스트라는 의미로, HTTP란 텍스트뿐 아니라 그림, 동영상 등을 포함하는 전송 프로토콜 이다. 
* 즉, HTML와 같은 문서를 전송하기위한 Application Layer에서 사용하는 규칙을 정의한 것.

### 어떻게 주고 받는가
* Request (요청) : 서버 <- 클라이언트(브라우저)
* Response (응답) : 서버 -> 클라이언트(브라우저)

### 무엇을 주고 받는가
* 프로토콜을 구성하는 하위 문서들
* ex. 텍스트, 레이아웃 설명, 이미지, 비디오, 스크립트

### HTTP와 HTTPS의 차이(TLS)
* TLS (Transport Layer Security) : 전송하고자 하는 것에 암호화를 더하여 기밀성을 보장하고자 할때 사용되는 기술

* HTTPS (HTTP Secure) : TLS을 거친 HTTP.

* 따라서 HTTP / HTTPS의 차이점은 보안계층인 TLS (혹은 SSL) 를 거쳤느냐 거치지 않았느냐 이다.


## 02. HTTP 특징
### 클라이언트-서버 모델
* 2-tier Architecture   
    * 구조 : 클라이언트 <-> 서버   
    * 의미 : 리소스를 사용하는 클라이언트와 리소스를 제공하는 서버를 분리시키는 모델을 뜻한다.

* 3-tier Architecture
    * 구조 : 클라이언트 <-> 서버 <-> DB
    * 의미 : 서버가 DB에 리소스를 저장하는 구조로, 서버는 리소스를 전달하는 역할만 한다. 대부분 요 구조.

* 브라우저   
클라이언트가 이용하는 도구. 브라우저를 통해 클라이언트가 서버에게 데이터를 요청하고, 서버가 클라이언트에게 데이터를 제공한다.

### Stateless와 Stateful
* Stateful   
    * 클라이언트의 상태 정보를 서버에서 저장함으로써 클라이언트와 서버간의 통신을 유지하는 것. 즉, 상태관리의 주체는 서버

    * How?   
    브라우저의 쿠키(Cookie)에 저장되거나, 서버의 세션(Session) 메모리에 저장

* Stateless   
    * 클라이언트의 상태 정보를 서버에서 자장하지 않고, 단순히 요청에 대한 응답만 보내게 됨.
    * 즉, 상태관리의 주체는 클라이언트

## 03. HTTP 네트워크

### 인터넷 (International Network) 이란? 

TCP/IP를 기반으로 전세계의 네트워크를 하나로 연결하여 각 PC가 가지고 있는 자료나 정보를 주고 받을 수 있는 광역 네트워크 (컴퓨터 망)

### IP (인터넷 프로토콜)
- TCP/IP 모델은 OSI 모델의 간결한 버전

### TCP (Transmission Control Protocol)

- 연결 지향 — 3 way handshake (가상 연결)
- 데이터 전달 보증
- 순서 보장
- 통신 순서 : Listen → Connect → Accept → Send&Receive → Close

### UDP (User Diagram Protocol)

- 하얀 도화지 (TCP가 하는 것들 다 안됨)
- 단순하고 빠름
- 그렇다면 IP와 뭐가 다른가?
    - PORT 추가
    - 체크섬 추가

### PORT

- 같은 IP내에서 프로세스를 구분해주는 녀석

### IP의 문제는?

- 외우기 어려움
- IP가 바뀔수 있음

### 즉, DNS란?

할당된 도메인 영역에 대한 정보를 가지고 있는 서버로, 주로 도메인을 IP주소로 변환하는 역할


## 04. URI와 웹 브라우저 요청 흐름

### 웹 (World Wide Web) 이란?

1. 인터넷으로 연결된 사용자들이 정보를 공유할 수 있는 공간.
    
    즉, 인터넷에서 사용되는 서비스 중 하나
    
2. HTML 이라는 문서 형태와, HTTP 라는 문서 전송 프로토콜, URL 로 문서의 위치 표시하는 시스템.

## URI / URL / URN

### URI (Uniform Resource Idenrifier)

- 정의
    
    자원의 식별자
    
- 구조
    
    ```
     scheme:[//[user[:password]@]host[:port]][/path][?query][#fragment]
    ```
    
    | scheme | 프로토콜 종류 (http, https, ftp, mailto) |
    | --- | --- |
    | user, password | 사용자 이름 , 비밀번호 (ftp 에서 사용됨) |
    | host, port | host (ip), 서버의 네티워크 포트 (http는 기본 80) |
    | path | 서버에서 리소스의 위치 |
    | query | 서버의 DB에서 요청받기 위한 리소스의 범위를 좁히기 위한 질의 (?이름=값) |
    | fragment | 특정 부분을 가리킴 (용량이 크거나 원하는 것이 명확할때) |

### URL (Uniform Resource Locator)

- 정의
    
    자원의 위치 (일반적으로 사이트 도메인을 의미)
    
- 구조
    
    ```
     scheme:[//[user[:password]@]host[:port]][/path]
    ```
    

### URN (Uniform Resource Name)

- 정의
    
    자원의 이름
    
- 구조
    
    ```
    urn:ietf:rfc:2648
    ```
    
- URN은 한 리소스에 대해 위치와 상관없이 유일하게 해당 리소스를 식별하는 이름
- 리소스가 이름에 매핑되어 있어야 하기 때문에 이름으로 부여하면 거의 찾기가 힘들다. 그래서 대부분 URL만 쓴다.

### 예시)

- **`https://example.com`**
    
    웹상의 자원을 가리키는 URL이며, 동시에 URI
    이 주소는 웹 서버 자체를 가리키며, 이 경우 URL이면서 URI
    
- **`https://example.com/skin`**
    
    "skin"은 서버상의 특정 자원의 위치를 나타내므로, 웹상의 특정 자원을 가리키는 URL이며, URI 
    
- **`https://example.com/one/two/abc.html`**
    
    "one/two/abc.html"은 서버상의 구체적인 파일 위치를 가리키므로, URL이며 URI
    
- **`https://example.com/one?id=123`**
    
    여기서 "?id=123"는 쿼리 스트링이며, 특정 자원을 더 구체적으로 식별하는 데 사용하므로, URL은 **`https://example.com/one`** 이며 URI은 **`https://example.com/one?id=123`**
    

### 안전하지 않은 문자

- 안전하지 않은 문자란?
    
    혼동을 일으킬 수 있는 문자 (예. 공백, 특수문자 (# % & ?), ASCII문자가 아닌것)
    
    | 문자 | 선점 및 제한 |
    | --- | --- |
    | % | 인코딩된 문자에 사용할 이스케이프 토큰으로 선점 |
    | / | 경로 컴포넌트에 있는 경로 세그먼트를 나누는 용도로 선점 |
    | . | 경로 컴포넌트에서 선점 |
    | .. | 경로 컴포넌트에서 선점 |
    | # | 프래그먼트의 구획 문자로 선점 |
    | ? | 질의 문자열의 구획 문자로 선점 |
    | ; | 파라미터의 구획 문자로 선점 |
    | : | 스킴, 사용자 이름/비밀번호, 호스트/포트의 구획 문자로 선점 |
    | $,+ | 선점 |
    | @&= | 특정 스킴에서 특별한 의미가 있기 때문에 선점 |
    | {}.~[]` | 게이트웨이와 같은 여러 전송 에이전트에서 불안전하게 다루기 때문에 제한 |
    | <>" | URL 범위 밖에서 역할이 있는 문자이기 때문에 반드시 인코딩해야 함 |
    | 0x00-0x1F, 0x7F | 인쇄되지 않는 US-ASCII 문자 |
    | 0x7F보다 큰 범위 | 7비트 US-ASCII 문자가 아님 |
- 처리 방법
    - 퍼센트 인코딩 → 이스케이프 문자
        
        % 다음에 두개의 16진수 숫자 가 오는 형태로 인코딩
        
    - 문자 제한

### 웹 브라우저 요청 흐름

1. 웹 브라우저에 [www.naver.com](http://www.naver.com) URL 입력
2. URL을 IP주소로 변환
    a. local 스토리지의 hosts 파일을 찾아봄. -> ip주소가 있다면 변환 끝   
        * host 파일
            
            
            # Copyright (c) 1993-2009 Microsoft Corp.
            #
            # This is a sample HOSTS file used by Microsoft TCP/IP for Windows.
            #
            # This file contains the mappings of IP addresses to host names. Each
            # entry should be kept on an individual line. The IP address should
            # be placed in the first column followed by the corresponding host name.
            # The IP address and the host name should be separated by at least one
            # space.
            #
            # Additionally, comments (such as these) may be inserted on individual
            # lines or following the machine name denoted by a '#' symbol.
            #
            # For example:
            #
            #      102.54.94.97     rhino.acme.com          # source server
            #       38.25.63.10     x.acme.com              # x client host
            
            # localhost name resolution is handled within DNS itself.
            #	127.0.0.1       localhost
            #	::1             localhost
            216.58.200.14	naver.com
            
            
    b. DNS cache 참고 —> ip주소가 있다면 변환 끝
        - cache 확인 링크
            
            chrome://net-internals/#dns
            
            
    c. DNS 서버에 접속
        - DNS 서버에서 IP 찾는법
            
3. 해당 IP주소로 TCP/IP 연결
4. client에서 서버에 get method로 HTTP request
5. server에서 HTTP response