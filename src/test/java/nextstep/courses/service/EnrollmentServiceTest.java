package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.fixture.FakeEnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class EnrollmentServiceTest {
    private EnrollmentService enrollmentService;

    private Enrollment enrollment1;
    private Enrollment enrollment2;
    private Enrollment enrollment3;
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

        enrollment1 = new Enrollment(1L, session1, student1, EnrollmentStatus.REQUESTED, LocalDateTime.now(), LocalDateTime.now());
        enrollment2 = new Enrollment(2L, session2, student2, EnrollmentStatus.APPROVED, LocalDateTime.now(), LocalDateTime.now());
        enrollment3 = new Enrollment(3L, session1, student2, EnrollmentStatus.REQUESTED, LocalDateTime.now(), LocalDateTime.now());

        enrollmentService.save(enrollment1);
        enrollmentService.save(enrollment2);
        enrollmentService.save(enrollment3);
    }

    @Test
    public void 수강신청_건에_대한_승인() {
        enrollmentService.approve(enrollment1.getId());
        assertThat(enrollment1.getStatus()).isEqualTo(EnrollmentStatus.APPROVED);
    }

    @Test
    public void 수강신청_건에_대한_반려() {
        enrollmentService.reject(enrollment1.getId());
        assertThat(enrollment1.getStatus()).isEqualTo(EnrollmentStatus.REJECTED);
    }

    @Test
    public void 수강신청_요청_상태인_건들_조회() {
        Enrollments enrollments = enrollmentService.findRequested();
        assertThat(enrollments.count()).isEqualTo(1);
    }

    @Test
    public void 특정_강의에_대한_수강신청_요청_상태인_건들_조회() {
        Enrollments requestedEnrollmentsForSession1 = enrollmentService.findRequestedBySessionId(1L);
        Enrollments requestedEnrollmentsForSession2 = enrollmentService.findRequestedBySessionId(2L);

        assertThat(requestedEnrollmentsForSession1.count()).isEqualTo(2);
        assertThat(requestedEnrollmentsForSession2.count()).isEqualTo(0);
    }
}
