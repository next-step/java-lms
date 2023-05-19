package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("질문 관련 기능")
public class QuestionTest {
    private Question Q1;
    private Question Q2;

    @BeforeEach
    void setUp() {
        this.Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        this.Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    }
}
