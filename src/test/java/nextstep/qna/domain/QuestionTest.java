package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @ParameterizedTest
    @MethodSource("isOwnerInputProvider")
    @DisplayName("해당 유저가 질문글의 주인이 맞는지 확인한다.")
    void testIsOwner(final NsUser user, final boolean expected) {
        //given, when
        final boolean actual = Q1.isOwner(user);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> isOwnerInputProvider() {
        return Stream.of(
                Arguments.of(NsUserTest.JAVAJIGI, true),
                Arguments.of(NsUserTest.SANJIGI, false)
        );
    }

    @ParameterizedTest
    @MethodSource("isNotOwnerInputProvider")
    @DisplayName("해당 유저가 질문글의 주인이 아닌지 확인한다.")
    void testIsNotOwner(final NsUser user, final boolean expected) {
        //given, when
        final boolean actual = Q1.isNotOwner(user);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> isNotOwnerInputProvider() {
        return Stream.of(
                Arguments.of(NsUserTest.JAVAJIGI, false),
                Arguments.of(NsUserTest.SANJIGI, true)
        );
    }

    @Test
    @DisplayName("질문을 삭제할 수 있는 유저인지 확인하고 삭제가 가능한 유저이면, 예외가 발생하지 않는다.")
    void testVerifyUserWithQuestionDeletionPermissionWithOwner() {
        //given
        final NsUser owner = NsUserTest.JAVAJIGI;

        //when, then
        assertThatNoException()
                .isThrownBy(() -> Q1.verifyUserWithQuestionDeletionPermission(owner));
    }

    @Test
    @DisplayName("질문을 삭제할 수 있는 유저인지 확인하고 삭제가 불가능한 유저이면, 예외가 발생한다.")
    void testVerifyUserWithQuestionDeletionPermissionWithNotOwner() {
        //given
        final NsUser notOwner = NsUserTest.SANJIGI;

        //when, then
        assertThatThrownBy(() -> Q1.verifyUserWithQuestionDeletionPermission(notOwner))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문을 삭제하면, Question의 deleted는 true가 되고 매개변수로 넣은 deleteHistories에 삭제 기록이 저장된다")
    void testDelete() {
        //given
        DeleteHistories deleteHistories = new DeleteHistories();
        final int sizeBeforeDelete = deleteHistories.size();

        //when
        Q1.delete(deleteHistories);
        final boolean isDeleted = Q1.isDeleted();
        final int sizeAfterDelete = deleteHistories.size();

        //then
        assertThat(isDeleted).isTrue();
        assertThat(sizeAfterDelete).isEqualTo(sizeBeforeDelete + 1);
    }
}
