package nextstep.courses.domain.session;

public enum Status {
    // 준비중, 모집중, 종료
    PREPARING("preparing"),
    OPEN("open"),
    CLOSE("close");

    private final String type;

    Status(String type) {
        this.type = type;
    }
}
