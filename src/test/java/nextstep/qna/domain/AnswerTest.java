package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete_성공() {
        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();

    }

    @Test
    void delete_history() {
        List<Answer> answers = List.of(A1, A2);

        List<DeleteHistory> deleteHistories = answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());

        assertThat(deleteHistories).hasSize(2);
    }
}
