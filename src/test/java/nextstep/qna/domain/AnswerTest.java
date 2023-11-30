package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void ownerCheck_실패() {
        assertThrows(CannotDeleteException.class, () -> A1.ownerCheck(NsUserTest.SANJIGI));
    }

    @Test
    void ownerCheck_성공() {
        assertDoesNotThrow(() -> A1.ownerCheck(NsUserTest.JAVAJIGI));
    }

    @Test
    void delete_상태값_확인() {
        A1.softDelete();
        assertThat(A1.isDeleted()).isTrue();
    }
}
