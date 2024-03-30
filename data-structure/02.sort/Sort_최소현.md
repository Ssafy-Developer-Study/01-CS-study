# 개념

## 알고가기

---

- 정렬 고려사항
    - 시간 복잡도
    - 메모리 사용량
    - 안정성
    - 직렬 vs 병렬
- 시간/공간 복잡도는 구현방법에 따라 다르므로 절대적이지 않음!
- 공간 복잡도는 시간 복잡도와 trade-off 관계

## 기본 버전

---

### 버블 정렬 (성능: 💩)

배열내 **인접한 두개의 인덱스를 비교**하여 더 큰 숫자를 뒤로 보내어 정렬하는 방법

- 특징
    - 매싸이클마다 n번 swap
    - 안정 정렬 (앞뒤로만 swap하므로 순서 안바뀜)
- 시간 복잡도
    
    
    | 최선 | O(n) |
    | --- | --- |
    | 평균 | O(n^2) |
    | 최악 | O(n^2) |
- 공간 복잡도
    - O(1)

### 선택 정렬 (성능: ⭐)

배열을 순회하면서 **현재 위치에 들어갈 원소를 선택**하여 정렬하는 방법

- 특징
    - 매싸이클마다 1번 swap
    - 불안정 정렬 (swap과정에서 순서 바뀔수 있음)
- 시간 복잡도
    
    
    | 최선 | O(n^2) |
    | --- | --- |
    | 평균 | O(n^2) |
    | 최악 | O(n^2) |
- 공간 복잡도
    - O(1)

### 삽입 정렬 (성능: ⭐⭐)

정렬된 앞의 인덱스들과 비교하여 **자신의 자리를 찾아 삽입**하여 정렬하는 방법

- 시간 복잡도
    
    
    | 최선 | O(n) |
    | --- | --- |
    | 평균 | O(n^2) |
    | 최악 | O(n^2) |
- 공간 복잡도
    - O(1)

## 개선 버전

---

### 힙 정렬 (성능: ⭐⭐⭐)

최대 힙 또는 최소 힙을 구성하고, 힙에서 루트를 제거하면서 정렬하는 방법

- 특징
    - 불안정 정렬
- 시간 복잡도
    
    
    | 최선 | O(n) |
    | --- | --- |
    | 평균 | O(nlogn) |
    | 최악 | O(nlogn) |
    |  |  |
- 공간 복잡도
    - O(1)

### 머지 정렬 (성능: ⭐⭐⭐)

배열을 반으로 나누어 각각을 정렬하고, 정렬된 부분 배열을 합쳐 전체를 정렬하는 방법 

- 특징
    - 분할 정복법 사용 (divide and conquer)
    - 무작정 쪼갬
- 시간 복잡도
    
    
    | 최선 | O(nlogn) |
    | --- | --- |
    | 평균 | O(nlogn) |
    | 최악 | O(nlogn) |
- 공간 복잡도
    - O(n)

### 퀵 정렬 (성능: ⭐⭐⭐⭐)

피벗(pivot)을 선택하고 피벗을 기준으로 작은 원소는 왼쪽, 큰 원소는 오른쪽으로 분할하여 정렬하는 방법 

- 특징
    - 분할 정복법 사용 (divide and conquer)
    - 피벗 기준으로 쪼개
    - 데이터의 이동이 데이터의 비교에 비해 적게 발생하고, 별도의 메모리 공간 x —> 동일 빅오중 가장 빠른 정렬
    - 최악의 경우는 n^2 이지만 피벗 선택을 최선으로 함으로써 극복 가능.
- 시간 복잡도
    
    
    | 최선 | O(nlogn) |
    | --- | --- |
    | 평균 | O(nlogn) |
    | 최악 | O(n^2) |
- 공간 복잡도
    - O(logn) — 재귀 호출에 따른 스택 공간

## 특수한 상황에 유리

---

### 기수 정렬 (Radix Sort)

데이터의 각 자릿수를 낮은 자리수에서부터 가장 큰 자리수까지 올라가면서 정렬하는 방법

- 특징
    - 자릿수가 존재하지 않는 데이터를 기수정렬로 정렬하는 것은 불가능
- 시간 복잡도
    - 최선 : O(n)

### [계수 정렬 (Count Sort)](https://www.acmicpc.net/problem/10989)

정수로 표현할 수 있는 자료에 대해서 각 원소가 몇 번 나타나는지를 메모리에 저장하며 세면서 정렬하는 방식

- 특징
    - 입력 데이터의 범위가 크거나 실수형 데이터에는 적용할 수 없음
- 시간 복잡도
    - 최선 : O(n) — 최대값에 따라 달라짐

# 구현

```java
public class QuickSort {
	
	static int partition(int[] nonsorted, int left, int right) {
		int pivot = nonsorted[left];
		int low = left; int high = right+1;
		int tmp;
		
		do {
			do {
				low++;
			} while (low <= right && pivot > nonsorted[low]);
			
			do {
				high--;
			} while (high >= left && pivot < nonsorted[high]);
			
			if (low < high) {
				tmp = nonsorted[low];
				nonsorted[low] = nonsorted[high];
				nonsorted[high] = tmp;
			}
		} while (low < high);
		
		tmp = nonsorted[high];
		nonsorted[high] = nonsorted[left];
		nonsorted[left] = tmp;
		return high;
	}
	
	static void quicksort(int[] nonsorted, int left, int right) {
		if (left < right) {
			int pivot = partition(nonsorted, left, right);
			quicksort(nonsorted, left, pivot-1);
			quicksort(nonsorted, pivot+1, right);
			
		}
		
	}
	
	public static void main(String[] args) {
		int[] nonsorted = {1, 5, 2, 6, 8, 4, 2, 6, 34, 7, 6};
		quicksort(nonsorted, 0, nonsorted.length-1);
		
		for (int i = 0; i < nonsorted.length; i++) {
			System.out.printf("%d ", nonsorted[i]);
		}
	}
}
```

