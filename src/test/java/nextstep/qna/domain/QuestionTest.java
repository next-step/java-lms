package nextstep.qna.domain;

import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("사용자와 질문자가 달라 질문을 삭제할 수 없다면 예외를 던진다.")
    void cannot_delete_question() {
        // when // then
        assertThatThrownBy(() -> Q1.delete(SANJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
