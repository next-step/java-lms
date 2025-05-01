package nextstep.session.domain.student;

public interface EnrolledStudentsRepository {
    int save(EnrolledStudents enrolledStudents);

    EnrolledStudents findById(Long id);
}
