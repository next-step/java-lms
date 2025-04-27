package nextstep.session.domain;

public interface EnrolledStudentsRepository {
    int save(EnrolledStudents enrolledStudents);

    EnrolledStudents findById(Long id);
}
