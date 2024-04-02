# 최건우

# 출처

[Linked List | 👨🏻‍💻 Tech Interview](https://gyoogle.dev/blog/computer-science/data-structure/Linked%20List.html)

# 개념 정리

<aside>
💡 링크드 리스트란

Data, Next 로 구성된 Node가 연결된 형태의 linear data structure

- 연속적인 위치에 저장되지 않음
</aside>

<aside>
💡 왜 씀?

1. 배열은 크기가 고정되어 요소의 수를 예측해 할당받아야 함
2. 새로운 요소의 삽입은 비용이 크다
</aside>

<aside>
💡 장점

- dynamic
- 삽입 / 삭제 용이 `O(1)`
</aside>

<aside>
💡 단점

- 임의의 indexing이 불가. 이진 검색이 불가능함 `O(N)`
- 포인터의 여분 메모리 공간이 추가적으로 필요
</aside>

# 구현

## single linked list

```jsx
1. next를 갱신할 것.
2. cursor를 갱신할 것.
3. 근데 이제 위의 2개를 tail일때에 대해 다시 고려
4. 근데 이제 위의 3개를 element가 0개, 1개일때에 대해 다시 고려
```

## double linked list

```jsx
스스로에게 물어봅시다. 내가 헷갈리지 않고 예외처리 잘 해가며 구현할 수 있을까?
1. prev를 갱신할 것.
2. next를 갱신할 것.
3. cursor를 갱신할 것.
4. 근데 이제 위의 3개를 head와 tail일 때에 대해 다시 고려
5. 근데 이제 위의 4개를 element가 0, 1, 2개일때에 대해 다시 고려
```

# Array & Dynamic Array & Linked List

<aside>
💡 세가지 자료구조의 특징

- Array : 크기가 정해져있다
- Dynamic Array: 크기가 가변적이다. - 사족) 내가 주로 사용하는 언어에서 dynamic array는 reallocation할 때 몇 배 커지는지 알면 좋다고 합니다
</aside>

# 예상 면접 질문

<aside>
💡 여기서 낸다면 자료구조 선택 관련해서 물어볼 것 같음

<aside>
💡 다음과 같은 로직을 짤건데, 어떻게 구현해야 할까요?

- 대용량 텍스트 편집기를 짤건데, 중간의 텍스트를 지우는 작업이 많습니다.
- 스크롤 할 시 현재 화면에 나온 텍스트만 불러와서 출력해야 합니다.
- find & replace 기능을 구현하려고 합니다.
- ++ 음악재생목록을 재생하는 서비스 (순차, 랜덤, 재생목록 추가)
</aside>

</aside>
