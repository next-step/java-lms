package nextstep.courses.domain.session;

public class RegistrationCount {

    private int value;

    public RegistrationCount(int value) {
        this.value = value;
    }

    public void addValue() {
        value++;
    }

    public boolean isValueZero() {
        return value == 0;
    }

    public int getValue() {
        return value;
    }
}
