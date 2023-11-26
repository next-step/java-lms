package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}
