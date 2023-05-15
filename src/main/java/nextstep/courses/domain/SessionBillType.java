package nextstep.courses.domain;

public enum SessionBillType {
    FREE("무료"),
    PAID("유료")
    ;

    private final String name;

    SessionBillType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
