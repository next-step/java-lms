package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        answer.deleteAnswer(NsUserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변 삭제시 답변이력 DeleteHistroy를 반환한다.")
    void test02() {
        DeleteHistory actual = answer.deleteAnswer(NsUserTest.JAVAJIGI);
        DeleteHistory expected = new DeleteHistory(
                ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("로그인 사용자와 답변한 사람이 같지 않을 경우 예외를 발생한다.")
    void test03() {
        assertThatThrownBy(() -> answer.deleteAnswer(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
