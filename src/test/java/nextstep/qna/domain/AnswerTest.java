package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static Answer A1;
    public static Answer A2;

    public static Answer aAnswer() {
        return AnswerBuilder.aAnswer().withQuestion(QuestionTest.aQuestion()).withWriter(NsUserTest.JAVAJIGI).withContent("Answers Contents1").build();
    }

    public static Answer otherAnswer() {
        return AnswerBuilder.aAnswer().withQuestion(QuestionTest.aQuestion()).withWriter(NsUserTest.SANJIGI).withContent("Answers Contents2").build();
    }

    @BeforeEach
    public void setUp() {
        A1 = aAnswer();
        A2 = otherAnswer();
    }

    @Test
    public void delete() throws Exception {
        A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.history()).isEqualTo(DeleteHistoryTest.A1DeleteHistory);
    }

    @Test
    public void deleteByWrongUser() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
