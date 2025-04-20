# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 🚀 1단계 - 레거시 코드 리팩터링
- [x] question에 delete 메서드 구현
- [x] answer에 delete 메서드 구현
- [x] List<Answer> 일급컬렉션으로 변경
- [x] Answers에 delete method 구현
- [x] List<DeleteHistory> 일급컬렉션으로 변경
- [x] delete method에서 deleteHistory도 추가하도록 변경

## 🚀 2단계 - 수강신청(도메인 모델)
### 도메인 모델 설계
- Course (class)
  - [Field]
    - int generation(기수)
    - Sessions sessions(강의 리스트)
  - [Method]
    
- Sessions (class)
  - [Field]
    - List<Session> sessions
  - [Method]
- Session (class)
  - [Field]
    - int id
    - String title
    - LocalDateTime startDate
    - LocalDateTime endDate
    - long tuition
    - int currentcount
    - int capacity
    - Image coverImage
    - SessionStatus status
    - JoinStrategy joinStrategy
  - [Method]
    - boolean joinable()
- JoinStrategy (interface)
  - [Method]
    - boolean joinable(Session session, long payAmount)
- FreeJoinStrategy (class, implements JoinStrategy)
  - [Method]
    - boolean joinable(Session session, long payAmount)
- PaidJoinStrategy (class, implements JoinStrategy)
  - [Method]
      - boolean joinable(Session session, long payAmount)

- SessionStatus (Enum)
  - PREPARING(준비중)
  - RECRUITING(모집중)
  - CLOSED(종료)
  
- Image (class)
  - [Field]
    - float fileSize
    - String fileType
    - String ImageUrl
    - int width
    - int height
  - [Method]
    - boolean validateFileSize()
    - boolean validateFileType()
    - boolean validateRatio()


## 🚀 4단계 - 수강신청(요구사항 변경)
- [] SessionStatus 진행상태와 모집상태로 분리
- [] coverImage 여러개 가능하도록 기능 추가
- [] 선발 여부 확인 기능 추가
- [] 수강 승인 및 수강 취소 기능 추가