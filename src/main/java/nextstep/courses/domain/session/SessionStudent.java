package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.List;

public class SessionStudent {
    private int maxStudentLimit;
    private SelectionUsers selectionStudents;

    public SessionStudent(final int maxStudentLimit, final List<NsUser> nsUsers) {
        this.maxStudentLimit = maxStudentLimit;
        this.selectionStudents = SelectionUsers.convert(nsUsers);
    }

    public SessionStudent(final int maxStudentLimit, final SelectionUsers selectionStudents) {
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
        this.selectionStudents.add(user);
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

    public SelectionUsers getSelectionUsers() {
        return this.selectionStudents;
    }
}
