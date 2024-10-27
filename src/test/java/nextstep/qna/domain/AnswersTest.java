package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

public class AnswersTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("저장되어있는 답변들 중 작성자가 일치하는지 확인한다.")
    @Test
    void confirmIsOwnerAnswerTest() {
        Answers answers = new Answers();
        answers.addAnswer(A1);

        assertThatThrownBy(() -> answers.confirmIsOwnerAnswer(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답변 삭제 후 삭제이력을 반환한다.")
    @Test
    void deleteTest() {
        Answers answers = new Answers();
        answers.addAnswer(A1);

        assertThat(answers.delete(NsUserTest.JAVAJIGI))
                .extracting("contentType", "contentId", "deletedBy")
                .containsExactly(
                        tuple(ContentType.ANSWER, null, NsUserTest.JAVAJIGI)
                );
    }
}
