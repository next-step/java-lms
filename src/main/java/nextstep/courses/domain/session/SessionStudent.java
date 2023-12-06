package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionStudent {
    private int maxStudentLimit;
    private List<NsUser> students = new ArrayList<>();
    private SelectionUsers selectionStudents;

    public SessionStudent(final int maxStudentLimit, final List<NsUser> nsUsers) {
        this.maxStudentLimit = maxStudentLimit;
        this.students.addAll(nsUsers);
        this.selectionStudents = SelectionUsers.convert(nsUsers);
    }

    public SessionStudent(final int maxStudentLimit, final SelectionUsers selectionStudents) {
        this.maxStudentLimit = maxStudentLimit;
        this.selectionStudents = selectionStudents;
    }

    public int getCurrentStudentCount() {
        return this.students.size();
    }

    public int getCurrentStudentCount2() {
        return this.selectionStudents.size();
    }

    public boolean isReachedMaxStudentLimit() {
        return this.students.size() >= this.maxStudentLimit;
    }

    public boolean isReachedMaxStudentLimit2() {
        return this.selectionStudents.size() >= this.maxStudentLimit;
    }

    public void increaseStudentCount(final NsUser user) {
        this.students.add(user);
    }

    public void increaseStudentCount2(final NsUser user) {
        this.selectionStudents.add(user);
    }

    public void changeMaxStudentLimit(final int maxStudentLimit) {
        validateMaxStudentLimit2(maxStudentLimit);

        this.maxStudentLimit = maxStudentLimit;
    }

    private void validateMaxStudentLimit(final int maxStudentLimit) {
        if (maxStudentLimit < this.students.size()) {
            throw new IllegalArgumentException("max student limit cannot be less than current student count");
        }
    }

    private void validateMaxStudentLimit2(final int maxStudentLimit) {
        if (maxStudentLimit < this.selectionStudents.size()) {
            throw new IllegalArgumentException("max student limit cannot be less than current student count");
        }
    }

    public int getMaxStudentLimit() {
        return this.maxStudentLimit;
    }

    public List<NsUser> getUsers() {
        return this.students;
    }
}
