# 정렬

# 어김없이 출처는…

[거품 정렬(Bubble Sort) | 👨🏻‍💻 Tech Interview](https://gyoogle.dev/blog/algorithm/Bubble%20Sort.html)

> 그리고 이 책. 이거 강추함. 일단 재밌슴

[생각하는 프로그래밍 - 예스24](https://www.yes24.com/Product/Goods/11686227)

# 시작해봅시다

# 각종 소팅 알고리즘

## bubble sort

<aside>
💡 앞뒤 바꾸면서 하는 그거 맞습니다

장점은 `Stable sort` 라고 합니다

단점은 O(N^2)라는 숨막히는 복잡도

</aside>

!https://github.com/GimunLee/tech-refrigerator/raw/master/Algorithm/resources/bubble-sort-001.gif

## selection sort

<aside>
💡 Find min value and idx → and swap with index.

`in-place` sorting (추가 메모리 필요 x)

`unstable` 임

bubble보다 살짝 빠르다고 함

</aside>

!https://github.com/GimunLee/tech-refrigerator/raw/master/Algorithm/resources/selection-sort-001.gif

## insertion sort

<aside>
💡 swap with prev element while prev is bigger

`in-place` , `stable`

최선의 경우(이미 정렬되었을 경우) `O(N)`

_여러 하이브리드 알고리즘이 부분적으로 채택함_

</aside>

!https://github.com/GimunLee/tech-refrigerator/raw/master/Algorithm/resources/insertion-sort-001.gif

## quick sort

<aside>
💡 Divide & conquer!

`in-place`

`unstable` ← qsort가 always best가 아닌 이유.

`O(NlogN)` 나오는 좋은 알고리즘

순수 quick sort는 최악의 경우 `O(N^2)`

변형해서 quick-rank 알고리즘도 짤 수 있음

JAVA에서 사용하는 `dual pivot quick sort`의 원형

</aside>

!https://github.com/GimunLee/tech-refrigerator/raw/master/Algorithm/resources/quick-sort-001.gif

### 이건 중요하다고 생각해서 직접 짜봤음

```java
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void swap(List<Double> l, int a, int b) {
		double temp = l.get(a);
		l.set(a, l.get(b));
		l.set(b, temp);
	}

	public static void quickSort(List<Double> l, int leftInclude, int rightInclude) {
		System.out.println(leftInclude + " ~ " + rightInclude);
		if (leftInclude >= rightInclude)
			return;

		int pivot = leftInclude;

		int left = leftInclude + 1;
		int right = rightInclude;
		while(left <= right) {
			if(l.get(left) < l.get(pivot)) {
				left++;
				continue;
			}
			if(l.get(right) > l.get(pivot)) {
				right--;
				continue;
			}
			System.out.println(left + "<->" + right);
			swap(l, left, right);
			left++;
			right--;
		}
		System.out.println("escpe");
		swap(l, pivot, right);

		System.out.println(Arrays.toString(l.toArray()));

		quickSort(l, leftInclude, right - 1);
		quickSort(l, left, rightInclude);
	}


	public static void sort(List<Double> l) {
		quickSort(l, 0, l.size() - 1);
	}

	public static void main(String[] args) {
		List<Double> l = IntStream.range(0, 10).mapToDouble(e->Math.random()).boxed().collect(Collectors.toList());
		Collections.shuffle(l);
		System.out.println(Arrays.toString(l.toArray()));
		sort(l);
		System.out.println(Arrays.toString(l.toArray()));
	}
}
```

```java
[0.8960453244445725, 0.259093869693678, 0.46029029974384394, 0.05463530350640755, 0.09993095432517751, 0.8852486877195314, 0.492752584971182, 0.44279759583209044, 0.47177783761785397, 0.769305178985595]
0 ~ 9
[0.769305178985595, 0.259093869693678, 0.46029029974384394, 0.05463530350640755, 0.09993095432517751, 0.8852486877195314, 0.492752584971182, 0.44279759583209044, 0.47177783761785397, 0.8960453244445725]
0 ~ 8
5<->8
[0.44279759583209044, 0.259093869693678, 0.46029029974384394, 0.05463530350640755, 0.09993095432517751, 0.47177783761785397, 0.492752584971182, 0.769305178985595, 0.8852486877195314, 0.8960453244445725]
0 ~ 6
2<->4
[0.05463530350640755, 0.259093869693678, 0.09993095432517751, 0.44279759583209044, 0.46029029974384394, 0.47177783761785397, 0.492752584971182, 0.769305178985595, 0.8852486877195314, 0.8960453244445725]
0 ~ 2
escpe
[0.05463530350640755, 0.259093869693678, 0.09993095432517751, 0.44279759583209044, 0.46029029974384394, 0.47177783761785397, 0.492752584971182, 0.769305178985595, 0.8852486877195314, 0.8960453244445725]
0 ~ -1
1 ~ 2
[0.05463530350640755, 0.09993095432517751, 0.259093869693678, 0.44279759583209044, 0.46029029974384394, 0.47177783761785397, 0.492752584971182, 0.769305178985595, 0.8852486877195314, 0.8960453244445725]
1 ~ 1
3 ~ 2
4 ~ 6
[0.05463530350640755, 0.09993095432517751, 0.259093869693678, 0.44279759583209044, 0.46029029974384394, 0.47177783761785397, 0.492752584971182, 0.769305178985595, 0.8852486877195314, 0.8960453244445725]
4 ~ 3
5 ~ 6
[0.05463530350640755, 0.09993095432517751, 0.259093869693678, 0.44279759583209044, 0.46029029974384394, 0.47177783761785397, 0.492752584971182, 0.769305178985595, 0.8852486877195314, 0.8960453244445725]
5 ~ 4
6 ~ 6
8 ~ 8
10 ~ 9
[0.05463530350640755, 0.09993095432517751, 0.259093869693678, 0.44279759583209044, 0.46029029974384394, 0.47177783761785397, 0.492752584971182, 0.769305178985595, 0.8852486877195314, 0.8960453244445725]
```

## merge sort

<aside>
💡 이것도 div & conq

배열 반띵 → 병합할 때는 투포인터로

</aside>

<aside>
💡 Linked List에서의 정렬

quick sort → 임의 접근이 가능해야 하기 때문에 성능이 떨어짐

merge sort → 순차접근만 요구하기 때문에 좋은 해결책.

</aside>

---

## 다음으로 넘어가기 전에 알아야 할 것: comparison-based sort

<aside>
💡 Comparsion based sort와 트리의 연관성

기본적으로 comparison-based sort는 두개의 원소를 비교하는 방식으로 정렬을 수행한다.

두개의 원소를 비교한다?

(다른 뜻으로) ⇒ 알고리즘이 진행하는 방식에 경우의 수가 두개로 나뉜다

(다른 뜻으로) ⇒ 정렬 알고리즘은 이진 트리를 탐색하는 문제와 비슷하다

</aside>

<aside>
💡 comparsion sort의 시간복잡도 하한

> N개 원소의 배열이 있을 때, 이를 모두 정렬하는 가짓수는 N!임
>
> Comparison Sort를 통해 생기는 트리의 말단 노드가 N! 이상의 노드 갯수를 갖기 위해서는,
>
> 2^h >= N! 를 만족하는 h를 가져야 하고,
>
> ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/15830120-3a4c-4f90-83e7-75a9770934a5/d4d1c6d9-4220-42a6-a304-dfa9b1ac4405/Untitled.png)
>
> 에 의해 h > O(nlgn)을 가져야 한다.
>
> (h는 트리의 높이,,, 즉 Comparison sort의 시간 복잡도임)

</aside>

이런 O(nlgn)을 줄일 수 있는 방법은 Comparison을 하지 않는 것

## radix sort

<aside>
💡 자릿수별로 순서를 유지하며 정렬하면 정렬이 완료된다!

`O( d * (n + b) )`

`d: max digit`

`n:  number of e`

`b: base`

장점: 문자열과 정수 정렬 가능

단점: digit의 개념이 없다면 정렬할 수 없다 (부동소수점)

</aside>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/15830120-3a4c-4f90-83e7-75a9770934a5/ee9275c1-69c8-4ad0-889d-06f593f68661/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/15830120-3a4c-4f90-83e7-75a9770934a5/2c879634-ffc8-419d-aea2-bc9158d44885/Untitled.png)

