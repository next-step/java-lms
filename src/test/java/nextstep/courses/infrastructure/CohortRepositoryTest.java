package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import nextstep.courses.domain.Cohort;
import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.session.CohortRepository;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.SessionTest;
import nextstep.courses.domain.user.Name;
import nextstep.courses.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;

@JdbcTest
public class CohortRepositoryTest {

    private final Logger logger = LoggerFactory.getLogger(CohortRepositoryTest.class);

    @Autowired
    private JdbcOperations jdbcTemplate;

    private CohortRepository cohortRepository;

    private Cohort cohort;

    @BeforeEach
    void 초기화() {
        cohortRepository = new JdbcCohortRepository(jdbcTemplate);
        cohort = Cohort.of(1L, CourseTest.TEST_COURSE, "1기", List.of(SessionTest.TEST_SESSION_CHARGED));

        tableIdAutoIncrementValueReset();

        new JdbcCourseRepository(jdbcTemplate).save(CourseTest.TEST_COURSE);
        new JdbcCoverImageRepository(jdbcTemplate).save(CoverImage.of(1L, "/resources/image/test"));
        JdbcSessionRepository jdbcSessionRepository = new JdbcSessionRepository(jdbcTemplate);
        jdbcSessionRepository.save(SessionTest.TEST_SESSION_CHARGED);
        jdbcSessionRepository.save(SessionTest.TEST_SESSION_SECOND);
        new JdbcUserRepository(jdbcTemplate).save(User.of(1L, Name.of("유저1")));
    }

    private void tableIdAutoIncrementValueReset() {
        jdbcTemplate.execute("alter table course alter column id restart with 1");
        jdbcTemplate.execute("alter table sessions alter column id restart with 1");
        jdbcTemplate.execute("alter table cover_image alter column id restart with 1");
        jdbcTemplate.execute("alter table users alter column id restart with 1");
        jdbcTemplate.execute("alter table cohort alter column id restart with 1");
    }

    @Test
    @DisplayName("기수 정보를 삭제한다.")
    void 기수_삭제() {
        cohortRepository.save(cohort);

        Cohort saved = cohortRepository.findById(1L);
        assertAll(
            () -> assertThat(cohortRepository.delete(saved)).isEqualTo(2),
            () -> assertThat(cohortRepository.findById(1L)).isEqualTo(null)
        );
    }

    @Test
    @DisplayName("기수 정보를 수정한다.")
    void 기수_수정() {
        cohortRepository.save(cohort);

        Cohort saved = cohortRepository.findById(1L);

        Cohort update = Cohort.of(
            saved.id(),
            saved.course(),
            saved.title(),
            List.of(SessionTest.TEST_SESSION_SECOND)
        );

        assertAll(
            () -> assertThat(cohortRepository.update(update)).isEqualTo(3),
            () -> assertThat(cohortRepository.findById(saved.id())).isEqualTo(update)
        );
    }

    @Test
    @DisplayName("기수 정보를 저장한다.")
    void 기수_저장() {
        assertAll(
            () -> assertThat(cohortRepository.save(cohort)).isEqualTo(2),
            () -> assertThat(cohortRepository.findById(1L)).isEqualTo(cohort)
        );
    }
}
