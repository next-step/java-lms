package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoryTest {

    @Test
    @DisplayName("질문삭제 히스토리 정적 팩토리 메소드를 사용하여 생성시 질문 삭제 타입의 히스토리가 생성된다.")
    void createQuestionDeleteHistory() {
        LocalDateTime deleteTime = LocalDateTime.now();

        DeleteHistory questionHistory = DeleteHistory.createQuestionHistory(1L, NsUserTest.JAVAJIGI, deleteTime);

        assertThat(questionHistory).isEqualTo(new DeleteHistory(ContentType.QUESTION, 1L, NsUserTest.JAVAJIGI, deleteTime));
    }

    @Test
    @DisplayName("답변삭제 히스토리 정적 팩토리 메소드를 사용하여 생성시 답변 삭제 타입의 히스토리가 생성된다.")
    void createAnswerDeleteHistory() {
        LocalDateTime deleteTime = LocalDateTime.now();

        DeleteHistory answerHistory = DeleteHistory.createAnswerHistory(1L, NsUserTest.JAVAJIGI, deleteTime);

        assertThat(answerHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.JAVAJIGI, deleteTime));
    }
}