<aside>
💡 MSD and LSD

MSD: 중간에 끊으면 “꽤 정렬된” 결과물을 얻을 수 있다

LSD: 최대 자릿수에 따라 branch가 나뉘지 않는다 (branch-free)

</aside>

## counting sort

<aside>
💡 쭈우우욱 읽고 쭈우우욱 정렬

원소의 최대 값인 k가 작을 때 사용

`O(n+k)` 의 시간복잡도를 보임

k가 클 경우 시간낭비, 공간낭비가 심함

</aside>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/15830120-3a4c-4f90-83e7-75a9770934a5/f526e775-9c48-4f31-9224-7e821d6c6dc2/Untitled.png)

---

# 기초는 아닌데 은근히 중요한 것들

## 정렬 알고리즘의 속성, `Stable Sort`에 대해

<aside>
💡 stable vs Unstable

compareTo가 equal을 뱉는다면 원래 순서를 유지하면서 정렬하는것이 좋은 속성일 것이다.

stable sort는 같은 평가값일때도 순서를 유지하면서 정렬함.

하지만 primitive value를 정렬할때는 그럴 필요까진 없지 않을까요? → Java 구현체가 그렇게 되어있다.

</aside>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/15830120-3a4c-4f90-83e7-75a9770934a5/0a32f7b0-8a6f-4aad-a725-8af2a3bf5f2c/Untitled.png)

