package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.courses.domain.user.Name;
import nextstep.courses.domain.user.User;
import nextstep.courses.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;

@JdbcTest
class UserRepositoryTest {

    @Autowired
    JdbcOperations jdbcTemplate;

    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void 초기화() {
        userRepository = new JdbcUserRepository(jdbcTemplate);

        user = User.of(1L, Name.of("홍길동"));
    }

    @AfterEach
    void 리셋() {
        jdbcTemplate.update("alter table users alter column id restart with 1");
    }

    @Test
    @DisplayName("유저 정보를 삭제한다.")
    void 유저_삭제() {
        userRepository.save(user);
        assertAll(
            () -> assertThat(userRepository.delete(user)).isEqualTo(1),
            () -> assertThat(userRepository.findById(1L)).isEqualTo(User.of(0L, Name.of("")))
        );
    }

    @Test
    @DisplayName("유저 정보를 수정한다.")
    void 유저_수정() {
        userRepository.save(user);
        User saved = userRepository.findById(1L);

        User update = User.of(saved.id(), Name.of("김이박"));
        assertAll(
            () -> assertThat(userRepository.update(update)).isEqualTo(1),
            () -> assertThat(userRepository.findById(saved.id())).isEqualTo(User.of(saved.id(), Name.of("김이박")))
        );
    }

    @Test
    @DisplayName("유저 정보를 저장합니다.")
    void 유저_저장() {
        assertAll(
            () -> assertThat(userRepository.save(user)).isEqualTo(1),
            () -> assertThat(userRepository.findById(1L)).isEqualTo(user)
        );
    }

}