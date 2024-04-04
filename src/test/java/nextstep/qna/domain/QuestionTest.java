package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_실패_권한_없음() {
        assertThatThrownBy(
                () -> Q1.delete(NsUserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공() throws CannotDeleteException {
        assertThat(Q1.delete(NsUserTest.JAVAJIGI))
                .containsExactly(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
    }

}
