package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("delete_method_test")
    public void delete_method_test(){

        DeleteHistory deleteHistory = A1.delete();

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
        assertThat(A1.isDeleted()).isTrue();
    }

}
