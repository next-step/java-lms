package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.UnAuthenticationException;
import nextstep.qna.exception.Exceptions;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete_다른_유저_답변이_있는경우() {
        Assertions.assertThatThrownBy(() -> A1.delete(NsUser.GUEST_USER))
                .isInstanceOf(UnAuthenticationException.class)
                .hasMessage(Exceptions.NOT_EXIST_AUTHENTICATION.message());
    }

    @Test
    void delete_성공_삭제이력_반환() throws UnAuthenticationException {
        List<DeleteHistory> delete = A1.delete(NsUserTest.JAVAJIGI);
        DeleteHistory history = DeleteHistory.of(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now());

        Assertions.assertThat(delete).contains(history);
    }
}
