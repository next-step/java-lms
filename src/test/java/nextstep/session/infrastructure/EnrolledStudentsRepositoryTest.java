package nextstep.session.infrastructure;

import nextstep.session.domain.student.EnrolledStudent;
import nextstep.session.domain.student.EnrolledStudents;
import nextstep.session.domain.student.EnrollmentStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class EnrolledStudentsRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnrolledStudentsRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcEnrolledStudentsRepository enrolledStudentsRepository;

    @BeforeEach
    void setUp() {
        enrolledStudentsRepository = new JdbcEnrolledStudentsRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Long sessionId = 1L;
        List<EnrolledStudent> students = new ArrayList<>();
        students.add(new EnrolledStudent(NsUserTest.JAVAJIGI.getId(), EnrollmentStatus.APPROVED));
        students.add(new EnrolledStudent(NsUserTest.SANJIGI.getId(), EnrollmentStatus.APPROVED));

        EnrolledStudents enrolledStudents = new EnrolledStudents(sessionId, students);

        int count = enrolledStudentsRepository.save(enrolledStudents);
        assertThat(count).isEqualTo(2);

        EnrolledStudents savedEnrolledStudents = enrolledStudentsRepository.findBySessionId(sessionId);
        assertThat(savedEnrolledStudents.getSessionId()).isEqualTo(enrolledStudents.getSessionId());
        assertThat(savedEnrolledStudents.size()).isEqualTo(enrolledStudents.getStudents().size());
        assertThat(savedEnrolledStudents.getStudents().get(0).getEnrollmentStatus()).isEqualTo(EnrollmentStatus.APPROVED);
        assertThat(savedEnrolledStudents.getStudents().get(1).getEnrollmentStatus()).isEqualTo(EnrollmentStatus.APPROVED);

        LOGGER.debug("enrolledStudents: {}", savedEnrolledStudents);
    }
}
