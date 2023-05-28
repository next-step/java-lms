package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("삭제 완료를 체크하는 테스트")
    public void checkDeleteDone() throws CannotDeleteException {
        Question deleteQuestion = Q1.deleteQuestion(NsUserTest.JAVAJIGI);
        assertThat(deleteQuestion.isDeleted()).isTrue();
        deleteQuestion.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    @Test
    @DisplayName("글쓴이가 아닌 사람이 삭제를 눌렀을 때 익셉션 발생하는 테스트")
    public void checkDeleteException() {
        assertThatThrownBy(() -> {
            Q1.deleteQuestion(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("답글을 다른 사람이 썼을 때 익셉션 발생하는 테스트")
    public void checkDeleteException2() {
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);
        assertThatThrownBy(() -> Q1.deleteQuestion(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
