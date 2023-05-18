package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문 삭제 요청을 나타내는 필드 표시")
    void question_delete_mark() {
        Q1.delete();

        assertThat(Q1.isDeleted())
                .isTrue();
    }

    @Test
    @DisplayName("질문을 삭제할 권한이 없으면 exception")
    void check_authority_to_delete() {
        assertThatThrownBy(() -> Q1.checkAuthorityToDelete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자와 답변글의 답변자가 다른 경우 exception")
    void check_answer_to_delete() {
        final NsUser nsUser = new NsUser(1L, "testId", "password", "test", "test@email.com");
        final Question question = new Question(nsUser, "testTitle", "testContents");
        question.addAnswer(new Answer(1L, NsUserTest.JAVAJIGI, question, "testContents"));

        assertThatThrownBy(() -> question.checkAnswerToDelete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문 삭제 테스트")
    void delete_question() {
        Q1.addAnswer(new Answer(1L, NsUserTest.JAVAJIGI, Q1, "testContents"));

        assertThat(Q1.delete())
                .hasSize(2);
        assertThat(Q1.isDeleted())
                .isTrue();
    }
}
