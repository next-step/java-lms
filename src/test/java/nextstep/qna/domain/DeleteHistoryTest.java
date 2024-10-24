package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoryTest {

    @DisplayName("삭제된 Answer 객체를 기반으로 생성이 잘 되는지")
    @Test
    void createTest_byAnswer() {
        Answer answer = new Answer(1L, "contents", "writer");
        DeleteHistory deleteHistory = new DeleteHistory(answer);

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(1L, ContentType.ANSWER, "writer"));
    }
}
