package nextstep.qna.domain;

import static java.time.LocalDateTime.now;
import static nextstep.qna.domain.ContentType.ANSWER;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer JAVAJIGI_ANSWER = new Answer(JAVAJIGI, QuestionTest.JAVAJIGI_QUESTION, "Answers Contents1");
    public static final Answer SANJIGI_ANSWER = new Answer(NsUserTest.SANJIGI, QuestionTest.JAVAJIGI_QUESTION, "Answers Contents2");

    private Answer single_answer;

    @BeforeEach
    void init() {
        single_answer = new Answer(JAVAJIGI, QuestionTest.JAVAJIGI_QUESTION, "Answers Contents1");
    }

    @Test
    @DisplayName("답변내역을 삭제한다.")
    void deleteTest() {
        JAVAJIGI_ANSWER.deleteSelf();
        assertThat(JAVAJIGI_ANSWER.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제가 되지 않은 답변에 대해 히스토리를 반환받을 경우 빈 객체를 리턴한다.")
    void deleteHistoryEmptyTest() {
        assertThat(single_answer.deleteHistory().isEmpty()).isTrue();
    }

    @Test
    @DisplayName("삭제된 답변에 대해 삭제 내역을 리턴한다.")
    void deleteHistoryTest() {
        JAVAJIGI_ANSWER.deleteSelf();

        assertThat(JAVAJIGI_ANSWER.deleteHistory())
                .isEqualTo(new DeleteHistory(ANSWER, null, JAVAJIGI, now()));
    }
}
