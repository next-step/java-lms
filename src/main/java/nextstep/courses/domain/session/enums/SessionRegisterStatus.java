package nextstep.courses.domain.session.enums;

public enum SessionRegisterStatus {
    WAITING("대기중"),
    ACCEPT("승인됨");

    private String name;

    SessionRegisterStatus(String name) {
        this.name = name;
    }

}
