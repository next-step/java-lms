package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("로그인 유저와 답변 유저가 동일")
    void sameAsLoginAndAnswerUserTest() {
        List<Answer> answers = Arrays.asList(AnswerTest.A1, AnswerTest.A2);
        Answers answers1 = new Answers(answers);
    }
}
