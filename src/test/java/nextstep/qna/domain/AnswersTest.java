package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswersTest {

    private Question Q1;
    private Answer A1;
    private Answer A2;

    @BeforeEach
    void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    }

    @Test
    @DisplayName("질문에 답변이 추가되는 경우 답변이 추가된다.")
    void createAnswersTest() {
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);
        assertThat(Q1.getAnswers().size()).isEqualTo(2);
    }
}
