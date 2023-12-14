package nextstep.courses.domain.session.enums;

public enum PayType {

    FREE("무료"),
    PAY("유료");

    private String description;

    PayType(String description) {
        this.description = description;
    }
}
