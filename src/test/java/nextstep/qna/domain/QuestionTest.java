package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("삭제할 때 로그인 사용자와 질문자가 다르면 예외를 던진다.")
    void question_삭제() {
        assertThatThrownBy(() -> Q1.validateCanDelete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문 삭제 시 히스토리를 반환한다.")
    void question_삭제_히스토리() {
        DeleteHistory fakeHistory = new DeleteHistory(ContentType.QUESTION, Q2.getId(), NsUserTest.SANJIGI, LocalDateTime.now());

        List<DeleteHistory> histories = Q2.delete();

        assertThat(histories).contains(fakeHistory);
    }
}
