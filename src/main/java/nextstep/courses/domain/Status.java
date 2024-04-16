package nextstep.courses.domain;

public enum Status {

    PREPARE("prepare"),
    END("end"),
    RECRUIT("recruit");

    final String status;
    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
