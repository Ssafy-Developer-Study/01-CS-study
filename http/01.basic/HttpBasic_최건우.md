# Intro + URI & Browser Request + HTTP basics

# 인터넷 네트워크

[Overview of the Internet](https://www.cs.utexas.edu/~mitra/csFall2019/cs329/lectures/webOverview.html)

고마워요 텍사스 대학교!

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/a47adb94-62f6-4d8b-8541-b9598de57c47/Untitled.png)

<aside>
📦 인터넷은 케이블이나 인공위성같은걸로 연결된 컴퓨터 연결의 집합체이다.

모든 컴퓨터가 인터넷에 접속하는 대신, 컴퓨터 뭉치들끼리는 보통 LAN으로 연결된다.

<aside>
📦 LAN(local area network)이란?

[LAN(근거리 통신망)이란? | Cloudflare](https://www.cloudflare.com/ko-kr/learning/network-layer/what-is-a-lan/)

</aside>

인터넷 연결은 ISP들끼리 책임진다.

인터넷을 가만 보면 core와 edge가 있는데,

core는 라우터들의 네트워크이고

edge는 개별 애플리케이션을 실행하는 서버나 클라이언트들이다.

</aside>

## 인터넷 통신

<aside>
📦 데이터 통신 방법이 두가지가 있는데요

1. circuit switching이랑
   - 전화망 같은거. 회로를 직접 연결해서 통화함.
   - 장점 - 빠르다 (공유하는 다른 친구 없음)
   - 단점: 동접자가 적으면 링크가 놀고, 동접자가 많으면 부족해서 못쓰는 사람이 생긴다.
2. packet switching이 있슴다 - 패킷 스위칭은 청크*chunk* 단위로 데이터를 라우터로 옮기고, 라우터가 그 데이터를 목적지로 보내줌. - 모두가 동일한 네트워크 자원을 공유하기 때문에 유저가 많아도 웬만큼 감당 가능하다. - 너무 많으면 대신 congestion 문제가 생긴다. 데이터 loss가 생기고, 그것 때문에 관련된 프로토콜이 생긴다
</aside>

<aside>
📦 패킷 딜레이에 대하여

`d_nodal = d_proc + d_queue + d_trans + d_prop`

`d_proc` : 라우터의 처리*processing* 딜레이. 수ms 단위.

`d_queue` : 라우터 *queue*에서 대기하는 딜레이: 라우터가 얼마나 혼잡한지에 따라 다름

`d_trans` : _transmission delay -_ 대역폭과 패킷 길이에 따라 다름.

`d_prop` : _propagation -_ 전송 수단의 속도에 따라 다름. 보통 수백 ms 단위.

</aside>

## TCP/IP

<aside>
📦 IPS 란?

인터넷에서 컴퓨터들이 서로 정보를 주고받는 데 쓰이는 통신규약(프로토콜)의 모음이다. 인터넷 프로토콜 슈트 중 TCP와 IP가 가장 많이 쓰이기 때문에 TCP/IP 프로토콜 슈트라고도 불린다.

</aside>

<aside>
📦 TCP/IP란?

인터넷 프로토콜인 IP와 전송 조절 프로토콜인 TCP로 이루어져 있다.

</aside>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/fcf17970-d7cb-41b5-a7eb-061dc29bb37a/Untitled.png)

대충 이 그림 따라가면 될듯?

## IP(인터넷 프로토콜)

<aside>
📦 Network Layer(3 Layer) ← IP(Internet Protocol)이 활용되는 부분

한 Endpoint가 다른 Endpoint로 가고자 할 경우, 경로와 목적지를 찾아줍니다. 이를 Routing이라고 하며 대역이 다른 IP들이 목적지를 향해 제대로 찾아갈 수 있도록 돕는 역할을 합니다.

</aside>

## TCP, UDP

간단하게 말하면, TCP는 신뢰성을 중시하고, UDP는 속도를 중시한다고 할 수 있어.

<aside>
📦 빠르게 정리하기 - TCP

TCP는 연결을 맺고 데이터가 제대로 도착했는지 확인하면서 전송해, 그래서 데이터 전송이 정확하지만 조금 느려.

</aside>

<aside>
📦 UDP

반면에 UDP는 연결 없이 데이터를 바로 보내버려서 빠르긴 한데, 가끔 데이터가 제대로 안 도착할 수도 있어.

</aside>

## PORT

