## 요구사항
- 2단계에서 구현한 객체(도메인) 구조를 가능한 유지하면서 DB 테이블과 매핑하고, 데이터를 저장
  - CRUD 쿼리와 코드를 구현하는데 집중하기 보다 테이블을 설계하고 객체 매핑하는 부분에 집중
- 성능보다 도메인 객체에 로직 구현하는 것을 목표로 연습
- Payment 는 고려 대상에서 제외
---
## 메모
- course 에 sessions 필드 추가
- session 과 session_cover_image 테이블 설계 필요
- N:N 으로 엮이는 관계가 있음 (session 과 ns_user)
### 테이블
- course (1) → (N) sessions
- session (1) → (1) session_cover_image
- session (N) → (N) ns_user
---
## TODO
- [x] Enrollment 클래스 추가 → 다대다 관계를 풀기 위해
- [x] NsUser 를 감싸는 Student 클래스 추가 → 강의에 특화된 도메인 객체
- [x] 테이블 설계
  - [x] session
  - [ ] ~~session_cover_image~~ 
    - 별도 테이블 설계 안하고 session 테이블에서 path 정보를 저장하는 방식
  - [x] enrollment
- [ ] CRUD 코드 작성
  - 강의 신설 (session)
  - 커버 이미지 등록 (session_cover_image)
  - 수강 신청 (enrollment)
