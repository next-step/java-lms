package nextstep.courses.infrastructure;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentsRepository;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.session.Students;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class StudentsRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentsRepository studentsRepository;
    private FreeSession freeSession;

    @BeforeEach
    void setUp() {
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
        freeSession = new FreeSession(1L,
                1L,
                CourseTest.C1.getId(),
                new DateRange(START, END),
                new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT),
                Status.PREPARE,
                START,
                START);
    }

    @Test
    void crud() {
        Students students = new Students(new Student(freeSession, NsUserTest.JAVAJIGI, START));
        students.add(new Student(freeSession, NsUserTest.SANJIGI, START));
        int freeSessionSavedCount = studentsRepository.saveAll(students);
        assertThat(freeSessionSavedCount).isEqualTo(2);

        Students savedStudents = studentsRepository.findAllBySessionId(1L);
        LOGGER.info("savedStudents = {}", savedStudents);
        LOGGER.info("students = {}", students);

        assertThat(students.size()).isEqualTo(savedStudents.size());
    }
}