## 각 언어별 기본 소팅 알고리즘에 대하여, 그리고 지역 참조성

<aside>
💡 지역참조성

1. Modern computer architecture는 인접 메모리를 cache에 지정하는 logic을 가집니다
2. 그러니까 지역 참조성이 좋으면 cache hit(값을 캐쉬에서 불러올 가능성)이 좋아진다는 뜻임.
3. 수학적으로는 동등한 알고리즘 (또는 오히려 비효율적인 것 처럼 보이는 알고리즘)이 practical하게는 더 우수한 성능을 보이는 현상이 나타남.

⇒ 실제로 각종 언어들에서, 라이브러리에서 사용되는 정렬 알고리즘은 지역참조성을 고려하는 경우가 많습니다

</aside>

<aside>
💡 Python

### Python - Timsort

[Tim sort에 대해 알아보자](https://d2.naver.com/helloworld/0315536)

> 요약:
>
> Insertion sort는 작은 값에 대해 정렬 성능이 좋다 `C_insert × n^2 < C_quick × nlogn`
>
> Merge sort는 큰 값에 대해 정렬 성능이 좋다
>
> ⇒ 작은 부분에 대해 insertion sort를 수행하고 덩어리들을 merge한다

</aside>

<aside>
💡 Java

### Collections.sort & Array.sort (Object array) - Timsort

`stable` 함을 전제로 하는 속성 때문에 사용.

최악의 경우에도 `O(nlogn)` 이 보장되어야 한다면 사용.

### Arrays.sort (primitive) - DualPivotQuicksort

[Dual-Pivot Quicksort Explained and Implemented with Examples in Java | Sorting Algorithms | Geekific](https://www.youtube.com/watch?v=XYVbjQXkmiI)

구현 내용은 일단 관심 안가짐. 핵심은 역시 지역참조성

</aside>

<aside>
💡 C++

### Cpp - Introsort

[[정렬] 인트로 정렬](https://justicehui.github.io/medium-algorithm/2019/03/24/IntroSort/)

</aside>

# Programming pearls에서 소개하는 상황 - 인터뷰 준비용

<aside>
💡 오늘 저녁에 집에 꽂혀있는거 간만에 좀 찾아보겠음

</aside>

### Q. 다음의 대화를 읽고 논의해봅시다:
