# 학습 관리 시스템(Learning Management System) - 2단계
## 요구사항
- [] 과정(Course)은 기수 단위로 운영하며, 여러 개의 강의(Session)를 가질 수 있다.
- [] 강의는 시작일과 종료일을 가진다.
- [] 강의는 강의 커버 이미지 정보를 가진다.
- [x] 이미지 크기는 1MB 이하여야 한다.
- [x] 이미지 타입은 gif, jpg(jpeg 포함),, png, svg만 허용한다.
- [x] 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
- [] 강의는 무료 강의와 유료 강의로 나뉜다.
- [] 무료 강의는 최대 수강 인원 제한이 없다.
- [] 유료 강의는 강의 최대 수강 인원을 초과할 수 없다.
- [] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
- [] 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.
- [] 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
- [] 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
- [] 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.

### 이미지
- long id - 아이디
- int size() - 크기(KB단위)
- enum type - 타입
- int width - 너비
- int height - 높이
- [x] 크기는 1024 KB 이하여야된다.
- [x] width는 300픽셀이상
- [x] height는 200픽셀이상
- [x] width/height 비율은 3:2여야 된다.

### 이미지 타입(enum)
- [x] 타입은 gif, jpg, jpeg, png, svg 허용

### 강의 상태(enum) - SessionState
- [] 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.

### 강의
- long id
- String startDay
- String endDay
- SessionState sessionState
- Image sessionImage
- boolean free
- Payment sessionPayment
- List<User> user
- [] 시작일과 종료일이 존재한다.
- [] 이미지 커버를 가진다.
- [] 유료/무료 강의로 나뉜다.
- [] 무료 강의는 인원수 제한이 없다.
- [] 유료 강의는 최대 수강인원을 넘을 수 없다.
- [] 유료 강의는 결제 금액과 수강료가 일치할 때만 수강신청이 가능하다.
- [] 강의 수강신청은 모집중일때만 가능하다.

### 결제
- [] 결제 정보는 Payment 도메인에 담겨온다.


### 과정
- [] 기수 단위로 운영된다.
- [] 여러 강의를 가질 수 있다.


