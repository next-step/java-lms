package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문삭제권한체크_exception() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .withMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void delete() throws CannotDeleteException {
        assertThat(Q1.delete(NsUserTest.JAVAJIGI))
                .containsExactly(DeleteHistory.ofQuestion(Q1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));
        assertThat(Q1.isDeleted()).isTrue();
    }
}
