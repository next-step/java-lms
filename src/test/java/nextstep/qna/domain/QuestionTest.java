package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private Question question;
    private Answer answer;


    @BeforeEach
    public void setUp() {
        question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = new Answer(11L, NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void assertValidateQuestionWriterForDelete() {
        Exception exception = assertThrows(CannotDeleteException.class, () -> {
            question.validateForDelete(NsUserTest.SANJIGI);
        });
        String expectedMessage = "질문을 삭제할 권한이 없습니다.";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void assertDeleteQuestion() {
        List<DeleteHistory> deleteHistories =  question.deleteQuestion();

        assertThat(deleteHistories.get(0).wasThisDeletedBy(NsUserTest.JAVAJIGI, ContentType.QUESTION)).isTrue();
    }
}
