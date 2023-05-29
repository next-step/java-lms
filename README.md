# 학습 관리 시스템(Learning Management System)

## 진행 방법

* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정

* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 코드리뷰 빠른이동

- step1 : https://github.com/next-step/java-lms/pull/37
- step2 : https://github.com/next-step/java-lms/pull/81
- step3 : null
- step4 : null

## Step1

### Step1 요구사항 정리

- 기능적 요구사항

```text
1. 삭제관련 정책 유지
  - soft delete 적용 : 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경
  - 삭제가 가능한 경우
    - 질문글 작성자와 로그인한 사용자가 같은 경우 삭제에 성공한다
    - 질문글에 답변이 달리지 않은 경우 삭제가 가능하다
    - 답변이 달린경우, 모두 질문자와 동일한 사람의 답변만 달려있는 경우에 한하여 삭제가 가능하다
  - 삭제가 불가능한 경우
    - 답변이 달린경우, 질문자 외의 타인이 답변이 존재한다면 삭제가 불가능하다
3. 이외 요구사항
   - 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 soft-delete 적용
   - 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
```

- 비기능적 요구사항

```text
- 리팩터링을 해서 클린코드로 만들기
- 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞이지 않도록 테스트가 쉬운부분과 어려운 부분의 분리
- 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현
- 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현
- QnaServiceTest의 모든 테스트는 통과해야 한다.
```

### step1 피드백 사항 정리

#### Test가 어려운 부분, 과연 테스트를 억지로 해야하나 내버려둬야하는지에 대한 피드백

- 상황설명 : Answer 에 다른 Question 연관관계를 맺으면 연관관계가 업데이트된다 를 검증해야하는 상황
    - 방법1) Question에 Answer가 추가되었는지 여부가 비즈니스상 중요하다면 contain 등의 메소드를 사용해서 검증하는것도 방법
    - 방법2) Workaround/간접테스트 >> "질문을 삭제할 때 답변 또한 삭제해야 한다" 등의 요구사항이 있으니, 이런 기능을 테스트할 때 DeleteHistory 등을 통해 Answer가 삭제되었는지를
      판별하며 정상적으로 추가되었는지도 간접적으로 테스트 가능

#### 테스트코드가 구현에 의존적인 것에 관하여 (클래스나, 메서드를 테스트하는것에 포커스가 있다)

