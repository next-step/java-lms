package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.qna.domain.fixture.DomainFixture.*;
import static nextstep.users.domain.fixture.DomainFixture.*;
import static org.assertj.core.api.Assertions.*;

public class QuestionTest {

    @DisplayName("Question객체의 deleted상태를 true로 바꿔 질문을 삭제한다.")
    @Test
    void delete() throws CannotDeleteException {
        // given
        Question question = new Question(JAVAJIGI, "title1", "contents1");

        // when
        question.delete(JAVAJIGI, LocalDateTime.now());

        // then
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문을 삭제할 때 로그인 정보와 질문의 사용자 정보가 일치 하지 않으면 예외를 던진다.")
    @Test
    void deleteWhenNotEqualWithLoginUser() {
        // given
        Question question = new Question(JAVAJIGI, "title1", "contents1");

        // when & then
        assertThatThrownBy(() -> question.delete(SANJIGI, LocalDateTime.now())).isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문을 삭제할 때 작성자가 다른 답변이 있으면 예외를 던진다.")
    @Test
    void deleteWhenNotEqualWithAnswerWriter() {
        // given
        Question question = new Question(JAVAJIGI, "title1", "contents1");
        question.addAnswer(A1);
        question.addAnswer(A2);

        // when & then
        assertThatThrownBy(() -> question.delete(JAVAJIGI, LocalDateTime.now())).isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문, 답변의 삭제 기록 리스트를 반환한다.")
    @Test
    void deleteHistories() throws CannotDeleteException {
        // given
        Question question = new Question(JAVAJIGI, "title1", "contents1");
        Answer answer1 = new Answer(JAVAJIGI, Q1, "Answers Contents1");
        Answer answer2 = new Answer(JAVAJIGI, Q1, "Answers Contents2");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        LocalDateTime now = LocalDateTime.of(2023,11,28,13,0);
        question.delete(JAVAJIGI, now);


        // when
        List<DeleteHistory> deleteHistories = question.createDeleteHistories();

        // then
        assertThat(deleteHistories).hasSize(3)
            .containsExactlyInAnyOrder(
                new DeleteHistory(ContentType.ANSWER, null, JAVAJIGI, LocalDateTime.of(2023, 11, 28, 13, 0)),
                new DeleteHistory(ContentType.ANSWER, null, JAVAJIGI, LocalDateTime.of(2023, 11, 28, 13, 0)),
                new DeleteHistory(ContentType.QUESTION, 0L, JAVAJIGI, LocalDateTime.of(2023, 11, 28, 13, 0))
            );
    }

    @DisplayName("삭제 되지 않은 질문의 삭제 기록을 생성하면 예외를 던진다.")
    @Test
    void deleteHistoriesWhenNotDeleted() {
        // given
        Question question = new Question(JAVAJIGI, "title1", "contents1");

        LocalDateTime now = LocalDateTime.of(2023,11,28,13,0);

        // when & then
        assertThatThrownBy(question::createDeleteHistories).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 질문은 삭제되지 않았습니다. 질문 ID :: 0");
    }
}
