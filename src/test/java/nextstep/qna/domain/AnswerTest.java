package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @ParameterizedTest
    @MethodSource("isOwnerInputProvider")
    @DisplayName("해당 유저가 답변글의 주인이 맞는지 확인한다.")
    void testIsOwner(final NsUser user, final boolean expected) {
        //given, when
        final boolean actual = A1.isOwner(user);

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
    @DisplayName("해당 유저가 답변글의 주인이 아닌지 확인한다.")
    void testIsNotOwner(final NsUser user, final boolean expected) {
        //given, when
        final boolean actual = A1.isNotOwner(user);

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
    @DisplayName("답변글의 주인인 유저가 답변을 삭제하면, Answer의 deleted는 true가 되고 해당 내용이 저장된 DeleteHistory가 반환된다.")
    void testDelete() throws CannotDeleteException {
        //given
        final Answer tempA1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Temp Answers Contents1");
        final NsUser user = NsUserTest.JAVAJIGI;

        final LocalDateTime ignored = LocalDateTime.now();
        final DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.ANSWER, tempA1.getId(), user, ignored);

        //when
        final DeleteHistory deleteHistory = tempA1.delete(user);
        final boolean isDeleted = tempA1.isDeleted();

        //then
        assertThat(isDeleted).isTrue();
        assertThat(deleteHistory).isEqualTo(expectedDeleteHistory);
    }

    @Test
    @DisplayName("답변글의 주인이 아닌 유저가 답변의 삭제를 시도하면, CannotDeleteException 예외가 발생한다.")
    void testDeleteWithNonOwner() {
        //given
        final Answer tempA1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Temp Answers Contents1");
        final NsUser user = NsUserTest.SANJIGI;

        //when, then
        assertThatThrownBy(() -> tempA1.delete(user))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("답변을 삭제할 권한이 없습니다.");
    }
}
