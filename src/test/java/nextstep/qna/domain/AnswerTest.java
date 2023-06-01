package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete_성공() {
        A1.delete(new ArrayList<>());
        A2.delete(new ArrayList<>());

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
    }

    @Test
    void delete_history() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        A1.delete(deleteHistories);
        A2.delete(deleteHistories);

        assertThat(deleteHistories).hasSize(2);
    }
}