- 전반적으로 테스트코드가 클래스, 메소드를 테스트하는 인상을 줍니다
    - (객체를 생성한다, xx를 반환한다, List를 ~ (특정 클래스 명)등..
- 테스트하고자 하는 바를 유비쿼터스 언어로 작성하기
    - 뒤집어서 말하면 구현에 의존적이게 테스트를 만들지 않아야 함
    - 어떤 `행위` or `동작` or `기능` 을 테스트해야하는지에 대해서 작성해야함
    - 함수 메서드의 입력과 출력에 대한 테스트가 아니다!

```text
넵 리뷰어님 피드백을 곰곰히 생각해보니  `테스트코드가 구현에 의존적인 것` 이 근본적인 문제인거같습니다.

어쩌면 TDD 가 아직 익숙하지 못하고 훈련이 더 필요하다는 증거인거도 같은데요. 

피드백 주신 `유비쿼터스 언어` `메서드나 클래스를 테스트하는게 아니라 기능과 요구조건을 테스트한다` 를 다음PR 에서는 잘 챙기겠습니다
```

#### 핵심 기능을 담당하는 메서드가 너무 여러가지 일을 하나의 메서드에서 처리하는 경우

- 상황설명 : Question.delete() 메서드
    - 내생각1 - 도메인 코드만 봤을때는 잘못된 구현같은데..?
        - 왜냐하면 사용자가 실수로 DeleteHistory 를 만들고 저장하지 않는 오류를 막을 수 있다..?(리턴을 해줘도 안받아서 안쓰면 그만이긴 한데..)
    - 내생각2 - Service 코드를 생각하면 좋은 코드 같기도..?
        - 왜냐하면 Service Layer 의 구현이 깔끔하고
- 피드백 1 : Question 도메인에 User/NsUser 의존성이 있는것은 문제
    - Question 도메인이 NsUser 도메인에 의존성을 갖는 부분이 문제임
- 피드백 2 : Question.delete() 의 역할이 두가지 건 인 것에 대하여
    - Question 삭제 시 sub class인 Answer를 삭제하는 책임도 같이 가지는것은 맞음 (왜냐하면 Answer의 라이프사이클은 Question에 속해 있으니까)
    - Question.delete() 메서드에서 삭제가능여부 검증 >> 취향이다
- 피드백 3 : 삭제가능여부 검증 로직이 delete() 안에 있어야하나?
    - 실제 삭제하는 행위, 삭제 가능여부를 검증하는 행위는 분리해도 된다 (선택 / 취향의 영역)
    - Question 삭제 인가에 대한 판별을 delete 메소드의 책임일때 >>  NsUser와의 결합을 줄이기 위해 NsUser가 아닌 id만 소유하도록 구성할 수도 있겠구요 (간접참조)
    - 삭제하는 행위, 삭제 가능여부를 검증하는 행위 두가지 분리시 >>정책과 같은 공통로직을 AOP 등으로 관리할 수도 있을거 같구요.

```text
피드백 감사합니다! 리뷰주신사항을 3개의 포인트로 정리해서 다음 PR 때 개선하겠습니다!

- 피드백 1 : Question 도메인에 User/NsUser 의존성이 있는것은 문제
  - UserID 만을 의존하도록 수정
- 피드백 2 : Question.delete() 의 역할에서 DeleteHistories 생성도 포함여부 
  - 현상유지 : delete() 메서드 내부에서 Sub Logic 메서드들이 잘 나뉘어 있고 분리될수 없는 하나의 동작이라 생각해서 DeleteHistories 를 리턴하는것은 유지
- 피드백 3 : 삭제가능여부 검증 로직이 delete() 안에 있어야하나
   - 현상유지 : 피드백2와 같은 이유로, Sub Logic 메서드들이 잘 나뉘어 있고 분리될수 없는 하나의 동작이라 생각해서 현상유지
```

## Step2 설계문서

### PR 메시지 정리

#### 1) 엔티티 클래스 에서의 id 를 별도클래스로 분리하였습니다.

- 단점 : 과도한 클래스 분리 && 복잡성 증가일수도 있는데,
- 장점 : sessionId, courseId, enrollId 등을 혼동을 방지할 수 있고,
- 실사용 방안 : JPA 에서는 `@Inheritance(strategy=InheritanceType.SINGLE)` 을 사용할수 있을꺼같고 쿼리맵퍼나 다른 DB 접근기술들과도 공존할수 있다고 판단했습니다..!

#### 2) 생성자 호출

- 객체 생성시 생성자를 바로 호출하지 않고, static of() 메서드를 이용해 생성하도록 수정하였습니다.
    - 생성자 수정시 소스코드 여려곳에서 에러가 나지 않도록 Object Mother (혹은 Fixture) 패턴을 적용하였습니다

#### 3) Bean Validation 을 추가하였습니다

- Null 이면 곤란한 부분 등에 대해서는 validate 메서드 구현 대신 spring 에서 지원해주는 validation 어노테이션을 사용하였습니다

#### 4) PR 과정에서 전체 테스트 통과여부를 확인할수 있는 gitaction을 추가하였습니다

- 전체 테스트가 꺠지지않고 통과하는지 Github PR 에서 확인할수 있도록 gitaction 을 추가하였습니다

#### 5) 이번 미션에서의 요구사항

- 기능적 요구사항
    - [x] 과정(Course)은 여러개의 기수(Term) 를 갖는다
    - [x] 기수(Term)는 여러 개의 강의(Session)를 갖는다
    - [x] 강의는 강의 커버 이미지 정보를 가진다
    - [x] 강의는 무료 강의와 유료 강의로 나뉜다
    - [x] 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다
    - [x] 강의 수강신청은 강의 상태가 모집중일 때만 가능하다
    - [x] 강의는 강의 최대 수강 인원을 초과할 수 없다

- 비기능적 요구사항
    - [x] 도메인 모델에 집중하자 (DB 테이블에 신경쓰지말자)
    - [x] 도메인 모델은 TDD로 구현하자 (Service 클래스는 단위 테스트가 없어도 된다)

- 피드백 사항 반영
    - [x] Question 도메인에 User 도메인 객체에 의존적인 부분 수정
        - UserId 클래스 분리
    - [x] 테스트코드가 구현(메서드, 클래스, Java 언어, 반환타입 등..)에 의존적이다
        - 테스트코드를 유비쿼터스 언어로 표현하고, 기능에 의존적인 테스트를 구현하자

#### 6) 이외

- 도메인 의사소통 용어 정리를 위해 일부 `@CommunicationTerm` 어노테이션을 추가하였습니다
    - 해당 어노테이션은 기능은 없고 단순히 커뮤니케이션 용어를 소스코드에 주석처럼 달아놓기 위함입니다
    - 단점은 `@CommunicationTerm(도메인 용어 정리)` 와 `Term.claas(과정의 기수 정보를 갖는 도메인 클래스)` 사이에는 연관이 전혀 없지만 키워드가 겹쳐서 이부분을 회피할수 있는
      방법을 고민하고 있습니다





## Step3

### DB 연결 정보 정리



- mariadb
```
FQDN : jdbc:mariadb://localhost:11010/lms
username/password : dong/dong
```

- h2
```
FQDN : jdbc:h2:tcp://localhost:1521/lms
CONSOLE : http://localhost:8081
username/password : sa/(공백, 아무것도 넣지않음)
```


### JdbcTemplete 트러블 슈팅 : Unique index or primary key violation

- 이슈의 발생과 증상
  - JdbcTemplete 로 save()기능을 구현하다 해당 에러가 발생하였습니다.  
  ```text
  PreparedStatementCallback; Unique index or primary key violation: "PRIMARY KEY ON PUBLIC.~~"
  ```

- Solution : 제 경우에는 스프링 시작시 자동으로 반영되는 `data.sql` 을 수정했습니다
  - Before
  ```text
  INSERT INTO question (question_id, writer_id, title, contents, created_at, deleted)
  VALUES (1, 1, ~~~
  ```
  - After
  ```text
  INSERT INTO question (question_id, writer_id, title, contents, created_at, deleted)
  VALUES (101, 101, ~~~
  ```
  
- Why : 문제가 발생한 원인은 PK 중복

- 이외
  - 테스트는 idempotent(멱등성) 


### JdbcTemplete 트러블 슈팅 : 

- 이슈의 발생과 증상
  - 아래의 테스트코드를 `단독으로 실행할때는 성공`하고, `전체테스트를 돌리면 실패`하는 증상이 있었습니다
  ```text
  @DisplayName("조회기능 검증한다")
  @Test
  public void findById() {
        //given
        Course savedCourse = courseRepository.save(TestFixture.K8S_COURSE);
        //when
        Course findByIdCourse = courseRepository.findById(savedCourse.getCourseId()).orElseThrow();
        //then
        LOG.info(savedCourse.toString());
        LOG.info(findByIdCourse.toString());
  }
  ```
- 원인
  - 위와 같은 증상이 발생한 원인은 `save()` 의 잘못된 구현이였습니다. 
  - 잘못구현된 save메서드
  ```text
  @Deprecated(since = "이유는 모르겠지만 뭔가 문제가있음 증상은 id 가 이상한값이 반환됨")
    public Course saveV0(Course course) {
        String sql = "insert into course (title, creator_id, created_at,updated_at) values(?, ?, ?, ?)";
        // 사실 id 가 리턴되는게 아니라 rowsAffectedCount 가 리턴되는것 이였습니다.
        int courseId = jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), course.getCreatedAt(), course.getUpdatedAt());
        //
        return new Course(
                new CourseId((long) courseId),
                course.getTitle(),
                course.getCreatorId(),
                null,
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }
  ```

- 해결 : jdbc Templete 문서를 보고 시키는데로 구현했습니다. 
  - jdbcTemplate.update() 를 사용하지는 않았는데 그 이유는 저는 `T save(T t)` 형식의 메서드 시그니쳐, 그러니까 저장된 객체를 그대로 리턴해서 DB 가 발급해준 PK 가 할당되어 돌아오는 형태로요
  - jdbc Templete 해결한 save() 코드입니다
  ```text
  public Course saveV1(Course course) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("course").usingGeneratedKeyColumns("course_id");

        Map<String,Object> sqlParameters = new HashMap<>() {{
            put("title",course.getTitle());
            put("creator_id",course.getCreatorId());
            put("created_at",course.getCreatedAt());
            put("updated_at", course.getUpdatedAt());
        }};
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(sqlParameters));
        return new Course(
                new CourseId(key.longValue()),
                course.getTitle(),
                course.getCreatorId(),
                null,
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }
  ```
  

- 결론
  - 사실은 Sava() 메서드를 잘못 구현해놨는데, 문제가 findById() 에서 발생하는것 처럼 보였습니다.
  - jdbcTemplate.update() 메서드의 리턴은 `Primary Key` 가 아니라 `Affected Rows Count` 의 의미였습니다.


- 참고
  - https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
  - https://docs.spring.io/spring-framework/reference/data-access/jdbc.html
  - 그런데 말입니다.. 이상한점이 하나 남아있습니다. 왜 메서드 하나만 테스트를 돌릴때는 왜 성공했던걸까요??
  ```text
  메서드 하나만 테스트를 돌릴때 성공했던 이유는 저장하는 Data 가 우연히 PK 가 항상 1이 되는 상황이고
  Primary Key 인줄 알았던.. 사실은 Affected Rows Count 는 항상 1이 되기 때문이였습니다
  test코드가 없었더라면 발견하지 못했을 구현오류인데, 만약에 어찌저찌해서 Product 로 올라갔다고 가정을 해보겠습니다.
  일단 에러로그는 문제가 발생하는 save() 에서 찍히는게 아니라 findById() 에서 발생을 할수도 있고 매번 발생하지는 않을수 있습니다. 
  더해서 PK 1번인 row 가 매번 고초를 겪고 데이터가 엉키는 증상이 발생할수 있습니다. 
  save() 에서 리턴받은 Entity 객체를 신뢰해서 들고 돌아다니면서 여러가지 side-effect 를 발생시킵니다
  ```