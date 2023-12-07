package nextstep.courses.domain;

public enum LengthUnit {
    PIXEL("px");

    private final String unitSymbol;

    LengthUnit(String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }

    public String toSymbol() {
        return this.unitSymbol;
    }
}
