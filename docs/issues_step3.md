# 3단계 - 수강신청(DB 적용)
## DB 매핑
- 테이블과 객체가 1:1 관계가 아님
  - 테이블과 같은 정보를 가진 DTO 객체를 생성 후 각 객체에 매핑
### Session 조회
- session 테이블
- session_enrollment 테이블
- image 테이블
### Session 리팩토링
- 기존 Session 생성자가 과도하게 많은 파라미터를 사용
- Session 객체는 자식 객체를 생성자로 받고, DTO를 통하여 자식 객체를 생성