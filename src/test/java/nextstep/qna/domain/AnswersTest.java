package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.fixture.NsUserFixture.*;
import static org.assertj.core.api.Assertions.*;

class AnswersTest {

    private static final String TEST_TILE = "test_tile";
    private static final String TEST_CONTENT = "test_content";

    @DisplayName("모든 답변은 자기 자신이 쓴 것이 아니라면 삭제시 예외가 발생한다.")
    @Test
    void if_answers_does_not_deleted_by_owner_then_throw_exception() {
        NsUser loginUser = JAVAJIGI;
        Question givenQuestion = new Question(JAVAJIGI, TEST_TILE, TEST_CONTENT);
        Answer givenAnswer1 = new Answer(JAVAJIGI, givenQuestion, TEST_CONTENT);
        Answer givenAnswer2 = new Answer(SANJIGI, givenQuestion, TEST_CONTENT);
        Answers answers = new Answers(givenAnswer1, givenAnswer2);

        assertThatThrownBy(() -> answers.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }
}