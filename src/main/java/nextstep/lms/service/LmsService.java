package nextstep.lms.service;

import nextstep.lms.domain.Session;
import nextstep.lms.domain.Student;
import nextstep.lms.domain.Students;
import nextstep.lms.repository.SessionRepository;
import nextstep.lms.repository.StudentsRepository;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("lmsService")
public class LmsService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "studentsRepository")
    private StudentsRepository studentsRepository;

    @Transactional
    public void enrollStudent(Payment payment) {
        Session session = sessionRepository.findById(payment.getSessionId());
        studentsRepository.save(session.enroll(payment));
    }

    @Transactional
    public void selectionStudent(Student student) {
        Students students = studentsRepository.findBySession(student.getSessionId());
        studentsRepository.updateStatus(students.selection(student));
    }

    @Transactional
    public void nonSelectionStudent(Student student) {
        Students students = studentsRepository.findBySession(student.getSessionId());
        studentsRepository.updateStatus(students.nonSelection(student));
    }
}
