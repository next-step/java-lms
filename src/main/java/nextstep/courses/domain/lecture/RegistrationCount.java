package nextstep.courses.domain.lecture;

public class RegistrationCount {

    private final int value;

    public RegistrationCount(int value) {
        this.value = value;
    }

    public boolean isValueZero(){
        return value == 0;
    }

    public int getValue() {
        return value;
    }
}
