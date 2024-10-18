package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {
    @Test
    @DisplayName("삭제할 답변이 존재하지 않음")
    void deleteEmpty() throws CannotDeleteException {
        Answers answers = new Answers(new ArrayList<Answer>());
        assertThat(answers.delete(NsUserTest.JAVAJIGI)).isEmpty();
    }

    @Test
    @DisplayName("질문자와 다른 답변자의 답변 존재")
    void delete_validation() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));
        assertThatThrownBy(()->answers.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class).hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("질문자와 다른 답변자 동일")
    void delete_normal() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));
        assertThat(answers.delete(NsUserTest.JAVAJIGI).get(0)).isEqualTo(new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(),AnswerTest.A1.getWriter(), LocalDateTime.now()));
    }

}
