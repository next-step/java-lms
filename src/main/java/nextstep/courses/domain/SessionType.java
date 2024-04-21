package nextstep.courses.domain;

import java.util.Objects;

public class SessionType {

    private boolean isFree;
    private int maxStudents;
    private Long amount;

    public SessionType(boolean isFree) {
        this(isFree, 0L);
    }

    public SessionType(boolean isFree, Long amount) {
        this.isFree = isFree;
        if (isFree) {
            this.maxStudents = Integer.MAX_VALUE;
            this.amount = 0L;
        }
        if (!isFree) {
            this.amount = amount;
        }
    }

    public void setMaxStudents(int maxStudents) {
        if (isFree) {
            return;
        }
        this.maxStudents = maxStudents;
    }

    public boolean isEqualMaxStudents(int maxStudents) {
        return this.maxStudents == maxStudents;
    }

    public boolean compareStudents(Students students) {
        if (this.isFree) {
            return false;
        }
        return students.compareMaxStudents(this.maxStudents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SessionType))
            return false;
        SessionType that = (SessionType) o;
        return isFree == that.isFree && maxStudents == that.maxStudents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isFree, maxStudents);
    }
}
