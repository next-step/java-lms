package nextstep.qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static nextstep.qna.domain.QuestionTest.Q1;

public class DeleteHistoriesTest {
    @Test
    void create() {
        Assertions.assertThatNoException().isThrownBy(() -> {
            DeleteHistories deleteHistories = new DeleteHistories(new DeleteHistory(Q1), new DeleteHistory(A1));
        });
    }

    @Test
    void add_성공() {
        DeleteHistories actual = new DeleteHistories();
        actual.add(new DeleteHistory(Q1));
        actual.add(new DeleteHistory(A1));
        DeleteHistories expected = new DeleteHistories(new DeleteHistory(Q1), new DeleteHistory(A1));

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void value_성공() {
        DeleteHistories deleteHistories = new DeleteHistories(new DeleteHistory(Q1), new DeleteHistory(A1));
        List<DeleteHistory> actual = deleteHistories.value();
        List<DeleteHistory> expected = List.of(new DeleteHistory(Q1), new DeleteHistory(A1));

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void value_수정불가() {
        DeleteHistories deleteHistories = new DeleteHistories(new DeleteHistory(Q1), new DeleteHistory(A1));
        List<DeleteHistory> actual = deleteHistories.value();

        Assertions.assertThatThrownBy(() -> {
            actual.add(new DeleteHistory(A2));
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
