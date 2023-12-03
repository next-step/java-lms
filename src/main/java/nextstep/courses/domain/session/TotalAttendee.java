package nextstep.courses.domain.session;

import nextstep.courses.exception.ExceedAttendeesException;
import nextstep.courses.exception.NegativeNumberException;
import nextstep.courses.exception.NegativeOrZeroNumberException;

import java.util.Objects;

public class TotalAttendee {

    private final int totalNumber;

    private final int currentAttendees;

    public TotalAttendee(int totalNumber) {
        validateTotalNumber(totalNumber);
        this.totalNumber = totalNumber;
        this.currentAttendees = 0;
    }

    public TotalAttendee(int totalNumber, int currentAttendees) {
        validateTotalNumber(totalNumber);
        validateCurrentAttendees(currentAttendees);
        this.totalNumber = totalNumber;
        this.currentAttendees = currentAttendees;
    }

    private void validateTotalNumber(int totalNumber) {
        if (totalNumber <= 0) {
            throw new NegativeOrZeroNumberException();
        }
    }

    private void validateCurrentAttendees(int currentAttendees) {
        if (currentAttendees < 0) {
            throw new NegativeNumberException();
        }
    }

    public TotalAttendee add() {
        validateExceedTotalNumber();
        return new TotalAttendee(this.totalNumber, this.currentAttendees+1);
    }

    private void validateExceedTotalNumber() {
        if (this.currentAttendees >= this.totalNumber) {
            throw new ExceedAttendeesException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalAttendee that = (TotalAttendee) o;
        return totalNumber == that.totalNumber && currentAttendees == that.currentAttendees;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalNumber, currentAttendees);
    }
}
