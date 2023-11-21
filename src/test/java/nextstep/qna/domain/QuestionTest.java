package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1", LocalDateTime.of(2023, 11,
            21, 0, 0));
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2", LocalDateTime.of(2023, 11,
            21, 0, 0));

    @Test
    @DisplayName("질문의 작성자와 삭제를 요청한 대상이 다르면 예외 처리를 한다")
    void deleteNotCorrectUser() {
        assertThrows(CannotDeleteException.class, () -> Q1.delete(NsUserTest.SANJIGI), "질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("삭제 처리를 한다")
    void delete() {
        Q1.delete(NsUserTest.JAVAJIGI);

        Question expected = new Question(NsUserTest.JAVAJIGI, "title1", "contents1", true, LocalDateTime.of(2023, 11,
                21, 0, 0));

        assertThat(Q1).isEqualTo(expected);
    }
}
