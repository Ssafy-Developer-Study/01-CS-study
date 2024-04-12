### 서론

짧게 할겁니다… 중요한건 운영체제

---

# [1] Introduction

## 01-1 컴퓨터 구조를 알아야 하는 이유

> **컴퓨터 구조는 실력 있는 개발자가 되려면 반드시 알아야 할 기본 지식**입니다. 컴퓨터 구조를 이해하면 얻을 수 있는 장점에 대해 알아보겠습니다.
> 

✅ 문제 해결 능력

✅ 성능, 용량, 비용을 고려하는 능력

## 01-2 컴퓨터 구조의 큰 그림

### 컴퓨터가 이해하는 정보 : 데이터 + 명령어

- 데이터: 작업하는 원본 정보
- 명령어: 컴퓨터에게 어떻게 데이터를 처리할지 알려주는 지시사항
- ⇒ 두가지가 컴퓨터를 작동시킨다

### 컴퓨터의 네가지 핵심 부품 : CPU, 메모리(RAM), 보조기억장치, 입출력장치

- CPU: 프로그램이 요구하는 모든 처리작업을 담당
- 메모리(RAM): 임시로 저장하는 휘발성 저장장치
- 보조기억장치: 비휘발성
- I/O Device: 사용자와 정보를 주고받는 역할

### 메모리

!https://velog.velcdn.com/images/hwanee/post/6ab3f0bd-96ee-4d24-955e-3cfdbbe123c0/image.png

### CPU

!https://velog.velcdn.com/images/hwanee/post/35af6c4f-45c6-490f-919b-c9f22371b0ba/image.png

### 보조기억장치

- **하드 드라이브(HDD)**: 자기 디스크를 사용하여 데이터를 저장합니다. 용량이 크고 가격이 저렴하지만, SSD에 비해 읽기/쓰기 속도가 느립니다.
- **솔리드 스테이트 드라이브(SSD)**: 플래시 메모리를 사용하여 데이터를 저장합니다. HDD에 비해 빠른 속도를 제공하지만, 비용이 더 높습니다.
- **필요한 개념**
    - 인터페이스, 파일 시스템, 캐싱 및 버퍼링의 개념

### 입출력장치

- I/O(입출력) 디바이스는 시스템과 외부 세계 사이의 데이터 교환을 담당하는 장치
    - 버스, 컨트롤러, 인터럽트, DMA의 개념
- 컴퓨터 시스템은 운영체제의 지원을 받아 I/O device를 관리함
    - 드라이버를 통화 쉽게 사용할 수 있도록 추상화

### 메인 보드

- 컴퓨터의 중요한 구성요소들이 물리적으로 결합된 장치
- 필요한 개념
    - CPU 통신
    - 메모리 관리
    - 확장성
    - 저장장치 접근
    - 주변기기 연결
    - 전원 분배, 바이오스 펌웨어

### 시스템 버스의 내부 구성

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/e767dd1d-7330-4952-a9aa-c16bf66091f6/Untitled.png)

- 데이터 버스
    - `CPU가 한번에 전송 가능한 데이터 크기 == 데이터버스 크기`
- 주소 버스
    - `주소버스의 비트 수 == 기억장치의 주소 수`
    - 주소버스는 단방향 버스이다(주소를 전달만 해서)
- 제어 버스
    - 제어 신호를 전송하는 통로
        - `기억장치 쓰기 신호`
        - `기억장치 읽기 신호`
        - `I/O 쓰기 신호`
        - `I/O 읽기 신호`
- `버스의 대역폭 = 버스의 클럭 주파스 * 데이터 버스의 크기`

# [2] 0과 1로 표현하기

## 02-1. 0과 1로 숫자를 표현하는 방법

### 정보의 단위

`bit` ⇒ `Byte` ⇒ `KB` ⇒ `…`

`KB`와 `KiB`의 차이는 1024 단위인지 1000 단위인지에 따라 다름…

- `WORD` 단위에 대해 아십니까?
    - `CPU가 한번에 처리할 수 있는 정보의 크기단위`
    - 이지만… 레거시와 어쩌구 때문에 환경마다 다르다 (알잘딱깔센)

### 이진법

`Decimal vs Binary`

- 음수 표현?
- 2의 보수
    - 어떤 수를 그보다 큰 `2^n` 에서 뺀 값
    - 0과 1을 뒤집고 1을 더한 값
- sign bit로 양수 음수 구분

### 십육진법

- `0123456789ABCDEF`

## 02-2. 0과 1로 문자를 표현하는 방법

### 문자 집합과 인코딩

- 문자 집합
    - 컴퓨터가 이해할 수 있는 문자 모음
- 인코딩
    - 부호화 / 문자를 binary code로
- 디코딩
    - 거꾸로

### 아스키 코드(ASCII)

- `7bit`로 표현 (8번째 bit는 오류 검출을 위한 `parity bit`로 남겨뒀음)
- `code point` : 문자에 부여된 값
- 너무 표현 범위가 작긴 해

### 한글 인코딩 : 완성형 vs 조합형

!https://velog.velcdn.com/images/hwanee/post/23b8305c-2100-481c-be7e-d292638eee71/image.png

