package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
        "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1,
        "Answers Contents2");

    @Test
    @DisplayName("질문을 삭제한 경우 상태가 True가 된다.")
    void deleteAnswer() throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("본인이 작성한 답변을 삭제 한다.")
    void deleteAllAnswer() throws CannotDeleteException {
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
            "Answers Contents1");
        Answer A2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
            "Answers Contents2");
        Answer A3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
            "Answers Contents2");
        Answers answers = new Answers(Arrays.asList(A1, A2, A3));

        answers.deleteAnswers(NsUserTest.JAVAJIGI);
    }
}
