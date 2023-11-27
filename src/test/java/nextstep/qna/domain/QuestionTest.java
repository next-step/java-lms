package nextstep.qna.domain;

import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question(JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(SANJIGI, "title2", "contents2");

    @DisplayName("질문 등록자와 삭제 요청자가 다르면 CannotDeleteException을 발생시킨다.")
    @Test
    void valid_question() {
        assertThatThrownBy(
            () -> Q1.deleteQuestion(
                SANJIGI, LocalDateTime.of(2023, 11,27, 1, 0, 0)
            )
        )
            .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("deleteQuestion 메서드 호출시 사용자가 soft delete된다.")
    @Test
    void delete() throws CannotDeleteException {
        Q1.deleteQuestion(
            JAVAJIGI, LocalDateTime.of(2023, 11,27, 1, 0, 0)
        );
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }
}
