package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AnswersTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("모든 답변의 주인이 해당 유저인지 확인하고 해당 유저가 맞으면, 예외가 발생하지 않는다.")
    void testVerifyAllAnswerOwnerIsTargetUserWithOwner() {
        //given
        final NsUser owner = NsUserTest.JAVAJIGI;
        Answers answers = new Answers(List.of(A1));

        //when, then
        assertThatNoException()
                .isThrownBy(() -> answers.verifyAllAnswerOwnerIsTargetUser(owner));
    }

    @Test
    @DisplayName("모든 답변 중 하나라도 해당 유저가 주인이 아니면, 예외가 발생한다.")
    void testVerifyUserWithAnswerDeletionPermissionWithNotOwner() {
        //given
        final NsUser owner = NsUserTest.JAVAJIGI;
        Answers answers = new Answers(List.of(A1, A2));

        //when, then
        assertThatThrownBy(() -> answers.verifyAllAnswerOwnerIsTargetUser(owner))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("모든 답변을 삭제한다.")
    void testDeleteAll() {
        //given
        final Answer tempA1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Temp Answers Contents1");
        final Answer tempA2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Temp Answers Contents2");

        final Answers answers = new Answers(List.of(tempA1, tempA2));
        final DeleteHistories deleteHistories = new DeleteHistories();
        final int sizeBeforeDelete = deleteHistories.size();

        //when
        answers.deleteAll(deleteHistories);

        final boolean isDeletedTempA1 = tempA1.isDeleted();
        final boolean isDeletedTempA2 = tempA2.isDeleted();
        final int sizeAfterDelete = deleteHistories.size();

        //then
        assertThat(isDeletedTempA1).isTrue();
        assertThat(isDeletedTempA2).isTrue();
        assertThat(sizeAfterDelete).isEqualTo(sizeBeforeDelete + 2);
    }
}
