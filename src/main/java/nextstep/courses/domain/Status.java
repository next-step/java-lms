package nextstep.courses.domain;

public enum Status {
    WAITING("준비중"),
    RECRUITING("모집중"),
    FINISH("종료");

    Status(String status) {
    }

    public void confirmRecruiting() throws CannotEnrollException {
        if(!this.equals(RECRUITING)) {
            throw new CannotEnrollException("강의가 모집중이 아닙니다.");
        }
    }
}
