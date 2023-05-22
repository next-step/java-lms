package nextstep.courses.domain;

import nextstep.courses.OverNumberOfStudentsException;

import static nextstep.courses.OverNumberOfStudentsException.*;

public class SessionStudents {

    private static final int MAX_STUDENTS = 10;

    private int numberOfStudents = 0;

    public SessionStudents() {
    }

    public void addStudent() {
        validateOverNumberOfStudents();
        this.numberOfStudents++;
    }

    private void validateOverNumberOfStudents() {
        if (numberOfStudents >= MAX_STUDENTS) {
            throw new OverNumberOfStudentsException(MESSAGE);
        }
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }
}
