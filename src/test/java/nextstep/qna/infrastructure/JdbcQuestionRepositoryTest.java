package nextstep.qna.infrastructure;

import nextstep.qna.domain.QuestionId;
import nextstep.qna.domain.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcQuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @DisplayName("id 로 조회한다")
    @Test
    public void findByQuestionId() {
        //findById << 통합필요
        //given
        //when
        questionRepository.findByQuestionId(new QuestionId(1L));
        //then
        fail();
    }

    @DisplayName("findAll")
    @Test
    public void findAll() {
        //given
        //when
        //then
        fail();
    }
    @DisplayName("save")
    @Test
    public void save() {
        //given
        //when
        //then
        fail();
    }
}