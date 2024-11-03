## 핵심 학습 목표
* DB 테이블이 변경될 때도 스트랭글러 패턴을 적용해 점진적인 리팩터링을 연습한다.
  * 스트랭글러(교살자) 패턴 - 마틴 파울러
  * 스트랭글러 무화과 패턴 

## 변경된 기능 요구사항
### 강의 수강신청은 강의 상태가 모집중일 때만 가능하다. 
* 강의가 진행 중인 상태에서도 수강신청이 가능해야 한다. 
  * 강의 진행 상태(준비중, 진행중, 종료)와 모집 상태(비모집중, 모집중)로 상태 값을 분리해야 한다. 
### 강의는 강의 커버 이미지 정보를 가진다.
* 강의는 하나 이상의 커버 이미지를 가질 수 있다. 
### 강사가 승인하지 않아도 수강 신청하는 모든 사람이 수강 가능하다.
* 우아한테크코스(무료), 우아한테크캠프 Pro(유료)와 같이 선발된 인원만 수강 가능해야 한다. 
  * 강사는 수강신청한 사람 중 선발된 인원에 대해서만 수강 승인이 가능해야 한다. 
  * 강사는 수강신청한 사람 중 선발되지 않은 사람은 수강을 취소할 수 있어야 한다.

## 프로그래밍 요구사항
* 리팩터링할 때 컴파일 에러와 기존의 단위 테스트의 실패를 최소화하면서 점진적인 리팩터링이 가능하도록 한다. 
* DB 테이블에 데이터가 존재한다는 가정하에 리팩터링해야 한다. 
  * 즉, 기존에 쌓인 데이터를 제거하지 않은 상태로 리팩터링 해야 한다.
    * 

## TODO
- ~~Sessions 변경~~
  - ~~ProcessStatus(READY, PROCESS, ENDED) 추가~~
    - process_status column 추가 
  - ~~RecruitmentStatus(CLOSED, OPEN) 추가~~
    - recruitment_status column 추가
  - ~~CoverImages 추가(coverImageId 필드 추가)~~
    - cover_image table 추가
    - ~~CoverImage 수정~~
  - ~~Instructor 추가~~
    - instructor table 추가
  - ~~Student 수정~~
    - ~~SelectedStatus field 추가~~
      - selected_status column 추가 
      - REJECTED(탈락)
      - SELECTED(선발)
    - ~~ApprovedStatus field 추가~~
      - approved_status column 추가
      - DENIED(승인취소)
      - APPROVED(승인)
- ~~수강신청은 RecruitmentStatus(OPEN) 일때 가능하다~~
- ~~강의는 하나 이상의 커버 이미지를 가질 수 있다.~~
- ~~학생은 수강신청을 할 수 있다.~~
- ~~강사가 선발된 인원들을 수강 승인한다~~
  - ~~Student(SelectedStatus:SELECTED) 인 경우
    Student(ApprovedStatus:DENIED -> APPROVED)) 로 변경~~
- ~~강사가 선발되지 않은 인원들을 수강 취소한다~~
  - ~~Student(SelectedStatus:REJECTED) 인 경우
    Student(ApprovedStatus:APPROVED -> DENIED)) 로 변경~~
- 리팩터링할 때 컴파일 에러와 기존의 단위 테스트의 실패를 최소화하면서 점진적인 리팩터링이 가능하도록 한다.
- 기존 domain 이 존재하는 상태에서 리팩토링 진행
  - 기존 domain, table 그대로 유지
    - 추가된 table, column 은 기존 domain 구조에 append 하는 방식
      - ex. table 내부 column 추가 : domain: column field 추가, table: column 추가
      - ex. table 하위 table 추가 : domain: tableId field 추가, table: table 추가