## 용어 정리
### 해시함수(Hash Functioon)   
* 임의 크기의 데이터를 고정된 크기의 값(해시 값 또는 해시 코드)으로 매핑하는 함수

### 해시값(Hash Value) / 해시코드(Hash Code) / 해시(Hash)   
* 해시 함수에 의해 생성된 값

### 로드 팩터 (Load Factor)
* 해시 테이블에 저장된 데이터 개수 n을 버킷의 개수 k로 나눈 것 (n / k)

* 1 (혹은 다른 정의된 임계값) 이면 해시 테이블이 꽉 찬 것이고, 1보다 크면 해시 충돌이 발생 했음을 의미

### 재해싱(Rehashing)
* 로드팩터의 값을 넘었을때 해시테이블의 크기를 조정하고 모든 요소를 새로운 해시테이블로 이동시키는 과정

## 해시
- 임의의 크기를 가진 데이터(Key)를 고정된 크기의 데이터(Value)로 변화시켜 저장하는 것
    
- 고려해야 할 것
    - 데이터의 양, 로드 팩터 —> 공간 크기
    - 해시 함수
    - 해시값 충돌 처리

## 해시 테이블
### 직접 주소 테이블
* key의 범위가 좁고 dense할 때 사용
* 가장 빠르지만 충돌 가능성이 없어야 함.

### 해시 테이블
* index와 value 값 연결해 데이터를 저장하는 자료구조

## 충돌 해결
1. Chaining
    - 충돌 발생시 데이터들을 포인터를 이용해 체인 형태로 연결
    - 단점) 최악의 경우 O(n) —> 자료구조로 O(logn)으로 극복 가능
2. Open Addressing
    - 충돌 발생시 다른 버킷 사용
    - 종류
        - Linear Probing : i ← i + k
        - Quadratic Probing :  i ← i^2
        - Double Hashing (이중 해싱)
            - 두개의 hash function을 사용하는 것
            - key를 저장할 index를 찾기 위한 놈, 충돌 발생시 저장할 index를 찾기 위한 놈

## 자바 API

### Java8의 HashMap

- HashMap 은 **chaining 방식**을 사용하며 해당 **버킷의 길이**에 따라 ****LinkedList에서 **Tree**(Red-Black Tree)로 변경
- 로드 팩터 값을 넘길 경우, 기존 사이즈의 **2배** 증가 시킴
- 내부 코드
    
    ```jsx
        /**
         * The default initial capacity - MUST be a power of two.
         */
        static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    
        /**
         * The maximum capacity, used if a higher value is implicitly specified
         * by either of the constructors with arguments.
         * MUST be a power of two <= 1<<30.
         */
        static final int MAXIMUM_CAPACITY = 1 << 30; 
    
        /**
         * The load factor used when none specified in constructor.
         */
        static final float DEFAULT_LOAD_FACTOR = 0.75f; // 75프로 채워지면 자동 확장
    
        /**
         * The bin count threshold for using a tree rather than list for a
         * bin.  Bins are converted to trees when adding an element to a
         * bin with at least this many nodes. The value must be greater
         * than 2 and should be at least 8 to mesh with assumptions in
         * tree removal about conversion back to plain bins upon
         * shrinkage.
         */
        static final int TREEIFY_THRESHOLD = 8; // 이진트리로 변환하는 임계
    
        /**
         * The bin count threshold for untreeifying a (split) bin during a
         * resize operation. Should be less than TREEIFY_THRESHOLD, and at
         * most 6 to mesh with shrinkage detection under removal.
         */
        static final int UNTREEIFY_THRESHOLD = 6; // 일반 연결 리스트로 변환하는데 사용되는 임계
    ```
    

### HashSet
- HashMap 사용
- 내부 코드
    ```java
        // Dummy value to associate with an Object in the backing Map
        private static final Object PRESENT = new Object();
    
        /**
         * Constructs a new, empty set; the backing {@code HashMap} instance has
         * default initial capacity (16) and load factor (0.75).
         */
        public HashSet() {
            map = new HashMap<>();
        }
    
    		 /**
         * Adds the specified element to this set if it is not already present.
         * More formally, adds the specified element {@code e} to this set if
         * this set contains no element {@code e2} such that
         * {@code Objects.equals(e, e2)}.
         * If this set already contains the element, the call leaves the set
         * unchanged and returns {@code false}.
         *
         * @param e element to be added to this set
         * @return {@code true} if this set did not already contain the specified
         * element
         */
        public boolean add(E e) {
            return map.put(e, PRESENT)==null;
        }
    ```
  
## 파이썬 API

### Dictionary

- 오픈 어드레싱 방식 —>특정 크기에 상응하는 Index의 배열 (무작위)을 만들고 이 순서에 따라 Probing
- 로드 팩터 값(2/3 = 0.67)을 작게 설정하여 성능 저하 문제를 해결