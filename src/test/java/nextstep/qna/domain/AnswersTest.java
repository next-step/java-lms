package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.QuestionTest.Q1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.*;

public class AnswersTest {
    @Test
    @DisplayName("로그인 사용자와 답변자가 다를 경우")
    void delete_exception() {
        assertThatThrownBy(() -> {
            Q1.addAnswer1(A2);
            Q1.getAnswers1().delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class).hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
