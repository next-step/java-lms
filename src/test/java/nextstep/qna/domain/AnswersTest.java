package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

class AnswersTest {

    @Test
    void 로그인_사용자와_답변_작성자_같을_경우_답변_삭제_성공() throws CannotDeleteException {
        Answers answers = new Answers(List.of(A1));
        NsUser loginUser = JAVAJIGI;
        assertThatCode(() -> answers.deleteAnswers(loginUser)).doesNotThrowAnyException();
    }

    @Test
    void 로그인_사용자와_답변_작성자_다를_경우_답변_삭제_실패() {
        Answers answers = new Answers(List.of(A2));
        NsUser loginUser = JAVAJIGI;
        assertThatThrownBy(() -> answers.deleteAnswers(loginUser)).isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
