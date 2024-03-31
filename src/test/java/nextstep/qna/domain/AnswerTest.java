package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    Answer A1;
    Answer A2;
    Question Q1;

    @BeforeEach
    void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    }

    @DisplayName("Answer에 대한 삭제를 진행할 때, loginUser와 writer가 같다면 삭제 상태를 변경한다.")
    @Test
    void changeDeleteStatueWhenLoginUserAndWriterSame() throws CannotDeleteException {
        // when
        A1.delete();

        // then
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("생성한 Answer에 대해, DeleteHistory로 변환하여 받을 수 있다.")
    @Test
    void getAsDeleteHistory() {
        // given
        DeleteHistory deleteHistory =
                new DeleteHistory(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now());

        // then
        assertThat(A1.asDeleteHistory()).isEqualTo(deleteHistory);
    }
}
