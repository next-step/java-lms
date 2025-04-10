package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    private Answer answer;

    @BeforeEach
    void setUp() {
        Question question = new Question(JAVAJIGI, "title1", "contents1");
        answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
    }

    @Test
    @DisplayName("정상적으로 답변 삭제에 성공한다")
    void delete_normal_case() {
        answer.delete();
        assertTrue(answer.isDeleted());
    }
}
