package nextstep.courses.domain.enrollment;

public enum SessionStatus {


    READY("준비중"),
    OPENED("모집중"),
    CLSOED("종료")
    ;

    private String name;

    SessionStatus(String name) {
        this.name = name;
    }

    public boolean canJoin() {
        return this.equals(OPENED);
    }
}
