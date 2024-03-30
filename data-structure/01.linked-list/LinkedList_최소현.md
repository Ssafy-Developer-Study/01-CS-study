# 개념

## LinkedList
 
> 각 노드는 데이터 필드와 다음 노드에 대한 참조로 구성되어 있고, 각 노드가 서로 연결되어 있는 자료형**

## Java에서의 LinkedList

> Deque 인터페이스 혹은 List 인터페이스를 구현한 Collection 구현체

** 참고) Java의 컬렉션 프레임워크에 구현된 LinkedList 클래스는 doubly linked list로 구현 되어 있음 **

### 장점

- 메모리상에 연속으로 저장되지 않으므로, **메모리 관리**가 용이 (성능X, 편의O)
- 즉, **삽입과 삭제가 용이**

### 단점

- 삽입, 삭제시 O(1) 이지만, 중간에 요소를 삽입하는 경우에는 사실상 O(n) 소요. —> 보완하기 위해 이중연결리스트 사용
- 즉, **탐색 속도가 느림**
- 노드 간의 포인터 연결로 인해 캐시 효율이 떨어질 수 있음

### 동적 / 정적 시간차이

* 노드풀을 두어 관리하는 방식(정적)이 더 빠를것이라 생각했으나, 자바에서는 비슷함.
* 이유는, JVM이 이미 노드풀을 두어 관리하는 방식을 사용하기 때문.

### 단순 연결 / 이중 연결 시간차이

- double linked list를 성능적으로 올릴려면, 추가적인 로직이 필요함
    
    **커서를 두어야함!**
    

# 참고

[https://inpa.tistory.com/entry/JAVA-☕-LinkedList-구조-사용법-완벽-정복하기#:~:text=LinkedList의 가장 큰 특징,를 변경하기만하면 된다](https://inpa.tistory.com/entry/JAVA-%E2%98%95-LinkedList-%EA%B5%AC%EC%A1%B0-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%99%84%EB%B2%BD-%EC%A0%95%EB%B3%B5%ED%95%98%EA%B8%B0#:~:text=LinkedList%EC%9D%98%20%EA%B0%80%EC%9E%A5%20%ED%81%B0%20%ED%8A%B9%EC%A7%95,%EB%A5%BC%20%EB%B3%80%EA%B2%BD%ED%95%98%EA%B8%B0%EB%A7%8C%ED%95%98%EB%A9%B4%20%EB%90%9C%EB%8B%A4).

# 예상 질문

- Array vs ArrayList vs LinkedList
    
    참고) ArrayList에는 원소가 선형적으로 저장되는 것이 아니라, 원소의 참조값이 선형적으로 저장되어 잇음! 따라서, 순차리스트라고 하기에 애매할지도?? 진정한 순차리스트는 array!!
    
    > int, char 등 원시 클래스는 원소값이 저장되어 있음. 따라서 어떤 타입을 저장하냐에 따라 다름.
    
- double linked list 는 언제 유리한가?
    
    > 커서가 있어야 한다!