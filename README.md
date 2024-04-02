# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## TODO
### Step1
- [x] Question, Answer를 삭제할 권한이 있는지 검증하는 로직을 분리한다.
- [x] DeleteHistries에 추가하하는 로직을 분리한다.
- [x] TEST를 먼저 작성한다.
- [x] 상태 데이터를 꺼내지 말고 메시지를 보내도록 구현한다.
- [x] 일급 컬렉션을 적용한다.
- [X] 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- [x] 도메인 모델에 setter 메서드 추가하지 않는다.

#### 추가 고민 사항
Question 인스턴스 변수를 줄일 수 없을 지
delete 내부에서 DeleteHistories 를 생성하는 것과 실제 삭제 로직을 분리하는건 어떤지 고민해보기
validate도 마찬가지