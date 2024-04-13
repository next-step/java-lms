package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("Answer를 삭제할 때 Owner가 아닐 때 Exception 확인 Test")
    void differentAnswerOwnerTest() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람");
    }

    @Test
    @DisplayName("Answer를 삭제할 때 Owner일 때 Exception이 발생하지 않는지 확인 Test")
    void sameAnswerOwnerTest() {
        assertThatCode(() -> A1.delete(NsUserTest.JAVAJIGI))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Answer Delete 확인 Test")
    void deleteAnswerTest() throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }
}
