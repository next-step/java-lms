package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
        "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1,
        "Answers Contents2");

    @DisplayName("답변 등록자와 로그인 유저가 일치할 땐 예외를 던지지 않습니다.")
    @Test
    void doesNotTrowExceptionWhenDeleteLoginUserMatchedWithAnswerWriter() {
        NsUser writer = A1.getWriter();
        assertThatNoException().isThrownBy(() -> A1.canDelete(writer));
    }

    @DisplayName("답변 등록자와 로그인 유저가 일치하지 않은데 삭제하려고 하면 예외를 던집니다.")
    @Test
    void throwExceptionWhenDeleteLoginUserDoesNotMatchedWithAnswerWriter() {
        NsUser writer = A1.getWriter();
        assertThatThrownBy(() -> A2.canDelete(writer)).isInstanceOf(CannotDeleteException.class);
    }


}
