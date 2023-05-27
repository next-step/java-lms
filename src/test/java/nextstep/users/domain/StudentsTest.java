package nextstep.users.domain;

public class StudentsTest {
    public static Students students = new Students();
    static {
        students.addStudent(StudentTest.student1);
    }
}
