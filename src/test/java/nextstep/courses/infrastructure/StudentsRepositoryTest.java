package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentsRepository;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.session.Students;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static nextstep.courses.domain.ApprovedStatus.DENIED;
import static nextstep.courses.domain.CourseTest.C1;
import static nextstep.courses.domain.CoverImageTest.*;
import static nextstep.courses.domain.InstructorTest.IN1;
import static nextstep.courses.domain.ProcessStatus.PROCESS;
import static nextstep.courses.domain.RecruitmentStatus.OPEN;
import static nextstep.courses.domain.SelectedStatus.SELECTED;
import static nextstep.courses.domain.session.DateRangeTest.DATE_RANGE1;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class StudentsRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentsRepository studentsRepository;
    private FreeSession freeSession;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @BeforeEach
    void setUp() {
        createdAt = LocalDateTime.of(2024, 10, 26, 10, 0);
        updatedAt = LocalDateTime.of(2024, 11, 26, 10, 0);
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
        Status prepare = Status.PREPARE;
        CoverImage coverImage = new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);
        freeSession = new FreeSession(1L,
                C1.getId(), DATE_RANGE1,
                coverImage, prepare,
                COVER_IMAGE_LIST1, IN1, PROCESS, OPEN,
                1L, createdAt, updatedAt);
    }

    @Test
    void crud() {
        Students students = new Students(new Student(freeSession, JAVAJIGI, SELECTED, DENIED, createdAt));
        students.add(new Student(freeSession, SANJIGI, SELECTED, DENIED, createdAt));
        int freeSessionSavedCount = studentsRepository.saveAll(students);
        assertThat(freeSessionSavedCount).isEqualTo(2);

        Students savedStudents = studentsRepository.findAllBySessionId(1L);
        LOGGER.info("savedStudents = {}", savedStudents);
        LOGGER.info("students = {}", students);

        assertThat(students.size()).isEqualTo(savedStudents.size());
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("delete from student");
        jdbcTemplate.execute("ALTER TABLE student ALTER COLUMN id RESTART WITH 1");
    }
}
