package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

public class SessionStudent {
    private int maxStudentLimit;
    private TotalSelectStatusUsers selectionStudents;

    public SessionStudent(final int maxStudentLimit, final TotalSelectStatusUsers selectionStudents) {
        this.maxStudentLimit = maxStudentLimit;
        this.selectionStudents = selectionStudents;
    }

    public int getCurrentStudentCount() {
        return this.selectionStudents.size();
    }

    public boolean isReachedMaxStudentLimit() {
        return this.selectionStudents.size() >= this.maxStudentLimit;
    }

    public void increaseStudentCount(final NsUser user) {
        this.selectionStudents.addUndecideUser(user);
    }

    public void changeMaxStudentLimit(final int maxStudentLimit) {
        validateMaxStudentLimit(maxStudentLimit);

        this.maxStudentLimit = maxStudentLimit;
    }

    private void validateMaxStudentLimit(final int maxStudentLimit) {
        if (maxStudentLimit < this.selectionStudents.size()) {
            throw new IllegalArgumentException("max student limit cannot be less than current student count");
        }
    }

    public int getMaxStudentLimit() {
        return this.maxStudentLimit;
    }

    public TotalSelectStatusUsers getSelectionUsers() {
        return this.selectionStudents;
    }
}
