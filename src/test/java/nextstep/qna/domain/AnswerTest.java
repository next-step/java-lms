package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.qna.domain.fixture.DomainFixture.*;
import static nextstep.users.domain.fixture.DomainFixture.*;
import static org.assertj.core.api.Assertions.*;

public class AnswerTest {

    @DisplayName("Answer객체의 deleted상태를 true로 바꿔 질문을 삭제한다.")
    @Test
    void delete() throws CannotDeleteException {
        // given
        Answer answer = new Answer(JAVAJIGI, Q1, "Answers Contents1");

        // when
        answer.delete(JAVAJIGI, LocalDateTime.now());

        // then
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("로그인 사용자와 답변의 작성자가 일치하지 않으면 예외를 던진다.")
    @Test
    void deleteWhenOtherWriter() throws CannotDeleteException {
        // given
        Answer answer = new Answer(1L, JAVAJIGI, Q1, "Answers Contents1");

        // when & then
        assertThatThrownBy(() -> answer.delete(SANJIGI, LocalDateTime.now())).isInstanceOf(CannotDeleteException.class)
            .hasMessage("답변을 삭제할 권한이 없습니다. 답변 ID ::1");
    }

    @DisplayName("DeleteHistory를 만들어 반환한다.")
    @Test
    void deleteHistory() throws CannotDeleteException {
        // given
        Answer answer = new Answer(JAVAJIGI, Q1, "Answers Contents1");
        LocalDateTime now = LocalDateTime.of(2023,11,28,13,0);
        answer.delete(JAVAJIGI, now);

        // when
        DeleteHistory deleteHistory = answer.createDeleteHistory();

        // then
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), JAVAJIGI, LocalDateTime.of(2023, 11, 28, 13, 0)));
    }

    @DisplayName("삭제되지 않은 답변의 삭제 기록을 생성하려하면 예외를 발생시킨다.")
    @Test
    void deleteHistoryWhenNotDeleted() {
        // given
        Answer answer = new Answer(JAVAJIGI, Q1, "Answers Contents1");

        // when & then
        assertThatThrownBy(answer::createDeleteHistory).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 답변은 삭제되지 않았습니다. 답변 ID ::null");
    }
}
