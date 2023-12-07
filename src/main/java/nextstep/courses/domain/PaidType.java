package nextstep.courses.domain;

public enum PaidType {
    FREE("free"),
    PAID("paid");

    private final String paidTypeName;

    PaidType(String paidTypeName) {
        this.paidTypeName = paidTypeName;
    }
}
