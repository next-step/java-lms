package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoryTest {

    @DisplayName("삭제 히스토리를 생성할 수 있다.")
    @Test
    void create() {
        DeleteHistory result = new DeleteHistory(ContentType.QUESTION, 1L, NsUserTest.GREEN, LocalDateTime.of(2024, 10, 10, 11, 10));

        assertThat(result).isEqualTo(new DeleteHistory(ContentType.QUESTION, 1L, NsUserTest.GREEN, LocalDateTime.of(2024, 10, 10, 11, 10)));
    }

    @DisplayName("질문 삭제 히스토리를 생성할 수 있다.")
    @Test
    void qusetionOf() {
        DeleteHistory result = DeleteHistory.questionOf(1L, NsUserTest.GREEN, LocalDateTime.of(2024, 10, 10, 11, 10));

        assertThat(result).isEqualTo(new DeleteHistory(ContentType.QUESTION, 1L, NsUserTest.GREEN, LocalDateTime.of(2024, 10, 10, 11, 10)));
    }

    @DisplayName("답변 삭제 히스토리를 생성할 수 있다.")
    @Test
    void answerOf() {
        DeleteHistory result = DeleteHistory.answerOf(1L, NsUserTest.GREEN, LocalDateTime.of(2024, 10, 10, 11, 10));

        assertThat(result).isEqualTo(new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.GREEN, LocalDateTime.of(2024, 10, 10, 11, 10)));
    }

}
