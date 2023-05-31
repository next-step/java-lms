package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SessionCapacity {

    private int maximumCapacity;
    private List<NsUser> students;

    public SessionCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        this.students = new ArrayList<>();
    }

    public void addUser(NsUser user) {
        validStudentNumbers();
        validDuplicateApplication(user);
        students.add(user);
    }

    private void validDuplicateApplication(NsUser user) {
        if (students.contains(user)) {
            throw new IllegalArgumentException("이미 등록된 학생입니다");
        }
    }

    private void validStudentNumbers() {
        if (isMaximumCapacity()) {
            throw new IllegalArgumentException("정원수를 초과했습니다");
        }
    }

    private boolean isMaximumCapacity() {
        return maximumCapacity <= students.size();
    }

    public int getStudentsNumbers() {
        return students.size();
    }

    @Override
    public String toString() {
        return "SessionCapacity{" +
                "maximumCapacity=" + maximumCapacity +
                ", students=" + students +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionCapacity that = (SessionCapacity) o;
        return maximumCapacity == that.maximumCapacity && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maximumCapacity, students);
    }
}
