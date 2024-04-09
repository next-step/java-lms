package nextstep.courses.domain.lecture;

public enum LectureStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    END("종료");

    LectureStatus(String value) {
        this.value = value;
    }

    private String value;
}
