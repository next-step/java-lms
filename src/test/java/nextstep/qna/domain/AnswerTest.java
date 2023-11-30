package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("사용자 정보를 인자로 받아 답변 작성자와 일치하는 지 확인한다.")
    @Test
    void isSameWriter() {
        // given
        Answer answer = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        // when & then
        assertThat(answer.isSameWriter(JAVAJIGI)).isTrue();
    }

    @DisplayName("Answer객체의 deleted상태를 true로 바꿔 질문을 삭제한다.")
    @Test
    void delete() {
        // given
        Answer answer = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        // when
        answer.delete();

        // then
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("현재 시간을 인자로 받아 DeleteHistory를 만들어 반환한다.")
    @Test
    void deleteHistory() {
        // given
        Answer answer = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answer.delete();
        LocalDateTime now = LocalDateTime.of(2023,11,28,13,0);

        // when
        DeleteHistory deleteHistory = answer.createDeleteHistory(now);

        // then
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), JAVAJIGI, LocalDateTime.of(2023, 11, 28, 13, 0)));
    }

    @DisplayName("삭제되지 않은 답변의 삭제 기록을 생성하려하면 예외를 발생시킨다.")
    @Test
    void deleteHistoryWhenNotDeleted() {
        // given
        Answer answer = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        LocalDateTime now = LocalDateTime.of(2023,11,28,13,0);

        // when & then
        assertThatThrownBy(() -> answer.createDeleteHistory(now)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 답변은 삭제되지 않았습니다.");
    }
}
