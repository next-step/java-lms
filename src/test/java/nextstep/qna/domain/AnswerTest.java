package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {

    private Answer answer;

    @BeforeEach
    void setUp() {
        Question javajigiQuestion = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = new Answer(NsUserTest.JAVAJIGI, javajigiQuestion, "Answers Contents1");
    }

    @Test
    @DisplayName("답변 삭제시 삭제 상태를 true로 변경한다.")
    void test01() {
        answer.deleteAnswer();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변 삭제시 답변이력 DeleteHistroy를 반환한다.")
    void test02() {
        DeleteHistory actual = answer.deleteAnswer();
        DeleteHistory expected = new DeleteHistory(
                ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now());
        assertThat(actual).isEqualTo(expected);
    }

}
