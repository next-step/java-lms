package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.UnAuthenticationException;
import nextstep.qna.exception.Exceptions;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_성공() throws UnAuthenticationException {
        List<DeleteHistory> histories = Q1.delete(NsUserTest.JAVAJIGI);
        DeleteHistory history = DeleteHistory.of(ContentType.QUESTION, Q1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now());

        Assertions.assertThat(histories).contains(history);

    }

    @Test
    void delete_이미_삭제된_질문() throws UnAuthenticationException {
        Assertions.assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(Exceptions.NOT_EXIST_QUESTION.message());
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        Assertions.assertThatThrownBy(() -> Q2.delete(NsUser.GUEST_USER))
                .isInstanceOf(UnAuthenticationException.class)
                .hasMessage(Exceptions.NOT_EXIST_AUTHENTICATION.message());

    }
}
