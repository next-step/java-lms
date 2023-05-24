package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName(value = "Answer 생성자 작성자 정보 예외처리 검사")
    void test1() {
        assertThatThrownBy(() -> {
            new Answer(null, null, QuestionTest.Q1, "Answers Contents1");
        }).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    @DisplayName(value = "Answer 생성자 질문 정보 예외처리 검사")
    void test2() {
        assertThatThrownBy(() -> {
            new Answer(null, NsUserTest.JAVAJIGI, null, "Answers Contents1");
        }).isInstanceOf(NotFoundException.class);
    }
}
