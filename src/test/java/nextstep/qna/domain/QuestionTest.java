package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private Question javajigiQuestion;
    private Question sanjigiQuestion;
    private Answer javajigiAnswer;
    private Answer sanjigiAnswer;

    @BeforeEach
    void beforeEach() {
        javajigiQuestion = new Question(NsUserTest.JAVAJIGI, "Javajigi title", "Javajigi Question");
        sanjigiQuestion = new Question(NsUserTest.SANJIGI, "sanjigi title", "sanjigi Question");
        javajigiAnswer = new Answer(NsUserTest.JAVAJIGI, javajigiQuestion, "javajigi Answers");
        sanjigiAnswer = new Answer(NsUserTest.SANJIGI, sanjigiQuestion, "sanjigi Answers");
    }

    @Test
    void delete() {
        javajigiQuestion.addAnswer(javajigiAnswer);
        assertThat(javajigiQuestion.delete(NsUserTest.JAVAJIGI)).isEqualTo(
            List.of(
                new DeleteHistory(ContentType.QUESTION, javajigiQuestion.getId(), NsUserTest.JAVAJIGI,
                    LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, javajigiAnswer.getId(), NsUserTest.JAVAJIGI,
                    LocalDateTime.now())
            )
        );
        assertThat(javajigiAnswer.isDeleted()).isTrue();
        assertThat(javajigiQuestion.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> javajigiQuestion.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void delete_다른_사람이_쓴_답변() {
        javajigiQuestion.addAnswer(sanjigiAnswer);
        assertThatThrownBy(() -> javajigiQuestion.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}
