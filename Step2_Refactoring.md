### 미션 링크
https://edu.nextstep.camp/s/qlBJbqQf/ls/kuQnQbnZ

## 기능 목록
- [ ] 새로 시작하는 강의를 생성할 수 있다.
- [ ] 강의의 상태를 변경할 수 있다.
- [ ] 강의 이미지를 등록할 수 있다.
  - 이미지 크기 체크
  - 이미지 타입 체크
  - 비율 체크
- [ ] 강의 이미지를 삭제할 수 있다. (삭제시 디폴트 이미지)
- [X] 강의 이미지를 변경할 수 있다.
  - 이미지 크기 체크
  - 이미지 타입 체크
  - 비율 체크
- [X] 강의 신청할 수 있다.
  - 타입에 따른 인원수 체크
  - 시작일, 종료일 체크
  - 강의 상태 체크
- [X] 강의에 결제할 수 있다.(payments만 return)
  - 결제 금액과 수강료가 일치하는지 체크


## 클래스 설계
### Session (Course 1 : N Session)
- id
- startDate
- endDate
  - 수강 기간 이내에만 수강 신청이 가능하다.
- Cover Image
- sessionFee
- sessionCapacity
- type
  - free
    - 최대 수강 인원 제한 없음
  - paid
    - 강의 최대 수강 인원을 초과할 수 없음
    - 결제한 금액과 수강료가 일치할 때 수강 신청 가능
- status
  - PREPARING
  - OPEN
    - 수강 신청 가능한 상태
  - CLOSED

### Cover Image
- size <= 1mb
- type(gif, jpg, jpeg, png, svg)
- width <= 300
- height <= 300
- ratio 3 : 2

### Enrollment
- sessionId
- studentId
