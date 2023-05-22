package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("답변 테스트")
public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    Question question;;
    Answer answer;

    @BeforeEach
    void init() {
        question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    }
    @DisplayName("답변이 삭제될때 질문자와 답변자가 다르면 답변 삭제가 불가능하다")
    @Test
    void deleteAnswerWithOtherQuestionOwner() {
        answer = new Answer(NsUserTest.SANJIGI, question, "answer3");
        question.addAnswer(answer);

        assertThatThrownBy(() -> {
            answer.deleteAnswer(NsUserTest.JAVAJIGI);
        })
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문자와 답변자가 달라서 삭제 할 수 없습니다.");
    }

}
