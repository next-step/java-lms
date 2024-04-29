package nextstep.sessions.domain.builder;

import nextstep.sessions.domain.Student;

public class StudentBuilder {
    private final long id = 1L;
    private final long sessionId = 1L;
    private boolean isSelected;
    private boolean isApproved;

    public StudentBuilder withIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
        return this;
    }

    public StudentBuilder withIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
        return this;
    }

    public Student build() {
        return new Student(id, sessionId, isSelected, isApproved);
    }
}
