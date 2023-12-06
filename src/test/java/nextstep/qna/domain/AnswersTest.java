package nextstep.qna.domain;

import java.util.Arrays;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {

    @Test
    @DisplayName("본인이 작성한 답변글을 삭제 한다")
    void delete_all_answer() throws CannotDeleteException {
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
            "Answers Contents1");
        Answer A2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
            "Answers Contents2");
        Answer A3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
            "Answers Contents2");
        Answers answers = new Answers(Arrays.asList(A1, A2, A3));

        answers.delete(NsUserTest.JAVAJIGI);
    }
}