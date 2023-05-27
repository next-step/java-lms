package nextstep.qna.domain;

import nextstep.fixture.TestFixture;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.DeleteHistoryRepository;
import nextstep.qna.domain.Question;
import nextstep.qna.infrastructure.JdbcDeleteHistoryRepository;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@JdbcTest
public class DeleteHistoryRepositoryTest {

    private final DeleteHistoryRepository deleteHistoryRepository;

    public DeleteHistoryRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.deleteHistoryRepository = new JdbcDeleteHistoryRepository(jdbcTemplate);
    }

    @DisplayName("모든 DeleteHistor 를 저장한다")
    @Test
    public void saveAll() {
        //given
        Question question = TestFixture.BADAJIGI_QUESTION;
        NsUser user = TestFixture.BADAJIGI;
        List<DeleteHistory> delete = question.delete(user.getUserCode());
        //when
        deleteHistoryRepository.saveAll(delete);
        //then
        fail();
    }

    @DisplayName("모든 DeleteHistor 를 조회한다")
    @Test
    public void findAll() {
        //given
        //when
        //then
        fail();
    }

    @DisplayName("저장된 row 의 count 를 조회한다")
    @Test
    public void count() {
        //given
        //when
        //then
        fail();
    }


}