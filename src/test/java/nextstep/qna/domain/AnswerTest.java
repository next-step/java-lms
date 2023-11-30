package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private static final LocalDateTime FIXED_NOW = LocalDateTime.of(2023, 11, 30, 17, 0, 4);

    @Test
    @DisplayName("[Answer.deleteIfWriter()] 주어진 사용자가 답변자라면 -> 삭제")
    public void deleteTest() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "hello");
        answer.deleteIfWriter(NsUserTest.JAVAJIGI, FIXED_NOW);

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("[Answer.deleteIfWriter()] 주어진 사용자와 답변자가 다르다면 -> 삭제 거부")
    public void deleteByWrongWriterTest() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "hello");
        answer.deleteIfWriter(NsUserTest.SANJIGI, FIXED_NOW);

        assertThat(answer.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("[Answer.deleteIfWriter()] 삭제를 요청하면 -> 자기 자신의 삭제 정보를 준다.")
    public void deleteInfoTest() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "hello");

        assertThat(answer.deleteIfWriter(NsUserTest.JAVAJIGI, FIXED_NOW))
                .isEqualTo(new DeleteHistory(
                        ContentType.ANSWER,
                        answer.getId(),
                        NsUserTest.JAVAJIGI,
                        FIXED_NOW
                ));
    }
}