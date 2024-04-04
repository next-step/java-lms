package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fixture.qna.domain.AnswersFixture;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class AnswersTest {

    @Test
    @DisplayName("다른 사람이 쓴 답변이 있으면 CannotDeleteException 발생한다.")
    void otherOwnerException() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        Answers answers = AnswersFixture.다른_사람의_답변이_있는_답변들.getAnswers();

        assertThatThrownBy(() -> answers.delete(javajigi))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변");
    }
}
