package nextstep.courses.domain.session.feetype;

public enum FeeType {
    PAID("유료"),
    FREE("무료");

    FeeType(String value) {
        this.value = value;
    }

    private String value;

}
