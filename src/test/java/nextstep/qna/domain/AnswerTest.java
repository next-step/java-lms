package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void checkCanDelete() throws CannotDeleteException {

        Assertions.assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> A1.checkCanDelete(NsUserTest.SANJIGI))
                .withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void makeDeleteHistory() throws CannotDeleteException {

        A1.makeDelete(NsUserTest.JAVAJIGI);
        Assertions.assertThat(A1.isDeleted()).isTrue();
    }
}
