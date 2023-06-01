package nextstep.courses.domain.session;

import nextstep.courses.exception.ExceedSessionCapacityException;

public class SessionCapacity {

    private int number;

    private int numberMax;

    public SessionCapacity(int number, int numberMax) {
        this.number = number;
        this.numberMax = numberMax;
    }

    public void increaseNumber() {
        validateNumber();
        number++;
    }

    private void validateNumber() {
        if (number >= numberMax) {
            throw new ExceedSessionCapacityException();
        }
    }

    public void changeNumberMax(int numberMax) {
        this.numberMax = numberMax;
    }

    public int getNumber() {
        return number;
    }

    public int getNumberMax() {
        return numberMax;
    }
}
