package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class AnswersTest {

    private Answers answers;

    @BeforeEach
    void setUp() {
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        answers = Answers.from(List.of(A1, A2));
    }

}
