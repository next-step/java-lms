package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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
}
