package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete() {
        Q1.addAnswer(A1);
        assertThat(Q1.delete(NsUserTest.JAVAJIGI)).isEqualTo(
            List.of(
                new DeleteHistory(ContentType.QUESTION, Q1.getId(), NsUserTest.JAVAJIGI,
                    LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI,
                    LocalDateTime.now())
            )
        );
        assertThat(A1.isDeleted()).isTrue();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

}
