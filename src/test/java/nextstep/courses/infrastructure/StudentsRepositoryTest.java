package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.session.Students;
import nextstep.courses.tobe.domain.TobeFreeSession;
import nextstep.courses.tobe.domain.TobeStudent;
import nextstep.courses.tobe.domain.TobeStudentsRepository;
import nextstep.courses.tobe.domain.session.TobeStudents;
import nextstep.courses.tobe.infrastructure.TobeJdbcStudentsRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.courses.domain.ApprovedStatus.DENIED;
import static nextstep.courses.domain.CourseTest.C1;
import static nextstep.courses.domain.CoverImageTest.*;
import static nextstep.courses.domain.InstructorTest.*;
import static nextstep.courses.domain.InstructorTest.IN1;
import static nextstep.courses.domain.ProcessStatus.*;
import static nextstep.courses.domain.ProcessStatus.READY;
import static nextstep.courses.domain.RecruitmentStatus.*;
import static nextstep.courses.domain.RecruitmentStatus.CLOSED;
import static nextstep.courses.domain.SelectedStatus.SELECTED;
import static nextstep.courses.domain.session.DateRangeTest.*;
import static nextstep.courses.tobe.domain.TobeCoverImageTest.TOBE_COVER_IMAGE_LIST1;
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

    @BeforeEach
    void setUp() {
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
        Status prepare = Status.PREPARE;
        CoverImage coverImage = new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);
        freeSession = new FreeSession(1L,
                C1.getId(), DATE_RANGE1,
                coverImage, prepare,
                COVER_IMAGE_LIST1, IN1, PROCESS, OPEN,
                1L, START, START);
    }

    @Test
    void crud() {
        Students students = new Students(new Student(freeSession, JAVAJIGI, SELECTED, DENIED, START));
        students.add(new Student(freeSession, SANJIGI, SELECTED, DENIED, START));
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

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    private StudentsRepository studentsRepository;
//    private FreeSession freeSession;
//
//    @BeforeEach
//    void setUp() {
//        studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
//        freeSession = new FreeSession(1L, CourseTest.C1.getId(), new DateRange(START, END),
//                new CoverImage(SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200), Status.PREPARE,
//                List.of(new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200)), IN1, READY, CLOSED,
//                1L, START, START);
//    }
//
//    @Test
//    void crud() {
//        Students students = new Students(new Student(freeSession, NsUserTest.JAVAJIGI, START));
//        students.add(new Student(freeSession, NsUserTest.SANJIGI, START));
//        int freeSessionSavedCount = studentsRepository.saveAll(students);
//        assertThat(freeSessionSavedCount).isEqualTo(2);
//
//        Students savedStudents = studentsRepository.findAllBySessionId(1L);
//        LOGGER.info("savedStudents = {}", savedStudents);
//        LOGGER.info("students = {}", students);
//
//        assertThat(students.size()).isEqualTo(savedStudents.size());
//    }
}
