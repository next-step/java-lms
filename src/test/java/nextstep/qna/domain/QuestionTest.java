package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 다른사람이작성한질문을_삭제할수없다() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(NsUserTest.SANJIGI, question, "Answers Contents2"));

        assertThatThrownBy(
                () -> question.delete(NsUserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
        }

    @Test
    void 다른사람이_작성한_답변을_삭제할수없다(){
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(NsUserTest.SANJIGI, question, "Answers Contents2"));

        assertThatThrownBy(
            () -> question.delete(NsUserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}
