package nextstep.lms.enums;

public enum PricingTypeEnum {
    FREE("무료"),
    PAID("유료");

    private String type;

    PricingTypeEnum(String type) {
        this.type = type;
    }
}
