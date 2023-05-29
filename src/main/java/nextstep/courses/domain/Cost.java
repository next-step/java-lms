package nextstep.courses.domain;

public enum Cost {
    FREE("FREE"), PAID("PAID");

    private final String code;

    Cost(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
