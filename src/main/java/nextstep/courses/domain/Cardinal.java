package nextstep.courses.domain;

public class Cardinal {
    private Long id;
    private final Session session;
    private Students students;

    public Cardinal(Long id, Session session, Students students) {
        this.id = id;
        this.session = session;
        this.students = students;
    }

    public void enroll(Student student) throws CannotEnrollException {
        session.checkAbleSession();
        students.addStudent(student);
    }
}
