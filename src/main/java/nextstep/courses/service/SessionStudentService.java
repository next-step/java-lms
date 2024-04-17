package nextstep.courses.service;

import nextstep.courses.domain.student.SessionStudents;
import nextstep.courses.infrastructure.engine.SessionStudentRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionStudentService {

    private final SessionStudentRepository sessionStudentRepository;

    public SessionStudentService(SessionStudentRepository sessionStudentRepository) {
        this.sessionStudentRepository = sessionStudentRepository;
    }

    public void approveStudents(SessionStudents students) {
        students.toApproveStatus();
        sessionStudentRepository.updateAll(students);
    }

    public void cancelStudents(SessionStudents students) {
        students.toCancelStatus();
        sessionStudentRepository.updateAll(students);
    }

}