[컴퓨터 포트란? | 네트워크 포트 | Cloudflare](https://www.cloudflare.com/ko-kr/learning/network-layer/what-is-a-computer-port/)

<aside>
📦 포트*port* 란??

포트는 운영 체제 **통신의 종단점**이다. 이 용어는 하드웨어 장치에도 사용되지만, 소프트웨어에서는 네트워크 서비스나 특정 프로세스를 식별하는 논리 단위이다.

</aside>

<aside>
📦 네트워크 포트란?

포트는 **네트워크 연결이 시작되고 끝나는 가상 지점**입니다. 포트는 **소프트웨어 기반**이며 컴퓨터의 운영 체제에서 관리합니다. 각 포트는 **특정 프로세스 또는 서비스와 연결**됩니다. 포트를 사용하면 컴퓨터가 서로 다른 종류의 트래픽을 쉽게 구별할 수 있습니다. 예를 들어 이메일은 동일한 인터넷 연결을 통해 컴퓨터에 도달하더라도 웹 페이지와 다른 포트로 이동합니다.

</aside>

<aside>
📦 포트 번호에 대하여

⇒ 강제는 아니고, IANA에서 권고한 사항들. 운영체제마다 디테일이 다르고, 보통은 수정할라면 할 수는 있음.

- 0번 ~ 1023번: 잘 알려진 포트 (well-known port)
- 1024번 ~ 49151번: 등록된 포트 (registered port)
- 49152번 ~ 65535번: 동적 포트 (dynamic port)
</aside>

## DNS

[DNS란 무엇입니까? – DNS 소개 - AWS](https://aws.amazon.com/ko/route53/what-is-dns/)

[DNS와 작동원리](https://velog.io/@minj9_6/DNS와-작동원리)

- 도메인 이름을 입력하면 IP로 바꿔주는 서비스 서버.
- Query
  → Local DNS → ROOT DNS → TLD DNS → Auth DNS → Cache →
  Response

# URI와 웹 브라우저 요청 흐름

<aside>
📦 요약

**URI**는 인터넷의 자원을 특정하는 표준화된 방법으로 굉장히 중요하다

scheme, auth, path, query, fragment 등을 포함함.

URL은 URI의 일종, URN은 위치와 상관없이 자원을 식별하는 unique한 식별자로서의 역할.

**웹 브라우저 요청 흐름**은 대충 이렇다.

1. DNS lookup
2. 연결 수립 (TCP, HTTP 등)
3. 요청 보내기 (GET, POST 등)
4. 서버에서의 처리
5. 응답 받기
6. 페이지 렌더링하기
</aside>

## URI

<aside>
📦 **Definition and Components**

[통합 자원 식별자](https://ko.wikipedia.org/wiki/통합_자원_식별자)

</aside>

<aside>
📦 **URL vs. URN**

[URI URL 구조, 차이점](https://velog.io/@h220101/URI-URL-구조-차이점)

</aside>

<aside>
📦 **Encoding and Internationalization**

</aside>

## 웹 브라우저 요청 흐름

<aside>
📦 **User Action to Request**

URL에 접속하거나, 버튼을 누르는 방식으로 리퀘스트를 만들게 된다

</aside>

<aside>
📦 **DNS Lookup**

[DNS와 작동원리](https://velog.io/@minj9_6/DNS와-작동원리)

</aside>

<aside>
📦 **Establishing a Connection**

[[HTTP] 커넥션 관리에 대해서](https://velog.io/@devstefancho/HTTP-커넥션-관리에-대해서)

</aside>

<aside>
📦 **Request**

<aside>
📦 **Sending the Request**

[[간단정리] HTTP Request/Response 구조](https://hahahoho5915.tistory.com/62)

</aside>

<aside>
📦 **Server Processing and Response**

</aside>

<aside>
📦 **Receiving and Rendering the Response**

</aside>

</aside>

<aside>
📦 **Handling Redirects and Errors**

</aside>

<aside>
📦 **Caching and Optimization**

</aside>

# HTTP 기본

## Intro

<aside>
📦 HTTP(hypertext transfer protocol)는 WWW에서 데이터를 통신하는 근-본 프로토콜이다.

- 메시지 형식과 전송 방식에 대해 알려줌
- 서버와 브라우저가 어떻게 다양한 요청에 응답해야 하는지를 알려줌
</aside>

## Client-Server Architecture

<aside>
📦 http는 client-server 아키텍처를 따라간다.

서버 사이드의 data storage, processing과 user interface를 분리하는 효과가 있음

</aside>

## Stateful, Stateless

<aside>
📦 http는 **stateless** 프로토콜이다.

각각의 요청이 전부 독립적으로 받아들여지고, 메모리를 가지지 않음

효율적인 관리에 유리하지만(서버 관리, 통신 효율 등에 의해), 쿠키/세션 등을 구현해야 함

</aside>

## connectionless

<aside>
📦 Http는 connectionless 프로토콜이다

요청 받아서 할거 다 하고 뱉으면 연결이 닫힘

기본적으로는 그렇지만, 버전이 업데이트 되면서 persistent connection과 multiplexing이 도입됨

</aside>

## HTTP message

<aside>
📦 HTTP 메시지의 양식에 대해 알아보자

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/09f5fea1-17ff-459d-b78b-874771a12d95/Untitled.png)

`Start-Line` : http status, url, protocol version, method등

`HEADER` : content-type, json, accept, connection, user-agent, authorization, cookie, session id 등

`Empty Line` : header-by 구분

`Body` : payload.

</aside>

# 예상 질문

<aside>
📦 다음의 상황에 UDP를 쓰시겠어요, 아니면 TCP를 쓰시겠어요?

⇒ 인스타그램 라이브 기능 구현

⇒ FPS 게임(오버워치나 배그같은거?)의 플레이어 정보 송수신 구현

</aside>

<aside>
📦 8080 포트가 뭔지 혹시 아세?

</aside>
