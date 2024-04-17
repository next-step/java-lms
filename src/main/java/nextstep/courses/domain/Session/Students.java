package nextstep.courses.domain.Session;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<Student> value;

    Students() {
        this.value = new ArrayList<>();
    }

    Students(List<Student> students) {
        this.value = students;
    }

    public void addAll(Students students) {
        this.value.addAll(students.getValue());
    }

    public void isPaidSessionFee(int sessionFee) {
        for (Student student : value) {
            student.isPaid(sessionFee);
        }
    }

    public List<Student> getValue() {
        return this.value;
    }

    public int getCounts() {
        return this.value.size();
    }
}
