# 트리와 탐색과 어쩌구

# 트리.

출처:

[](<https://namu.wiki/w/트리(그래프)>)

~~잘 돼있더라~~

## 0. 트리란?

<aside>
💡 (수학적인 정의) 트리는 Graph의 Subset이다.

> 트리: **회로가 없는 무향의 그래프.** ← 수학적인 정의

</aside>

<aside>
💡 (자료구조 정의) 트리는 부모-자식 관계를 갖는 노드가 간선으로 이어진 **유향** 그래프이다.

> 구성요소:
>
> - 루트 노드
>
> 노드의 구성요소:
>
> - 노드의 값
> - 자식들

</aside>

### 0-1. 용어 정리

- 노드

  - 루트 노드
  - 부모 노드
  - 자식 노드
  - 형제 노드
  - 리프 노드

- 경로
- 길이
- 깊이
- 레벨
- 높이
- 차수

- 트리의 차수
- 크기
- 너비
- 내부 정점
- 포레스트
- 방향 트리

## 1. Binary Tree

<aside>
💡 이진트리: 차수가 최대 2로 제한되는 트리.

child를 left, right로 두고 구현.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/15830120-3a4c-4f90-83e7-75a9770934a5/95f09580-5a75-44b8-8bc3-8df73314f9f9/Untitled.png)

모든 트리는 left-child, right-sibling 구조를 이용해 이진 트리로 변환할 수 있다.

</aside>

- 정 이진트리(full binary tree)란?
- 포화 이진트리(perfect binary tree)란?
- 완전 이진트리(complete binary tree)란?

## 2. Traversal

- preorder
- inorder
- postorder
- breadth-first

> 본인이 잘 이해하고 있는지, 해봅시다.
>
> - 정답
>   - In-order: 1 3 4 6 7 8 10 13 14
>   - Pre-order: 8 3 1 6 4 7 10 14 13
>   - Post-order: 1 4 7 6 3 13 14 10 8
>   - Level-order: 8 3 10 1 6 14 4 7 13

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/15830120-3a4c-4f90-83e7-75a9770934a5/59a7db0e-820e-4441-825d-a586aafebffa/Untitled.png)

## 3. Priorty Queue (heap)

<aside>
💡 complete binary tree의 일종.

항상 첫번째 원소가 max(heap)이도록 유지되는 array-based 자료구조

- 기습 질문 - heap도 트리인데 node-based면 안되는거야?
  물론 가능하지만, complete binary tree인데 굳이 node based로 해야 할 이유가 없다.

</aside>

### 힙의 구현

- 생략~ :)

## 4. Search Tree Structures

### 4-1. Binary Search Tree

BST는 모든 node에서 left child는 작게, right child는 크게 유지되는 binary tree로, 평균 `log n` 시간 안에 검색, 삽입, 삭제가 일어나는 구조.

<aside>
💡 하지만 최악의 경우 `O(N)`

순서대로 삽입될 경우 그렇게 된다.

</aside>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/15830120-3a4c-4f90-83e7-75a9770934a5/3c12212f-7c2d-4ab9-902b-92afc0e0af19/Untitled.png)

### 4-2. Unbalanced Tree & Balanced Tree, Self-Balancing Tree

<aside>
💡 Balanced tree

검색이 `O(logN)` 시간에 이루어짐.

각 leaf node의 `depth` 가 크게 차이나지 않는 트리.

</aside>

<aside>
💡 Unbalanced Tree

검색이 `O(N)` 시간에 이루어짐.

한쪽으로 쭉쭉~~

</aside>

<aside>
💡 Self-Balancing Tree

Unbalanced Tree 문제를 해결하기 위해, 노드의 연결 구조를 변경시키는 트리 구조!

앞으로 쭉 설명하겠습니다~!

</aside>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/15830120-3a4c-4f90-83e7-75a9770934a5/c8b4f5a1-57ca-411a-bb7d-8e9858b468c7/Untitled.png)

### 4-3. AVL Tree

<aside>
💡 AVL Tree는 Self-Balancing Tree다.

- 서브트리의 높이(height) 차이를 최대 1로 유지하기 때문에 균형잡힌 tree가 구성된다.
- 회전을 통해 균형을 잡아 높이 차이를 줄인다.
</aside>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/15830120-3a4c-4f90-83e7-75a9770934a5/ebfef62e-098a-4395-aabb-ae83f689e40f/Untitled.png)

### 4-4. Red-Black Tree

[Red–black tree](https://en.wikipedia.org/wiki/Red–black_tree)

### Rotation 연산

- 이거 보고 구현할 수 있으면 충분합니다~

![https://upload.wikimedia.org/wikipedia/commons/f/f2/Binary_Tree_Rotation_(animated).gif](<https://upload.wikimedia.org/wikipedia/commons/f/f2/Binary_Tree_Rotation_(animated).gif>)

### 4-5. B Tree, B+ Tree

[B-트리(B-Tree)란? B트리 탐색, 삽입, 삭제 과정](https://velog.io/@chanyoung1998/B트리)

<aside>
💡 요약

B Tree는…

1. 단일 노드에 삽입 가능한 최대 갯수의 데이터에 상한이 있고
2. 초과할 때 재조정이 일어나는 형태의 트리.
</aside>

[[자료구조] B+ Tree란?](https://velog.io/@kyeun95/자료구조-B-Tree란)

<aside>
💡 요약

B 트리의 마지막 요소에 linked list가 삽입된 형태.

다양한 RDBMS에서 널리 사용하고 있음.

</aside>

## 5. Trie

소현누나가 해줄거임~ 킼킼

# 예상 질문

## Q1. (기본) 왜 RDBMS는 B+ 트리를 쓸까요? Red-Black-tree 쓰면안됨?

[Red Black Tree versus B Tree](https://stackoverflow.com/questions/6401039/red-black-tree-versus-b-tree)

[Are red/black trees, B trees and B+ trees defunct?](https://www.quora.com/Are-red-black-trees-B-trees-and-B+-trees-defunct)

- **B+ tree와 RB tree의 속성에 대해서 생각해봅시다.**
- RB tree는 [Locality of reference](https://en.wikipedia.org/wiki/Locality_of_reference) 가 낮음. 대신 메모리를 효율적으로 쓰고 insertion이 더 빠른 경향이 있다.
- B+ tree는 저장 공간을 비교적 비효율적으로 쓰는 편. 대신 “디스크”에서의 참조 지역성이 높다. (spinning disk의 속성에 대해 생각해보면 더더욱!)

## Q2. (심화)혹시 Redis에서 무슨 자료구조 쓰는지 아는 사람?
