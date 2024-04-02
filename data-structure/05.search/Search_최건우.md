# 탐색.

## binary search

<aside>
💡 순차탐색과 이분탐색

`O(N) vs O(NlogN)`

진행 순서

> 원문에는 이렇게 되어있는데 난 다르게 함
>
> 1. 일단 정렬
> 2. left와 right를 평균내 mid 값 설정
> 3. 구할 값이 mid보다 높으면 `left = mid - 1`, 낮으면 `right = mid - 1`
> 4. `left > right`가 될 때까지 반복

</aside>

<aside>
💡 나는 range를 이렇게 두고 합니다: `[left include, right exclude) range`

1. `left = 0, right = len(arr)`
2. `mid = (left + right) / 2`
3. if `arr[mid] > query`:
   1. false ⇒ `left = mid`
   2. true ⇒ `right = mid`
4. `left == right - 1` 까지 반복

여러가지 변종이 있으므로 헷갈리면 안됨:

Q를 넘지 않으면서 가장 큰 원소라던가..

Q와 일치하면서 제일 첫번째로 등장하는 원소라던가…

</aside>
