package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.session.apply.*;
import nextstep.courses.fixture.ApplyFixtures;
import nextstep.courses.fixture.SessionFixtures;
import nextstep.qna.NotFoundException;
import nextstep.users.fixtures.NsUserFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class ApplyRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ApplyRepository applyRepository;

    @BeforeEach
    void setUp() {
        applyRepository = new JdbcApplyRepository(jdbcTemplate);
    }

    /* data.sql 파일에 이미 저장한 데이터로 테스트 합니다. */
    @Test
    void find_success() {
        Applies applies = applyRepository.findAllBySessionId(10L);

        assertThat(applies.size()).isEqualTo(2);
        LOGGER.debug("Applies: {}", applies);
    }

    @Test
    void saveApply_success() {
        Apply savedApply = applyRepository.save(ApplyFixtures.apply_one_canceled());

        Apply findApply = applyRepository
                .findApplyByNsUserIdAndSessionId(
                        NsUserFixtures.TEACHER_JAVAJIGI_1L.getId(),
                        SessionFixtures.createdFreeSession().id()
                )
                .orElseThrow(NotFoundException::new);

        assertThat(findApply.nsUserId()).isEqualTo(savedApply.nsUserId());
        assertThat(findApply.sessionId()).isEqualTo(savedApply.sessionId());
    }

    @Test
    void updateApply_success() {
        Apply savedApply = applyRepository.save(ApplyFixtures.apply_one_canceled());
        Apply updatedApply = savedApply.approve(SessionFixtures.DATETIME_2023_12_5);
        applyRepository.update(updatedApply);

        Apply findApply = applyRepository
                .findApplyByNsUserIdAndSessionId(
                        NsUserFixtures.TEACHER_JAVAJIGI_1L.getId(),
                        SessionFixtures.createdFreeSession().id()
                )
                .orElseThrow(NotFoundException::new);
        assertThat(findApply.approval()).isEqualTo(ApprovalStatus.APPROVED);
    }
}
