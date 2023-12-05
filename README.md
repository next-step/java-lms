# 수강 신청 기능 요구사항

## Course - 과정
- 기수 단위로 운영하며, 여러 개의 강의를 가진다.
  - `Long` id(PK) 
  - `int` number(기수)
  - `Long` creatorId(강의자ID)
  - `Sessions`(강의들)
## Sessions - 강의들
- 여러 강의들을 가진다.
## Session - 강의
- 시작일과 종료일을 가진다.
- 강의 커버 이미지 정보를 가진다.
- 무료, 유료 강의로 나뉜다.
- 강의 상태를 가진다.
- 무료 강의는 최대 수강 인원 제한이 없다.
- 유료 강의는 강의 최대 수강 인원을 초과할 수 없다.
- 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
  - `Long` id(PK) 
  - `LocalDateTime` startedDate(시작일)
  - `LocalDateTime` startedDate(종료일)
  - `Image` image(커버 이미지)
  - `int` fee(강의료)
  - `int` limit(인원제한)
  - `Status` status(강의 상태, enum)
    - PREPARE, RECRUITING, CLOSED
  - `SessionType` sessionType(강의 유형, enum)
    - PAY, FREE
## Image - 커버 이미지
- 이미지 크기는 1MB 이하여야 한다.
- 이미지 타입은 gif, jpg(jpeg 포함),, png, svg만 허용한다.
- 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
  - `Long` id(PK) 
  - `long` size(크기)
  - `int` width(가로)
  - `int` height(세로)
  - `ImageType` imageType(이미지 타입, enum)
    - GIF, JPG, JPEG, PNG, SVG