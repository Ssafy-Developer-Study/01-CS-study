# 메서드와 상태코드.araboza

[RFC 7231: Hypertext Transfer Protocol (HTTP/1.1): Semantics and Content](https://www.rfc-editor.org/rfc/rfc7231)

여기에 다 있음. 진짜 다 있음.

# HTTP 메서드

### HTTP 메서드는 왜 존재하는가?

- 웹 서버에 의미있는 요청을 보내기 위해!

<aside>
📦 문장에서 Verb의 역할을 한다고 생각하면 좋다.

문장의 구성: S V O ← 주어 동사 목적어죠?

S: url → 이 `사이트 / 자원`이

V: method → ~~한다 (추가, 삭제, 읽기 등)

O: 이런저런 녀석을 (Payload, request body 라고 불리우는 것)

</aside>

### 주요 메서드들

- GET
- POST
- PUT
- DELETE

⇒ 보통 RESTful API에서 적극 활용하는 친구들. 필요에 따라 GET만 쓰는 경우도 있고 그렇습니다

### GET

- 목적: 정보 땡기기 `retrieve`
- 용례: 사이트 방문, 데이터 fetch등.

### POST

- 목적: `Submit data to be processed to a specified resource.`

<aside>
📦 뭐야 리소스 생성이 아니었어?

- “특정 자원으로 처리될 어떤 데이터를 제출`submit`” 할 때 쓰이는 메서드임
- 물론 REST에서는 새 자원을 생성할 때 쓰긴 하는데, 정석적인 용례와 미묘하게 다르지만 그렇게 쓰인다는 것을 알고있으면 좋음.
</aside>

- 용례: DB에 새로운 자원을 추가할 때, 파일을 업로드 할 때 등…

### PUT

- 목적: 특정 payload를 통해 완전히 update하고자 할 때
- 용례: DB에 세부 값을 업데이트한다거나 할 때

---

### ”PUT은 POST와 달리 `idempotent`해야 한다”

<aside>
📦 `Idempotent` 라는 개념에 대하여:

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/c75b9cf6-2f32-4573-ac62-dc6dde65ea2f/Untitled.png)

~~멱등원이 뭔데…~~

동일한 요청을 여러번 보내도, 결과는 항상 같아야 한다는 것.

예를 들어 봅시다

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/cb756533-e560-4848-ae3b-fe95e80645a2/Untitled.png)

얘는 요청을 보낼 때 마다 새로운 자원이 추가됨. `posts/123, post/124, …` 이렇게

근데 PUT은 이렇게 됩니다

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/b19631b6-27cb-4c7e-8a32-94e7ddb87a03/Untitled.png)

그럼 항상 같은 값으로 수정되거나, 한결같이 `204 no content`를 뱉을거에요!

</aside>

### DELETE

- 목적: 자원 삭제.
- 용례: 데이터를 DB에서 지울 때 사용.

### 그리고 기타 메서드들.

- PATCH: PUT과 비슷하지만 부분적 fetch
- HEAD: GET과 비슷하지만 헤더만 받고 싶음
- OPTIONS: 타깃 자원과 통신하는 options를 보여줌 (CORS에서 활용)
- TRACE: loop-back test에 (진단 목적으로) 활용

# HTTP 메서드 활용

(express.js 서버로 간단히 보는 CRUD API)

```jsx
const express = require("express");
const app = express();
app.use(express.json()); // Middleware to parse JSON bodies

let books = []; // This will act as our in-memory database

// CREATE a book
app.post("/books", (req, res) => {
  const book = { id: books.length + 1, ...req.body };
  books.push(book);
  res.status(201).send(book);
});

// READ all books
app.get("/books", (req, res) => {
  res.status(200).send(books);
});

// UPDATE a book
app.put("/books/:id", (req, res) => {
  let book = books.find((b) => b.id === parseInt(req.params.id));
  if (!book) return res.status(404).send("Book not found.");
  book = { ...book, ...req.body };
  res.status(200).send(book);
});

// DELETE a book
app.delete("/books/:id", (req, res) => {
  books = books.filter((b) => b.id !== parseInt(req.params.id));
  res.status(204).send();
});

const port = 3000;
app.listen(port, () => console.log(`Listening on port ${port}...`));
```

# HTTP 상태 코드

### 왜 필요한가?

- 당연히 method랑 똑같이… response에 대해 간단히 알려주는 국룰 같은거.

## 1XX: **Informational**

- 100 Continue.
  - 첫 부분은 다 받았음. 나머지 받든가 말든가.

## 2XX: **Success**

- 200 OK
- 201 Created
- 204 No content

## 3XX: **Redirection**

- 301 Moved Permanently
- 302 Found

## 4XX: **Client Error**

- 클라이언트 문제라는 뜻이 아니라, 서버에서 기대했던 `expected`이슈라는 뜻.
- 400 Bab Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not found
- 405 Method not allowed
- 418 `I'm a teapot`
  [](https://www.google.com/teapot)

## 5XX: Server Error

- 500 Internal Server Error
- 501 Not Implemented
- 503 Service Unavailable

# 그냥 개인적으로 궁금한 것들

- GET request를 보낼때는 payload를 비워야 하나요?
  ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/5ba68b5b-52d0-468e-8e7f-8ef3ed6ad522/Untitled.png)
- GraphQL은 POST 메서드만 사용해?
  ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/ea766943-1b6b-4a35-b653-39897026faeb/Untitled.png)
- HTTP Method와 SEO에 대하여
