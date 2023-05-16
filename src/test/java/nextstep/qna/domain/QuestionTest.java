package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void deleteQuestion() {
        assertThat(Q1.deleteQuestion()).isEqualTo(new DeleteHistory(ContentType.QUESTION, 0L, Q1.getWriter(), LocalDateTime.now()));
        assertThat(Q2.deleteQuestion()).isEqualTo(new DeleteHistory(ContentType.QUESTION, 0L, Q2.getWriter(), LocalDateTime.now()));
    }
}