- 한글의 특징 때문에…

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/b475d9fd-c24a-48d3-bdef-2c2e76595ace/787cad33-f975-4c95-bd59-feda83f325fa/Untitled.png)

- EUC-KR
    - 완성형 인코딩, 글자 하나에 `2byte`
    - 뷃, 뙠, 휔 같은 한글 표현 불가

### 유니코드 문자 집합과 utf-8

- UNICODE - 통일된 문자 집합
- UTF-8 : 1바이트~2바이트~3바이트~4바이트 방식으로 가변 길이 인코딩 함
    - 유럽, 중동권 2바이트, 아시아권 3바이트
- UTF-16:
    - 2바이트~3바이트~4바이트 사용

# [3] 명령어

## 03-1. 소스 코드와 명령어

### 고급 언어와 저급 언어

!https://velog.velcdn.com/images/hwanee/post/4796e605-e10f-4218-ad15-1402f7da9ebe/image.png

### 고급 언어

- C언어 같은거

### 저급 언어(명령어)

- 어셈블리어 → 기계어를 사람이 읽기 좋은 instruction으로 변형해 주는 것

### 고급 언어 → 저급 언어

!https://velog.velcdn.com/images/hwanee/post/fe6e5ee2-e8c8-4bd8-9a79-fd932f4c6a25/image.png

### 컴파일 언어

### 인터프리터 언어

- 인터프리터 언어가 칼같이 구분되는게 아닙니다

## 03-2. 명령어의 구조

- 명령어는 연산 코드와 오퍼랜드로 구성
- 기계어나 어셈은 연산코드 + 오퍼랜드로 이루어짐

### 연산 코드

- 더하라, 빼라, 대입해라 `ADD SUB DIV FDIV MOV`

### 오퍼랜드

- 데이터 또는 데이터의 위치 `R4 FFFF`

### 명령어 주소 지정 방식

!https://velog.velcdn.com/images/hwanee/post/0d68410d-0d26-4f55-83d6-45e3b0178653/image.png

- 연산 코드 / 오퍼랜드 / 오퍼랜드
- ex ⇒ `4bit, 6bit(64종), 6bit(64종)` ⇒ 4바이트 instruction
- or ⇒ `4bit 4bit(16종) 4bit 4bit`
- 오퍼랜드에 저장된 레지스터 번호를 따라 가면 거기에 연산에 사용할 데이터 OR 주소가 있음

### 유효 주소(effective address)

- 데이터가 저장된 위치
- 명령어 주소 지정 방식으로 찾음. 대표적인 방식 을 아라보자

### 명령어 주소 지정 방식(addressing modes)

### 1. Immediate addressing mode 즉시 주소 지정 방식

- 오퍼랜드 필드에 직접 명시 → 데이터의 크기가 작아질 수는 있지만 빠르다

**Example**

!https://velog.velcdn.com/images/hwanee/post/db540afa-f0ad-484b-bb04-fabb4042f9f0/image.png

### 2. Direct addressing mode 직접 주소 지정 방식

- 오퍼랜드 필드에 유효 주소 직접 명시 → 연산 코드만큼 유효주소 크기가 줄어든다

**Example**

!https://velog.velcdn.com/images/hwanee/post/2a197c72-f39f-4830-8aec-be0fa1f0cae8/image.png

### 3. Indirect addressing mode 간접 주소 지정 방식

- 오퍼랜드 필드에 유효 주소가 저장된 주소를 명시
    - 앞선 주소 지정방식에 비해 느릴 수도 있다.

**Example**

!https://velog.velcdn.com/images/hwanee/post/bfdaefc0-0658-41b6-883b-577e5fb67fac/image.png

### 4. Register addressing mode 레지스터 주소 지정 방식

**Example**

!https://velog.velcdn.com/images/hwanee/post/4d1a2537-29fd-433d-a8ec-7783fc5f85d6/image.png

### 5. Register indirect addressing mode 레지스터 간접 주소 지정 방식

**Example**

!https://velog.velcdn.com/images/hwanee/post/a03b23d7-0cff-4f1d-883e-5149514d2c69/image.png

## 03-3. C 언어의 컴파일 과정

!https://velog.velcdn.com/images/hwanee/post/d88440a1-2d98-4e0f-ba2d-af9ae24c2cc7/image.png

### Preprocessor

전처리기가 `#pragma`, `#include`, `#define`  등의 구문 처리 후 확장

`test.c` → `test.i`

### Compiler

컴파일 수행 후 어셈블리로 된 파일을 내뱉

`test.i` → `test.s`

### Assembler

어셈블리를 기계어로 변환

`test.s` → `test.o`

### Linker

목적 파일을 합쳐서  exe 파일루

`test.o` → `test.exe`

## 03-3. Python의 컴파일 과정

!https://velog.velcdn.com/images/hwanee/post/5af75798-e07a-4bf4-9e3e-b60f010fbac4/image.png

### Compiler

`test.py` → `test.pyc`

### PVM (Python Virtual Machine)

```
동시에Just-In-Time (JIT)등의 기술 개발 및컴퓨팅 하드웨어의 발전은 이전의 C, C++ 수준의 수행속도를 Python 및 Java 등에서 가능하게 해주고 있다.
```
