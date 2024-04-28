package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<Student> value;

    public Students() {
        this.value = new ArrayList<>();
    }

    public Students(List<Student> students) {
        this.value = students;
    }

    public List<Student> getValue() {
        return this.value;
    }

    public int getCounts() {
        return this.value.size();
    }

    public void add(Student newStudent) {
        this.value.add(newStudent);
    }
}
