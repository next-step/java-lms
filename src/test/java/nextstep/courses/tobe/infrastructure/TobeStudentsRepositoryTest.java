package nextstep.courses.tobe.infrastructure;

import nextstep.courses.infrastructure.CourseRepositoryTest;
import nextstep.courses.tobe.domain.*;
import nextstep.courses.tobe.domain.session.TobeStudents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.CourseTest.C1;
import static nextstep.courses.domain.session.DateRangeTest.DATE_RANGE1;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.tobe.domain.ApprovedStatus.DENIED;
import static nextstep.courses.tobe.domain.SelectedStatus.SELECTED;
import static nextstep.courses.tobe.domain.TobeCoverImageTest.TOBE_COVER_IMAGE_LIST1;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class TobeStudentsRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private TobeStudentsRepository studentsRepository;
    private TobeFreeSession freeSession;

    @BeforeEach
    void setUp() {
        studentsRepository = new TobeJdbcStudentsRepository(jdbcTemplate);
        freeSession = new TobeFreeSession(1L,
                C1.getId(),
                DATE_RANGE1,
                TOBE_COVER_IMAGE_LIST1,
                InstructorTest.IN1,
                ProcessStatus.PROCESS,
                RecruitmentStatus.OPEN,
                1L,
                START,
                START);
    }

    @Test
    void crud() {
        TobeStudents students = new TobeStudents(new TobeStudent(freeSession, JAVAJIGI, SELECTED, DENIED, START));
        students.add(new TobeStudent(freeSession, SANJIGI, SELECTED, DENIED, START));
        int freeSessionSavedCount = studentsRepository.saveAll(students);
        assertThat(freeSessionSavedCount).isEqualTo(2);

        TobeStudents savedTobeStudents = studentsRepository.findAllBySessionId(1L);
        LOGGER.info("savedTobeStudents = {}", savedTobeStudents);
        LOGGER.info("students = {}", students);

        assertThat(students.size()).isEqualTo(savedTobeStudents.size());
    }
}
