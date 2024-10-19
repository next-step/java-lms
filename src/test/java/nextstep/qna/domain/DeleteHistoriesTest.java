package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.qna.domain.QuestionTest.Q1;
import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {

    @DisplayName("삭제 히스토리 리스트를 생성할 수 있다.")
    @Test
    void create() {
        DeleteHistories result = new DeleteHistories(List.of(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.of(2024, 10, 10, 11, 0))));

        assertThat(result).isEqualTo(new DeleteHistories(List.of(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.of(2024, 10, 10, 11, 0)))));
    }

    @DisplayName("삭제 히스토리를 추가할 수 있다.")
    @Test
    void add() {
        DeleteHistories deleteHistories = new DeleteHistories();

        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.of(2024, 10, 10, 11, 0)));

        assertThat(deleteHistories).isEqualTo(new DeleteHistories(List.of(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.of(2024, 10, 10, 11, 0)))));
    }

}
