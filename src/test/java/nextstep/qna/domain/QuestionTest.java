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
    Question Q1_QUESTION_BY_JAVAJIGI;
    Question Q2_QUESTION_BY_SANJIGI;
    Answer A1_ANSWER_BY_JAVAJIGI_OF_Q1;
    Answer A2_ANSWER_BY_JAVAJIGI_OF_Q1;
    Answer A3_ANSWER_BY_SANJIGI_OF_Q1;

    @BeforeEach
    void setUp() {
        Q1_QUESTION_BY_JAVAJIGI = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2_QUESTION_BY_SANJIGI = new Question(NsUserTest.SANJIGI, "title2", "contents2");
        A1_ANSWER_BY_JAVAJIGI_OF_Q1 = new Answer(NsUserTest.JAVAJIGI, Q1_QUESTION_BY_JAVAJIGI, "Answers Contents1");
        A2_ANSWER_BY_JAVAJIGI_OF_Q1 = new Answer(NsUserTest.JAVAJIGI, Q1_QUESTION_BY_JAVAJIGI, "Answers Contents2");
        A3_ANSWER_BY_SANJIGI_OF_Q1 = new Answer(NsUserTest.SANJIGI, Q1_QUESTION_BY_JAVAJIGI, "Answers Contents3");
    }

    @DisplayName("질문 작성자와 loginUser가 다르면 CannotDeleteException을 던진다.")
    @Test
    void throwCannotDeleteExceptionWhenOwnerAndLoginUserNotSame() {
        // then
        assertThatThrownBy(() -> Q1_QUESTION_BY_JAVAJIGI.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("Question을 삭제하면, 해당 질문의 delete 상태가 변경된다.")
    @Test
    void changeDeleteStatusWhenDelete() throws CannotDeleteException {
        // when
        Q1_QUESTION_BY_JAVAJIGI.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(Q1_QUESTION_BY_JAVAJIGI.isDeleted())
                .isTrue();
    }

    @DisplayName("Question을 삭제하면, 해당 질문의 답변삭제를 실행한다.")
    @Test
    void deleteAnswersWhenQuestionDelete() throws CannotDeleteException {
        // given
        Q1_QUESTION_BY_JAVAJIGI.addAnswer(A1_ANSWER_BY_JAVAJIGI_OF_Q1);
        Q1_QUESTION_BY_JAVAJIGI.addAnswer(A2_ANSWER_BY_JAVAJIGI_OF_Q1);

        // when
        Q1_QUESTION_BY_JAVAJIGI.delete(NsUserTest.JAVAJIGI);

        // then
        Answers answers = Q1_QUESTION_BY_JAVAJIGI.getAnswers();
        assertThat(answers.asDeleteHistoryTargets().asList())
                .containsExactly(
                        new DeleteHistory(ContentType.ANSWER, A1_ANSWER_BY_JAVAJIGI_OF_Q1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, A2_ANSWER_BY_JAVAJIGI_OF_Q1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now())
                );
    }

    @DisplayName("삭제 도중 실패하면 모든 상태는 변하지 않는다.")
    @Test
    void noStatusUpdateWhenFailedToDelete() throws CannotDeleteException {
        // given
        boolean beforeQ1DeleteStatus = Q1_QUESTION_BY_JAVAJIGI.isDeleted();
        boolean beforeA1DeleteStatus = A1_ANSWER_BY_JAVAJIGI_OF_Q1.isDeleted();
        boolean beforeA3DeleteStatus = A3_ANSWER_BY_SANJIGI_OF_Q1.isDeleted();
        Q1_QUESTION_BY_JAVAJIGI.addAnswer(A1_ANSWER_BY_JAVAJIGI_OF_Q1);
        Q1_QUESTION_BY_JAVAJIGI.addAnswer(A3_ANSWER_BY_SANJIGI_OF_Q1);

        // then
        assertThatThrownBy(() -> Q1_QUESTION_BY_JAVAJIGI.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);

        assertThat(Q1_QUESTION_BY_JAVAJIGI.isDeleted()).isEqualTo(beforeQ1DeleteStatus);
        assertThat(A1_ANSWER_BY_JAVAJIGI_OF_Q1.isDeleted()).isEqualTo(beforeA1DeleteStatus);
        assertThat(A3_ANSWER_BY_SANJIGI_OF_Q1.isDeleted()).isEqualTo(beforeA3DeleteStatus);
    }

    @DisplayName("삭제가 성공하면 모든 상태는 삭제상태가 된다.")
    @Test
    void statusUpdateWhenSuccessToDelete() throws CannotDeleteException {
        // given
        Q1_QUESTION_BY_JAVAJIGI.addAnswer(A1_ANSWER_BY_JAVAJIGI_OF_Q1);
        Q1_QUESTION_BY_JAVAJIGI.addAnswer(A2_ANSWER_BY_JAVAJIGI_OF_Q1);

        // when
        Q1_QUESTION_BY_JAVAJIGI.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(Q1_QUESTION_BY_JAVAJIGI.isDeleted()).isTrue();
        assertThat(A1_ANSWER_BY_JAVAJIGI_OF_Q1.isDeleted()).isTrue();
        assertThat(A2_ANSWER_BY_JAVAJIGI_OF_Q1.isDeleted()).isTrue();
    }

    @DisplayName("삭제 리스트에 대해 DeleteHistoryTargets로 반환받을 수 있다.")
    @Test
    void getDeletedElementToDeleteHistoryTargets() throws CannotDeleteException {
        // given
        Q1_QUESTION_BY_JAVAJIGI.addAnswer(A1_ANSWER_BY_JAVAJIGI_OF_Q1);
        Q1_QUESTION_BY_JAVAJIGI.addAnswer(A2_ANSWER_BY_JAVAJIGI_OF_Q1);

        // when
        Q1_QUESTION_BY_JAVAJIGI.delete(NsUserTest.JAVAJIGI);

        // then
        DeleteHistoryTargets deleteHistoryTargets = Q1_QUESTION_BY_JAVAJIGI.asDeleteHistoryTargets(NsUserTest.JAVAJIGI);
        assertThat(deleteHistoryTargets.asList())
                .contains(
                        new DeleteHistory(ContentType.QUESTION, Q1_QUESTION_BY_JAVAJIGI.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, A1_ANSWER_BY_JAVAJIGI_OF_Q1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, A2_ANSWER_BY_JAVAJIGI_OF_Q1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now())
                );
    }
}
