## RAM

- 휘발성 저장 장치
- 용량과 성능은 비례하지만, 필요 이상이 되면 증가하지 않음

- DRAM (Dynamic RAM)
    - 일반적으로 사용하는 RAM으로, 소비 전력이 낮고 저렴하고 집적도가 높음
    - 대용량 설계에 용이
    - 리프레시 사이클로 인해 속도가 느림 (SRAM 대비)
- SRAM (Static RAM)
    - 저장된 데이터가 변하지 않는 RAM
    - 집적도가 낮고 소비 전력이 크며 가격이 비쌈
    - 대용량은 아니지만 속도가 빨라야하는 저장장치 (**캐시 메모리**) 에 사용
- SDRAM (Synchronous Dynamic RAM)
    - 클럭 신호와 동기화된 발전된 형태의 DRAM
    - DRAM에서 속도가 개선
- DDR SDRAM (Double Data SDRAM)
    - 최근 가장 흔히 사용하는 RAM
    - 대역폭을 넓혀 속도를 빠르게 만든 SDRAM
    

## 메모리의 공간

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/2e1dc2d7-ab73-4426-bd42-ea5511afa0c1/f2e23882-8b33-4c64-a43c-2f2fafeb6dd1/Untitled.png)

- 물리 주소 : 메모리 하드웨어가 사용하는 주소
- 논리 주소 : CPU와 실행중인 프로그램이 사용하는 주소
- MMU (Memory Management Unit)에 의해 논리주소가 물리주소로 맵핑됨
    - 원리
        
        논리 주소 + 베이스 레이스터 값
        
- 메모리 보호 기법
    - 한계 레지스터
    실행중인 프로그램의 논리 주소 영역을 벗어나면 안되므로 범위 체크 
    (베이스 레지스터 ≤ x ≤ 베이스 레지스터 + 한계 레지스터)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/2e1dc2d7-ab73-4426-bd42-ea5511afa0c1/3042530b-25bb-4c9f-8180-1a4c96669781/Untitled.png)
    

## 캐시 메모리

- CPU와 메모리 사이에 위치하며, 레지스터보다 용량이 크고 메모리보다 빠른 SRAM 기반의 저장 장치
- 코어와 가까운 순서대로 L1 → L2 → L3 (메모리 용량은 반비례)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/2e1dc2d7-ab73-4426-bd42-ea5511afa0c1/48de1902-8917-4091-b749-cd8f5722913a/Untitled.png)

- 참조 지역성
    - 캐시 메모리의 용량은 작으므로, 예측을 잘 해야함.
    - 자주 사용될 것으로 예측한 데이터가 실제로 맞아떨어지면 **캐시히트**, 아니면 **캐시미스**
    - 일반적인 캐시의 적중률은 대략 85~90 % —> 비법은 참조 지역성의 원리
    
    ```
    캐시 적중률 = 캐시 히트 횟수 / (캐시 히트 횟수 + 캐시 미스 횟수)
    ```
    
    - 원리
        
        1) 시간 지역성
        
        2) 공간 지역성