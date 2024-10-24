package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문과 답변을 모두 삭제 후, 삭제 이력을 반환한다. (답변이 없는 경우)")
    @Test
    void delete() throws CannotDeleteException {
        List<DeleteHistory> actual = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(actual).extracting("contentType", "deletedBy")
                .containsExactly(new Tuple(ContentType.QUESTION, NsUserTest.JAVAJIGI));
    }

    @DisplayName("질문과 답변을 모두 삭제 후, 삭제 이력을 반환한다. (답변이 없는 경우)")
    @Test
    void deleteWithAnswer() throws CannotDeleteException {
        Q1.addAnswer(A1);

        List<DeleteHistory> actual = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(actual).extracting("contentType", "deletedBy")
                .containsExactly(
                        new Tuple(ContentType.QUESTION, NsUserTest.JAVAJIGI),
                        new Tuple(ContentType.ANSWER, NsUserTest.JAVAJIGI)
                );
    }

    @DisplayName("질문작성자와 로그인 사용자가 다른 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenDeleteByNonOwner() throws CannotDeleteException {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문작성자와 댓글 작성자가 다른 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenDeletingQuestionWithOthersAnswer() throws CannotDeleteException {
        Q1.addAnswer(A2);

        assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(RuntimeException.class);
    }

}
