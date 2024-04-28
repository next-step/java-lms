package nextstep.courses.service;

import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.student.Student;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.StudentEntity;
import nextstep.courses.error.exception.NotExistSession;
import nextstep.courses.error.exception.NotExistStudent;
import nextstep.courses.infrastructure.CourseRepository;
import nextstep.courses.infrastructure.ImageRepository;
import nextstep.courses.infrastructure.SessionRepository;
import nextstep.courses.infrastructure.StudentRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class lmsService {

    private final PaymentService paymentService;

    private final CourseRepository courseRepository;

    private final SessionRepository sessionRepository;

    private final StudentRepository studentRepository;

    private final ImageRepository imageRepository;

    public lmsService(PaymentService paymentService, CourseRepository courseRepository,
        SessionRepository sessionRepository, StudentRepository studentRepository,
        ImageRepository imageRepository) {
        this.paymentService = paymentService;
        this.courseRepository = courseRepository;
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
        this.imageRepository = imageRepository;
    }

    public void enroll(Long sessionId, NsUser nsUser) {
        SessionEntity sessionEntity = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new NotExistSession(sessionId));

        Payment payment = paymentService.payment(sessionId, nsUser);

        Enrollment enrollment = sessionEntity.enrollment();
        Student student = enrollment.enroll(nsUser, payment);

        studentRepository.save(StudentEntity.from(student));
    }

    public void approveStudent(Long studentId){
        StudentEntity studentEntity = studentRepository.findById(studentId)
            .orElseThrow(() -> new NotExistStudent(studentId));

        Student student = studentEntity.toStudent();
        Student approvedStudent = student.approveStudent();

        studentRepository.updateApprovalState(studentEntity.from(approvedStudent));
    }

    public void approveCancelStudent(Long studentId){
        StudentEntity studentEntity = studentRepository.findById(studentId)
            .orElseThrow(() -> new NotExistStudent(studentId));

        Student student = studentEntity.toStudent();
        Student approvedStudent = student.approveCancelStudent();

        studentRepository.updateApprovalState(studentEntity.from(approvedStudent));
    }
}
