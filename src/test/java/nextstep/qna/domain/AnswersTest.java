package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static nextstep.users.domain.fixture.DomainFixture.*;
import static nextstep.qna.domain.fixture.DomainFixture.*;
import static org.assertj.core.api.Assertions.*;

public class AnswersTest {

    @DisplayName("모든 답변의 작성자가 인자로 받은 로그인 사용자와 일치하면 모든 Answer의 삭제 상태를 true로 바꿔 답변을 삭제한다.")
    @Test
    void deleteAll() throws CannotDeleteException {
        // given
        Answer answer1 = new Answer(JAVAJIGI, Q1, "Answers Contents1");
        Answer answer2 = new Answer(JAVAJIGI, Q1, "Answers Contents2");
        Answers answers = new Answers(List.of(answer1, answer2));

        // when
        answers.deleteAll(JAVAJIGI, LocalDateTime.now());

        // then
        IntStream.range(0, 2)
            .forEach(i -> assertThat(answers.isDeleted(i)).isTrue());
    }

    @DisplayName("현재 시간을 인자로 받아 DeleteHistory 리스트를 만들어 반환한다.")
    @Test
    void deleteHistory() throws CannotDeleteException {
        // given
        Answer answer1 = new Answer(JAVAJIGI, Q1, "Answers Contents1");
        Answer answer2 = new Answer(JAVAJIGI, Q1, "Answers Contents2");
        Answers answers = new Answers(List.of(answer1, answer2));

        LocalDateTime now = LocalDateTime.of(2023,11,28,13,0);
        answers.deleteAll(JAVAJIGI, now);


        // when
        List<DeleteHistory> deleteHistories = answers.createDeleteHistories();

        // then
        assertThat(deleteHistories).hasSize(2)
            .containsExactlyInAnyOrder(
                new DeleteHistory(ContentType.ANSWER, null, JAVAJIGI, LocalDateTime.of(2023, 11, 28, 13, 0)),
                new DeleteHistory(ContentType.ANSWER, null, JAVAJIGI, LocalDateTime.of(2023, 11, 28, 13, 0))
            );
    }
}
