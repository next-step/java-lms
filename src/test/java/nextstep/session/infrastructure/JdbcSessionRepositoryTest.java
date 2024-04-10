package nextstep.session.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.session.domain.*;
import nextstep.session.dto.SessionDto;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private Resolution resolution;
    private ImageFilePath imageFilePath;
    private Course course;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);

        resolution = new Resolution(300, 200);
        imageFilePath = new ImageFilePath("/home", "mapa", "jpg");
        course = new Course("Course1", 1L, 3);
    }

    @Test
    void saveAndFindForFreeSession() {
        // given
        Session session = new FreeSession(
                1L,
                new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)),
                new Cover(resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI),
                "얼른 배우자 객체지향",
                course,
                new Tutor(NsUserTest.JAVAJIGI)
        );

        // when
        long savedId = sessionRepository.save(session.toDto());
        SessionDto sessionDto = sessionRepository.findById(savedId);

        // then
        assertThat(sessionDto.getId())
                .isEqualTo(savedId);
    }

    @Test
    void saveAndFindForPaidSession() {
        // given
        Session session = new PaidSession(
                1L,
                new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)),
                new Cover(resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI),
                "얼른 배우자 객체지향",
                course,
                100,
                1_000_000L,
                new Tutor(NsUserTest.JAVAJIGI)
        );

        // when
        long savedId = sessionRepository.save(session.toDto());
        SessionDto sessionDto = sessionRepository.findById(savedId);

        // then
        assertThat(sessionDto.getId())
                .isEqualTo(savedId);
    }
}
