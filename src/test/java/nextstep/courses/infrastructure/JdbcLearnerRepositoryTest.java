package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.infrastructure.dto.ApplyStatus;
import nextstep.courses.infrastructure.dto.LearnerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class JdbcLearnerRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcLearnerRepository learnerRepository;

    @BeforeEach
    void setUp() {
        learnerRepository = new JdbcLearnerRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("중복되지 않으면 Learner 정보가 성공적으로 저장된다")
    void save() {
        // given
        LearnerDto learnerDto = new LearnerDto(1L, 1L);

        // when
        learnerRepository.save(learnerDto);

        // then
        assertThat(learnerRepository.exists(learnerDto)).isTrue();
    }

    @Test
    @DisplayName("중복 저장 시 예외가 발생한다")
    void save_fail_for_duplicated() {
        // given
        LearnerDto learnerDto = new LearnerDto(1L, 1L);
        learnerRepository.save(learnerDto);

        // when, then
        assertThatThrownBy(() -> learnerRepository.save(learnerDto))
            .isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    @DisplayName("exists는 존재하는지 여부를 반환한다")
    void exists() {
        jdbcTemplate.update("delete from session_learner");
        LearnerDto learnerDto = new LearnerDto(1L, 1L);

        assertThat(learnerRepository.exists(learnerDto)).isFalse();

        learnerRepository.save(learnerDto);
        assertThat(learnerRepository.exists(learnerDto)).isTrue();
    }

    @Test
    @DisplayName("aceept하면 수강신청 상태를 ACCEPTED로 바꾼다")
    void accept() {
        // given
        LearnerDto learnerDto = new LearnerDto(1L, 1L, ApplyStatus.PENDING);
        learnerRepository.save(learnerDto);

        // when
        learnerRepository.accept(learnerDto.getSessionId(), learnerDto.getUserId());

        // then
        String sql = "select apply_status from session_learner where session_id = ? and user_id = ?";
        ApplyStatus applyStatus = jdbcTemplate.queryForObject(sql, ApplyStatus.class, learnerDto.getSessionId(), learnerDto.getUserId());
        assertThat(applyStatus).isEqualTo(ApplyStatus.ACCEPTED);
    }
}
