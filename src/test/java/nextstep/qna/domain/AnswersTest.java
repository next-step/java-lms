package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AnswersTest {

    private Answers answers;

    @BeforeEach
    void setUp() {
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        answers = Answers.from(List.of(A1, A2));
    }

    @Test
    @DisplayName("답변들의 작성자와 삭제를 요청한 대상이 다르면 예외 처리를 한다")
    void deleteNotCorrectUser() {
        Assertions.assertThrows(CannotDeleteException.class, () -> answers.delete(NsUserTest.SANJIGI), "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}
