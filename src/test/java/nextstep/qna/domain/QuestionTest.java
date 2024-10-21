package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문을_삭제한다() {
        Q1.addAnswer(AnswerTest.A1);
        LocalDateTime deleteDateTime = LocalDateTime.of(2024, 10, 21, 10, 10);
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI, deleteDateTime);

        List<DeleteHistory> result = List.of(
            new DeleteHistory(ContentType.QUESTION, Q1.getId(), NsUserTest.JAVAJIGI, deleteDateTime),
            new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), NsUserTest.JAVAJIGI, deleteDateTime)
        );

        assertThat(deleteHistories).hasSize(2);
        assertThat(deleteHistories).isEqualTo(result);
    }

    @Test
    void 질문작성자가_다르면_삭제에_실패한다() {
        LocalDateTime deleteDateTime = LocalDateTime.of(2024, 10, 21, 10, 10);
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI, deleteDateTime))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