# 요약

### 정렬 알고리즘을 선택할 때 고려해야 할 점

- 정렬할 데이터의 양
- 이미 정렬된 정도
- 안정성의 중요도
- 사용하는 자료구조

# 지식 한스푼

### 일반적으로 quick sort가 merge sort보다 뛰어난 이유는 지역 참조성 때문?!

- 설명
    - 정렬에서 성능에 높은 영향을 주는것이 데이터의 접근이다.
    - 이 때 quick sort는 in-place 정렬이므로 한번 접근을 하면 그 이후로는 캐시에만 접근하면 됨
    - 반면, merge sort는 추가적인 메모리를 사용하기 때문에 데이터가 저장되는 메모리 주소가 바뀜. (지역 참조성이 떨어짐)
    - 따라서 동일한 빅오를 가지고 있더라도, 이 참조지역성이 뛰어나면 더 높은 성능을 보이는 것.

### linked list 자료구조로 정렬할때는 merge sort가 유리하다?!

- 이유
    - merge sort은 순차적인 비교로 정렬하기 때문에 링크 인덱스만 변경하면 돼서 데이터의 이동에 드는 비용이 매우 줄어듬
    - 배열과 달리 추가 공간을 필요로 하지 않음.
    - 반면, quicksort는 인덱스를 사용하여 배열의 임의 위치에 빠르게 접근하기 효율적으로 동작하므로 linked list로는 비효율 발생

### 언어별로 어떤 sort? — 자바, c++, python

- 일단 gpt 답변
    
    Java:
    Java의 Arrays.sort 메서드는 기본적으로 병합 정렬(Merge Sort)을 기반으로 한 팀소트(Timsort)를 사용합니다.
    
    C++:
    C++의 std::sort 함수는 퀵소트(Quicksort)를 기반으로 한 인트로소트(Introsort)를 사용합니다. 인트로소트는 퀵소트의 평균적인 빠른 속도와 힙소트의 최악의 경우에 대비한 안정성을 결합한 알고리즘입니다. IntroSort로 시작하여 입력 크기에 따라 QuickSort, HeapSort, InsertionSort 중에서 선택하여 사용합니다.
    
    Python:
    Python의 sorted 함수나 리스트의 sort 메서드는 팀소트(Timsort)를 사용합니다. 팀소트는 병합 정렬과 삽입 정렬을 결합한 알고리즘이며, 안정적이고 성능이 좋은 정렬 알고리즘 중 하나입니다.
    
- 찾아보기
    - python : tim sort
    - java7 이후 Arrays.sort
        - tim sort (객체)
        - dual-pivot quicksort (원시)
            
            : 데이터의 크기와 정렬된 정도를 따져서 선택
            
            ```
            인서트 소트 : 47개 이하
            퀵 소트 : 286개 이하, 286개 초과하며 많이 정렬된 경우
            머지 소트 : 286개 초과
            카운팅 소트 : 20개 초과 (byte 자료형 sort시) / 3200개 초과 (short, char 자료형 sort시)
            ```
            
    

### tim sort란?

- 어떻게 성능을 높이는가
    1. run 분할 (insertion 정렬)
        - 쪼갤때도 정렬을 함
        - 기존 숫자들의 정렬방식을 참고하여 정렬함으로써 성능 높임
    2. run 병합 전 (기준 충족한 경우 stack에 쌓음)
        - 시작 인덱스, 끝 인덱스만 stack에 저장
        - a < b, a + b < c 만족하면 쌓음으로써 사용하는 메모리를 O(logn)으로 줄임
    3. run 병합 과정 (Galloping mode)
        - 인덱스를 하나씩 옮겨서 비교하지 않고, 2^n으로 증가시키면서 비교 (logn)
    
    —> 일정한 패턴이 있는 일반적인 데이터에서 빠른 성능을 보여줌. 완전한 무작위면 엄청 빠른편은 아니지만 O(nlong)이므로 표준 정렬 알고리즘으로 채택하여 사용
    

# 참고

여러 구글링과 gpt 선생과 열혈 자료구조

tim sort : https://d2.naver.com/helloworld/0315536

java sort : https://taes-k.github.io/2021/04/19/java-sort/

언어별 sort : https://www.geeksforgeeks.org/know-sorting-algorithm-set-1-sorting-weapons-used-programming-languages/

intro sort : https://www.geeksforgeeks.org/introsort-cs-sorting-weapon/

# 예상 질문

- 퀵 vs 머지
    
    
- ~~ 이 상황에서 어떤 정렬이 가장 적합할까~요~?
    - 거의 정렬된 List는?
    - 병렬처리가 가능한 환경에서는?
    - 사전순 정렬은?
    - linked list or 배열에 적합한 정렬은?

# 다시 보기

collections.sort 내부 구현 

comparison-based sort 개념