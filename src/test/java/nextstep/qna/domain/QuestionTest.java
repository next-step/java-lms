package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문을 삭제하는 경우 질문의 deleted의 상태가 true가 된다.")
    void deleteQuestion() throws CannotDeleteException {
        Q1.deleteQuestion(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문자와 삭제하는 사람이 다른경우 삭제가 불가능하다.")
    void cannotDeleteDifferentUser() throws CannotDeleteException{
        assertThatThrownBy(() -> Q1.deleteQuestion(NsUserTest.SANJIGI)).isInstanceOf(
            CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자와 답변자가 다른경우는 삭제가 불가능하다.")
    void cannotDeleteDifferentWriter() {
        Q2.addAnswer(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents1"));
        assertThatThrownBy(() -> Q2.deleteQuestion(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문을 삭제할때, 답변도 같이 삭제 되어야 한다.")
    void deleteQuestionWithAnswer() throws Exception {
        Answer answers_contents1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
            "Answers Contents1");

        Q1.addAnswer(answers_contents1);
        Q1.deleteQuestion(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(answers_contents1.isDeleted()).isTrue();
    }

}
