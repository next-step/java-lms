package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    public static final Question Q3 = new Question(NsUserTest.SANGHYUN, "title3", "contents3");

    @Test
    @DisplayName("글 작성자가 아닌 유저가 글을 삭제하려고 하는 경우 예외가 발생한다.")
    void shouldThrowExceptionWhenUserIsNotAuthorAndAttemptsToDeletePost() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANGHYUN))
            .hasMessage("질문을 삭제할 권한이 없습니다.")
            .isExactlyInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("글에 답변이 없는 자신의 글일 경우 삭제가 가능하다.")
    void shouldDeletePostWhenNoAnswersExist() {
        Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("다른 사용자의 답변이 존재하는 글을 삭제하려 하는 경우 예외가 발생한다.")
    void shouldThrowExceptionWhenAttemptingToDeletePostWithAnswersFromOtherUsers() {
        Q1.addAnswer(AnswerTest.A2);
        Q1.addAnswer(AnswerTest.A1);

        assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.")
            .isExactlyInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변 목록이 전부 글 작성자와 동일한 경우 삭제가 가능하다.")
    void shouldDeletePostWhenAllAnswersAreByAuthor() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);

        Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 삭제에 성공 할 경우, 질문 삭제 이력, 답변 삭제 이력 목록을 가진 DeleteHistory 목록을 반환한다.")
    void shouldReturnDeleteHistoryList() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);
        final List<DeleteHistory> resultDeleteHistories = List.of(
            new DeleteHistory(ContentType.QUESTION, Q1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now())
        );

        assertThat(Q1.delete(NsUserTest.JAVAJIGI)).isEqualTo(resultDeleteHistories);
    }
}
