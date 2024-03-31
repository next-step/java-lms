package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    Question Q1;
    Question Q2;
    Answer A1;
    Answer A2;
    Answer A3;

    @BeforeEach
    void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
        A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents2");
        A3 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents3");
    }

    @DisplayName("질문 작성자와 loginUser가 다르면 CannotDeleteException을 던진다.")
    @Test
    void throwCannotDeleteExceptionWhenOwnerAndLoginUserNotSame() {
        // then
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("Question을 삭제하면, 해당 질문의 delete 상태가 변경된다.")
    @Test
    void changeDeleteStatusWhenDelete() throws CannotDeleteException {
        // when
        Q1.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(Q1.isDeleted())
                .isTrue();
    }

    @DisplayName("Question을 삭제하면, 해당 질문의 답변삭제를 실행한다.")
    @Test
    void deleteAnswersWhenQuestionDelete() throws CannotDeleteException {
        // given
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        // when
        Q1.delete(NsUserTest.JAVAJIGI);

        // then
        Answers answers = Q1.getAnswers();
        assertThat(answers.asDeleteHistoryTargets().asList())
                .containsExactly(
                        new DeleteHistory(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, A2.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now())
                );
    }

    @DisplayName("삭제 도중 실패하면 모든 상태는 변하지 않는다.")
    @Test
    void noStatusUpdateWhenFailedToDelete() throws CannotDeleteException {
        // given
        boolean beforeQ1DeleteStatus = Q1.isDeleted();
        boolean beforeA1DeleteStatus = A1.isDeleted();
        boolean beforeA3DeleteStatus = A3.isDeleted();
        Q1.addAnswer(A1);
        Q1.addAnswer(A3);

        // then
        assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);

        assertThat(Q1.isDeleted()).isEqualTo(beforeQ1DeleteStatus);
        assertThat(A1.isDeleted()).isEqualTo(beforeA1DeleteStatus);
        assertThat(A3.isDeleted()).isEqualTo(beforeA3DeleteStatus);
    }

    @DisplayName("삭제가 성공하면 모든 상태는 삭제상태가 된다.")
    @Test
    void statusUpdateWhenSuccessToDelete() throws CannotDeleteException {
        // given
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        // when
        Q1.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
    }

    @DisplayName("삭제 리스트에 대해 DeleteHistoryTargets로 반환받을 수 있다.")
    @Test
    void getDeletedElementToDeleteHistoryTargets() throws CannotDeleteException {
        // given
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        // when
        Q1.delete(NsUserTest.JAVAJIGI);

        // then
        DeleteHistoryTargets deleteHistoryTargets = Q1.asDeleteHistoryTargets(NsUserTest.JAVAJIGI);
        assertThat(deleteHistoryTargets.asList())
                .contains(
                        new DeleteHistory(ContentType.QUESTION, Q1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, A2.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now())
                );
    }
}
