package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswersTest {

    @Test
    @DisplayName("삭제를 할 경우 답변들의 삭제 상태를 변경한다.")
    void 답변들_삭제_상태_변경() throws CannotDeleteException {
        Question question = Question.of(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer1 = Answer.of(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answer2 = Answer.of(NsUserTest.JAVAJIGI, question, "Answers Contents2");

        Answers answers = Answers.from(Arrays.asList(answer1, answer2));
        answers.deleteBy(NsUserTest.JAVAJIGI);

        assertAll(
                () -> assertThat(answer1.isDeleted()).isTrue(),
                () -> assertThat(answer2.isDeleted()).isTrue()
        );
    }

}
