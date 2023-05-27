package nextstep.courses.domain.session;

public enum PriceType {
    CHARGED("유료"),
    FREE("무료");

    private String description;

    PriceType(String description) {
        this.description = description;
    }
}
