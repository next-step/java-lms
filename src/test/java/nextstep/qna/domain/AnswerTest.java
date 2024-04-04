package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3");
    public static final Answer A4 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3");

    @Test
    public void delete_성공() {
        assertThat(A1.isDeleted()).isFalse();
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        A1.delete(deleteHistories);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistories.size()).isEqualTo(1);
    }
}
