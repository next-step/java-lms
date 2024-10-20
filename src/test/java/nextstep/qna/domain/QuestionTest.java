package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("글 작성자가 아닌 유저가 글을 삭제하려고 하는 경우 예외가 발생한다.")
    void shouldThrowExceptionWhenUserIsNotAuthorAndAttemptsToDeletePost() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANGHYUN))
            .hasMessage("질문을 삭제할 권한이 없습니다.")
            .isExactlyInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("글에 답변이 없는 자신의 글일 경우 삭제가 가능하다.")
    void shouldDeletePostWhenNoAnswersExist() throws CannotDeleteException {
        final DeleteHistory deleteHistory = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistory)
            .isEqualTo(
                new DeleteHistory(
                    ContentType.QUESTION,
                    Q1.getId(),
                    NsUserTest.JAVAJIGI,
                    LocalDateTime.now()
                )
            );
    }
}
