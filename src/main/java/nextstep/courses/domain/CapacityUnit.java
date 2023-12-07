package nextstep.courses.domain;

public enum CapacityUnit {
    KB("KB");

    private final String unitSymbol;

    CapacityUnit(String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }

    public String toSymbol() {
        return this.unitSymbol;
    }
}
