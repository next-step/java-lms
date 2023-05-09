package nextstep.qna.domain;

import nextstep.Fixtures;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.Fixtures.createAnswer;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Fixtures.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, Fixtures.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents2");

    @Test
    @DisplayName("answer 를 삭제한다.")
    void test01() {
        Answer answer = createAnswer(NsUserTest.JAVAJIGI);

        answer.delete();

        assertThat(answer.isDeleted()).isEqualTo(true);
    }
}
