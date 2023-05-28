package nextstep.qna.domain;

import static java.time.LocalDateTime.now;
import static nextstep.qna.domain.ContentType.ANSWER;
import static org.assertj.core.api.Assertions.assertThat;

import nextstep.dummy.answer.AnswerDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    private AnswerDummy answerDummy;

    private Answer javajigiAnswer;

    @BeforeEach
    void init() {
        answerDummy = new AnswerDummy();
        javajigiAnswer = answerDummy.javajigi_answer;
    }

    @Test
    @DisplayName("답변내역을 삭제한다.")
    void deleteTest() {
        javajigiAnswer.deleteSelf();
        assertThat(javajigiAnswer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제가 되지 않은 답변에 대해 히스토리를 반환받을 경우 빈 객체를 리턴한다.")
    void deleteHistoryEmptyTest() {
        assertThat(javajigiAnswer.deleteHistory().isEmpty()).isTrue();
    }

    @Test
    @DisplayName("삭제된 답변에 대해 삭제 내역을 리턴한다.")
    void deleteHistoryTest() {
        javajigiAnswer.deleteSelf();
        assertThat(javajigiAnswer.deleteHistory())
                .isEqualTo(
                        new DeleteHistory(ANSWER, null, answerDummy.javajigi_writer, now()));
    }
}
