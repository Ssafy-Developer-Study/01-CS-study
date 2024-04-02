# 해쉬테이블 + 그래프

### 목차

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/7a7d7a8a-1f3f-4b1a-9cbd-8996b9c6f3e5/Untitled.png)

# 해시 테이블

## 개요

<aside>
💡 해싱을 해서 테이블에 넣으니까 해시테이블입니다

</aside>

## 직접 주소 테이블

`arr[key] = value`

key의 범위가 좁고 dense할 때 사용

가장 빠르지만 `O(1)`

1. 최대 키 값을 알고 있어야 하고
2. 키 값이 골고루 분포하지 않는다면 실용적이지 않음

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/fde46d7c-362e-4b1c-9d46-aa7666b732fb/Untitled.png)

## 해시 테이블

1. 해시함수를 사용하여 특정 해시값을 알아내고
2. 그 해시값을 인덱스로 변환하여 키 값과 데이터를 저장하는 자료구조이다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/0fcc5747-9eef-45ca-a818-3354b1915594/Untitled.png)

## 해시 함수

- 정수의 경우, 간단하게 생각해볼 수 있는 해시 함수는 mod N을 하는 것.
- String은 ascii 기준으로, `H(n) = H(n-1) * p + S.charAt(n)` 방식으로 정의하는 방식도 괜찮다.
  - Java의 경우 31로 놓고 정의하고 있음

### 자바에서 hashcode를 구현할 때

> The general contract of `hashCode` is:
>
> - Whenever it is invoked on the same object more than once during an execution of a Java application, the `hashCode` method must consistently return the same integer, provided no information used in `equals` comparisons on the object is modified. This integer need not remain consistent from one execution of an application to another execution of the same application.
>     <aside>
>     💡 같은 object는 같은 hashcode를 반환해야 함
>     
>     </aside>
>
> - If two objects are equal according to the `[equals](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#equals(java.lang.Object))` method, then calling the `hashCode` method on each of the two objects must produce the same integer result.
>     <aside>
>     💡 `equals`에 의해 같다고 정의되는 object끼리는 같은 integer `hashcode`를 반환해야 함.
>     
>     </aside>
>
> - It is *not* required that if two objects are unequal according to the `[equals](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#equals(java.lang.Object))` method, then calling the `hashCode` method on each of the two objects must produce distinct integer results. However, the programmer should be aware that producing distinct integer results for unequal objects may improve the performance of hash tables.
>     <aside>
>     💡 hash table의 성능을 위해서는 not equal일 경우 서로 다른 값을 리턴하도록 설계하는 것을 권장하지만, 반드시 필요한 것은 아니다.
>     
>     </aside>
>

## 충돌, 그리고 대책

- 해시 함수는 어떤 길이의 값이 들어오더라도 고정된 값으로 집어넣습니다.
- 그렇기 때문에 다른 값이 같은 해시로 들어올 가능성 → 이것을 충돌(collision)이라고 함
- 충돌 나면 덮어씌워지는 문제가 있어서 해결책이 필요하다

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/5c915a4d-c8a8-4edc-a2d6-a9a2fa892298/Untitled.png)

### 개방 주소화 방법 (open addressing)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/2b778950-d816-4752-90a0-715238555e0f/Untitled.png)

테이블의 다음 공간이 비어있는지 확인하는 방식.

1칸씩 뛰는 방법도 있고, 1칸 4칸 9칸… 방식도 있다

### 체이닝

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/8aa3f468-5471-4451-97bc-72fc616c2f42/Untitled.png)

value를 Linked List로 이어버리는 방법.

# 그래프와 그래프 알고리즘

[What is Graph in Data Structure & Types of Graph?](https://www.simplilearn.com/tutorials/data-structure-tutorial/graphs-in-data-structure)

이거 보고 만듦

## 개요

<aside>
💡 그래프란?

Node (Vertex, 정점)와 Edge(간선)으로 이루어진 비선형 자료구조.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/2563efb6-300a-4830-8f12-53f30053d03b/Untitled.png)

</aside>

## 22.1 그래프의 표현

- 간선 리스트
  - 백준에서 간선 주는 그 방식 - 이거 난 자료구조 아니라고 생각함 암튼 그럼
- 인접 행렬
  - N x N 행렬에 (node_a, node_b) 의 형태로 가중치를 저장하는 것
- 인접 리스트
  - 연결된 간선을 각 노드에 저장하는 것.
  - 양방향 그래프를 양쪽에서 수정해줘야 함.
  - 원할 경우 간선의 정보를 array-based list로 node를 indexing 할 수도, linked list로 저장할 수도 있음.

## 강한 연결 요소

# 질문 만들기?

## 1. 다음의 코드를 보고, 의견을 내보세요.

```java
import java.util.HashMap;
import java.util.Map;

class DataObject {
	private int aTypeQuantity;
	private int bTypeQuantity;

	public DataObject(int aTypeQuantity, int bTypeQuantity) {
		this.aTypeQuantity = aTypeQuantity;
		this.bTypeQuantity = bTypeQuantity;
	}

	@Override
	public int hashCode() {
		return aTypeQuantity + bTypeQuantity;
	}
}

public class HashFunction {
	public static void main(String[] args) {
		Map<DataObject, String> m = new HashMap<>();

		for (int i = 0; i < 100; i++) {
			m.put(new DataObject(i, 100 - i), "aTypeQuantity is " + i);
		}

		m.forEach((k, v) -> System.out.println(v));
	}
}
```

## 2. 심화 - Open addressing 최적화에 관하여
