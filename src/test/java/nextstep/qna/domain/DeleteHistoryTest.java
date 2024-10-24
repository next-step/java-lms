package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoryTest {

    @DisplayName("삭제된 Answer 객체를 기반으로 생성이 잘 되는지")
    @Test
    void createTest_byAnswer() {
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "writer");
        DeleteHistory deleteHistory = new DeleteHistory(answer);
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
