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
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    @DisplayName("질문을 삭제하면, Question의 deleted는 true가 되고 매개변수로 넣은 deleteHistories에 삭제 기록이 저장된다")
    void testDelete() throws CannotDeleteException {
        //given
        final Question tempQ1 = new Question(NsUserTest.JAVAJIGI, "title1", "Temp contents1");
        final NsUser user = NsUserTest.JAVAJIGI;

        final LocalDateTime ignored = LocalDateTime.now();
        final DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.QUESTION, tempQ1.getId(), user, ignored);

        //when
        final List<DeleteHistory> deleteHistories = tempQ1.delete(user);
        final boolean isDeleted = tempQ1.isDeleted();

        //then
        assertThat(isDeleted).isTrue();
        assertThat(deleteHistories).contains(expectedDeleteHistory);
    }

    @Test
    @DisplayName("질문글의 주인이 아닌 유저가 질문의 삭제를 시도하면, CannotDeleteException 예외가 발생한다.")
    void testDeleteWithNonOwner() {
        //given
        final Question tempQ1 = new Question(NsUserTest.JAVAJIGI, "title1", "Temp contents1");
        final NsUser user = NsUserTest.SANJIGI;

        //when, then
        assertThatThrownBy(() -> tempQ1.delete(user))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
