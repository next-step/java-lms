# 학습 관리 시스템(Learning Management System)

## step1 기능 목록

* Question
    * [X] 삭제 가능한지 검증
    * [X] 상태값 변경
* Answers: List<Answer>를 관리하는 일급 컬렉션
    * [X] 삭제 메시지를 각각의 Answer에게 전달
* Answer
    * [X] 삭제 가능한지 검증
    * [X] 상태값 변경

## step2 기능 목록

* 피드백 반영
    * [X] 이전 단계 피드백 반영
* 수강신청(도메인 모델)
    * Session
        * [X] 강의 생성
        * [X] 수강신청 (enroll)
            * [X] 등록 가능한지 판단
            * [X] 등록
        * [X] 강의 상태 정보 업데이트

## step3 기능 목록

* 피드백 반영
    * [X] 이전 단계 피드백 반영
* 수강신청(DB 적용)
    * [X] 테이블 DDL 추가
    * Session
        * [X] session에 대한 레파지토리 추가

## step4 기능 목록

* 강의가 진행 중인 상태에서도 수강신청이 가능하도록 수정
    * [X] Session, Session에 대한 테스트 수정
    * [X] SessionRepository와 SessionRepository에 대한 수정
    * [X] SessionService 수정
    * [X] 사용하지 않는 메서드 제거
* 강의가 하나 이상의 커버 이미지를 가지도록 수정
    * [X] Session, Session에 대한 테스트 수정
    * [X] SessionRepository와 SessionRepository에 대한 수정
    * [X] 사용하지 않는 메서드 제거
* 선발된 인원만 수강 가능하도록 수정
    * [ ] Session, Session에 대한 테스트 수정
    * [ ] SessionRepository와 SessionRepository에 대한 수정
    * [ ] SessionService 수정
    * [ ] 사용하지 않는 메서드 제거