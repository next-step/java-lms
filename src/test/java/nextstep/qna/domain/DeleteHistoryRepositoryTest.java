package nextstep.qna.domain;

import nextstep.fixture.TestFixture;
import nextstep.qna.infrastructure.JdbcDeleteHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class DeleteHistoryRepositoryTest {

    private final DeleteHistoryRepository deleteHistoryRepository;

    public DeleteHistoryRepositoryTest(@Autowired JdbcTemplate jdbcTemplate) {
        this.deleteHistoryRepository = new JdbcDeleteHistoryRepository(jdbcTemplate);
    }

    @DisplayName("모든 DeleteHistor 를 저장한다")
    @Test
    public void saveAll() {
        //given
        Question question = TestFixture.BADAJIGI_QUESTION;
        Answer answer = TestFixture.JAVAJIGI_ANSWER;
        List<DeleteHistory> deleteHistories = List.of(
                question.toDeleteHistory(),
                answer.toDeleteHistory()
        );
        //when
        deleteHistoryRepository.saveAll(deleteHistories);
        //then
    }

    @DisplayName("모든 DeleteHistor 를 조회한다")
    @Test
    public void findAll() {
        //given
        //when
        List<DeleteHistory> deleteHistories = deleteHistoryRepository.findAll();
        //then
        assertThat(deleteHistories.size()).as("data.sql 에 작성된숫자보단 많아야함").isGreaterThanOrEqualTo(0);
    }

    @DisplayName("저장된 row 의 count 를 조회한다")
    @Test
    public void count() {
        //given
        Question question1 = TestFixture.BADAJIGI_QUESTION;
        Question question2 = TestFixture.JAVAJIGI_QUESTION;
        Answer answer1 = TestFixture.SANJIGI_ANSWER;
        Answer answer2 = TestFixture.BADAJIGI_ANSWER;
        Answer answer3 = TestFixture.JAVAJIGI_ANSWER;
        List<DeleteHistory> deleteHistories = List.of(
                question2.toDeleteHistory(),
                question1.toDeleteHistory(),
                answer1.toDeleteHistory(),
                answer2.toDeleteHistory(),
                answer3.toDeleteHistory()
        );
        //when
        Long count = deleteHistoryRepository.count();
        //then
        assertThat(count).isGreaterThanOrEqualTo(0);
    }
}