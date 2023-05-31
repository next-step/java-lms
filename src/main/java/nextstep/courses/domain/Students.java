package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<Student> students = new ArrayList<>();

    private Students() {
    }

    public static Students from() {
        return new Students();
    }

    public static Students fromStudentNumber(int number) {
        Students students = Students.from();

        for(int i = 0; i < number; i++){
            students.add(Student.from());
        }

        return students;
    }

    public int number() {
        return students.size();
    }

    public void add(Student student) {
        students.add(student);
    }
}
