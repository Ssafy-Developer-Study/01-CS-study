# 입출력 장치

### 정의
컴퓨터와 사용자 사이의 정보를 교환할 수 있도록 하는 장치

- USB 메모리, SSD, 키보드, 마우스 등이 있음
- 이 때, 보조기억장치도 입출력장치가 될 수 있음

### 특징

- 데이터 전송률이 CPU, 메모리에 비해 낮음 
- 즉, CPU-메모리가 입출력장치와 데이터를 주고 받기 위해서는 싱크를 맞출 필요가 있음 
    —> 장치 컨트롤러 

# 장치 컨트롤러

### 정의
입출력 장치와 정보를 주고받기 위한 인터페이스 역할의 **하드웨어**

### 역할
- CPU와 입출력장치 간의 통신 중개
- 데이터 버퍼링
- 오류 검출

### 구조
![캡처1](https://github.com/Ssafy-Developer-Study/CS-study/assets/64201367/4533bffd-5a62-4d4d-b291-b5fea50d130c)
- 데이터 레지스터
    - CPU와 입출력장치 사이에 주고받을 데이터가 담기는 레지스터
- 상태 레지스터
    - 상태 정보 저장 (입출력 작업 준비여부, 작업 완료여부, 오류여부)
- 제어 레지스터
    - 입출력장치가 수행할 내용에 대한 제어 정보 (명령)

# 장치 드라이버

### 정의

장치 컨트롤러의 동작을 감지하고 제어하는 **프로그램**

### 구조
![캡처2](https://github.com/Ssafy-Developer-Study/CS-study/assets/64201367/01201dad-6a8b-43d8-bbca-e9cc382af7ae)

# 프로그램 입출력

- 프로그램 명령어로 입출력장치를 제어하는 방법
- 즉, **명령어** —> 장치 컨트롤러와 상호작용

## 방법1. 메모리 맵 입출력
- 입출력 장치 접근을 위한 명령어가 딱히 없음   
![캡처3](https://github.com/Ssafy-Developer-Study/CS-study/assets/64201367/f417eceb-9779-440c-9826-819bee9105bf)

## 방법2. 고립형 입출력
- 입출력장치 읽기/쓰기 라인을 제어버스에 추가하여, 접근하도록 함
![캡처4](https://github.com/Ssafy-Developer-Study/CS-study/assets/64201367/48849f41-78bb-453d-9f5e-11e1d7575e29)

# 인터럽트 기반 입출력
### 구조
![캡처5](https://github.com/Ssafy-Developer-Study/CS-study/assets/64201367/7c9dffe5-7710-48f8-8683-aae4ab3bdae6)
- 우선순위가 높은 인터럽트를 먼저 처리
    —> PIC (Programmable Interrut Controller)
    - 여러 장치 컨트롤러와 연결되어, 장치 컨트롤러의 인터럽트간 우선순위를 판단
      ![캡처6](https://github.com/Ssafy-Developer-Study/CS-study/assets/64201367/a95a8048-1659-4877-92c1-c9273e9addd7)

# DMA(Direct Memory Access) 입출력
- CPU를 거치지 않고 입출력장치가 메모리에 직접적으로 접근하는 기능

### 입출력 과정
![캡처7](https://github.com/Ssafy-Developer-Study/CS-study/assets/64201367/68a3e2af-1bb5-4904-8dd4-f01b21b38f66)   
—> CPU는 입출력 작업의 **시작과 끝** 만 관여

### PCIe (PCI express)
- 컴퓨터 하드웨어 간에 데이터를 전송하는 고속 인터페이스 표준
- PCIe 슬롯을 통해 입출력 버스에 연결
