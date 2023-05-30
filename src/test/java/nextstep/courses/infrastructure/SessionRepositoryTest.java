package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionTest;
import nextstep.courses.domain.user.Name;
import nextstep.courses.domain.user.User;
import nextstep.courses.domain.user.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;

@JdbcTest
class SessionRepositoryTest {

    @Autowired
    private JdbcOperations jdbcTemplate;

    private SessionRepository sessionRepository;

    private Session session;

    @BeforeEach
    void 초기화() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);

        session = SessionTest.TEST_SESSION_CHARGED;
        jdbcTemplate.update("INSERT INTO cover_image(id, image_path) VALUES (1, '/resources/image/test')");
        jdbcTemplate.update("insert into users(id, name) values(1, '유저1')");
        jdbcTemplate.update("insert into users(id, name) values(2, '유저2')");
    }

    @AfterEach
    void 리셋() {
        jdbcTemplate.execute("alter table sessions alter column id restart with 1");
    }

    @Test
    @DisplayName("세션 정보를 삭제한다.")
    void 세션_삭제() {
        sessionRepository.save(session);

        Session saved = sessionRepository.findById(1L);

        assertAll(
            () -> assertThat(sessionRepository.delete(saved)).isEqualTo(2),
            () -> assertThat(sessionRepository.findById(saved.id())).isEqualTo(null)
        );
    }

    @Test
    @DisplayName("세션 정보를 수정한다.")
    void 세션_수정() {
        sessionRepository.save(session);

        Session saved = sessionRepository.findById(1L);

        List<User> newUserList = new ArrayList<>(List.of(User.of(2L, Name.of("유저2"))));
        Session update = new Session.Builder()
            .id(saved.id())
            .duration(saved.duration())
            .coverImage(saved.coverImage())
            .priceType(saved.priceType())
            .status(saved.status())
            .maximumCapacity(saved.maximumCapacity())
            .users(Users.of(newUserList))
            .build();

        assertAll(
            () -> assertThat(sessionRepository.update(update)).isEqualTo(3),
            () -> assertThat(sessionRepository.findById(saved.id())).isEqualTo(update)
        );
    }

    @Test
    @DisplayName("세션을 저장한다.")
    void 세션_저장() {
        assertAll(
            () -> assertThat(sessionRepository.save(session)).isEqualTo(2),
            () -> assertThat(sessionRepository.findById(1L)).isEqualTo(session)
        );
    }
}