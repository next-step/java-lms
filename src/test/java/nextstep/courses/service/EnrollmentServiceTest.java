package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.fixture.FakeEnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class EnrollmentServiceTest {
    private EnrollmentService enrollmentService;

    private Enrollment requestedEnrollment1;
    private Enrollment requestedEnrollment2;
    private Enrollment approvedEnrollment;
    private Enrollment rejectedEnrollment;
    private Session session1;
    private Session session2;
    private Student student1;
    private Student student2;

    @BeforeEach
    public void setUp() {
        enrollmentService = new EnrollmentService(new FakeEnrollmentRepository());

        session1 = new Session(1L);
        session2 = new Session(2L);

        student1 = new Student(1L);
        student2 = new Student(2L);

        requestedEnrollment1 = Enrollment.request(session1, student1);
        requestedEnrollment2 = Enrollment.request(session1, student2);

        approvedEnrollment = Enrollment.request(session2, student2);
        approvedEnrollment.updateStatus(EnrollmentStatus.APPROVED);

        rejectedEnrollment = Enrollment.request(session2, student1);
        rejectedEnrollment.updateStatus(EnrollmentStatus.REJECTED);

        enrollmentService.save(requestedEnrollment1);
        enrollmentService.save(requestedEnrollment2);
        enrollmentService.save(approvedEnrollment);
        enrollmentService.save(rejectedEnrollment);
    }

    @Test
    public void 수강신청_건에_대한_승인() {
        enrollmentService.approve(requestedEnrollment1.getId());
        assertThat(requestedEnrollment1.getStatus()).isEqualTo(EnrollmentStatus.APPROVED);
    }

    @Test
    public void 수강신청_건에_대한_반려() {
        enrollmentService.reject(requestedEnrollment1.getId());
        assertThat(requestedEnrollment1.getStatus()).isEqualTo(EnrollmentStatus.REJECTED);
    }

    @Test
    public void 반려된_신청_건에_대한_승인시도시_예외_발생() {
        assertThatIllegalStateException()
                .isThrownBy(() -> enrollmentService.approve(rejectedEnrollment.getId()));
    }

    @Test
    public void 승인된_신청_건에_대한_반려시도시_예외_발생() {
        assertThatIllegalStateException()
                .isThrownBy(() -> enrollmentService.reject(approvedEnrollment.getId()));
    }

    @Test
    public void 수강신청_요청_상태인_건들_조회() {
        Enrollments enrollments = enrollmentService.findRequested();
        assertThat(enrollments.count()).isEqualTo(2);
    }

    @Test
    public void 특정_강의에_대한_수강신청_요청_상태인_건들_조회() {
        Enrollments requestedEnrollmentsForSession1 = enrollmentService.findRequestedBySessionId(1L);
        Enrollments requestedEnrollmentsForSession2 = enrollmentService.findRequestedBySessionId(2L);

        assertThat(requestedEnrollmentsForSession1.count()).isEqualTo(2);
        assertThat(requestedEnrollmentsForSession2.count()).isEqualTo(0);
    }
}
