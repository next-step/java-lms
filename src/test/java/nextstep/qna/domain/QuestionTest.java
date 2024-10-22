package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    public static final LocalDateTime NOW = LocalDateTime.now();

    @Test
    void delete_다른_사람이_쓴_질문() throws Exception {
        assertThatThrownBy(() -> Q2.delete(NsUserTest.JAVAJIGI, NOW))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공() throws Exception {
        assertThat(Q1.isDeleted()).isFalse();
        assertThat(Q1.delete(NsUserTest.JAVAJIGI, NOW)).isEqualTo(List.of(new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI, NOW)));
        assertThat(Q1.isDeleted()).isTrue();
    }
}
