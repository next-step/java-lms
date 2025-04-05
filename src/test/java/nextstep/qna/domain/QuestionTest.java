package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }


    @Test
    @DisplayName("본인이 쓴 질문이고, 답변이 없으면 삭제 가능하다.")
    void canDeleteQuestionWithNoAnswerByOwner() {
        assertThatCode(() -> Q1.canDelete(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("다른 사람이 쓴 질문이면, 삭제 불가능하다.")
    public void canDeleteQuestionByOther() {
        assertThatThrownBy(() -> question.canDelete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("본인이 쓴 질문이고, 본인이 쓴 답변만 있으면 삭제 가능하다.")
    public void canDeleteQuestionWithSelfAnswerByOwner() {
        assertThatCode(() -> question.canDelete(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("본인이 쓴 질문이고, 타인이 쓴 답변이 있으면 삭제 불가능하다.")
    public void canDeleteQuestionWithOtherAnswerByOwner() {
        assertThatThrownBy(() -> question.canDelete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

}
