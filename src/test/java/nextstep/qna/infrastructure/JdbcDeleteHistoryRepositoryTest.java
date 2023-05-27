package nextstep.qna.infrastructure;

import nextstep.fixture.TestFixture;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.DeleteHistoryRepository;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class JdbcDeleteHistoryRepositoryTest {

    @Autowired
    private DeleteHistoryRepository deleteHistoryRepository;

    @DisplayName("모든 DeleteHistor 를 저장한다")
    @Test
    public void saveAll() {
        //given
        Question question = TestFixture.BADAJIGI_QUESTION;
        NsUser user = TestFixture.BADAJIGI;
        List<DeleteHistory> delete = question.delete(user);
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