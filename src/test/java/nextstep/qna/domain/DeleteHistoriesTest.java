package nextstep.qna.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static nextstep.qna.domain.QuestionTest.Q1;
import static org.assertj.core.api.Assertions.*;

public class DeleteHistoriesTest {
    @Test
    void create() {
        assertThatNoException().isThrownBy(() -> {
            DeleteHistories deleteHistories = new DeleteHistories(new DeleteHistory(Q1), new DeleteHistory(A1));
        });
    }

    @Test
    void add_성공() {
        DeleteHistories actual = new DeleteHistories();
        actual.add(new DeleteHistory(Q1));
        actual.add(new DeleteHistory(A1));
        DeleteHistories expected = new DeleteHistories(new DeleteHistory(Q1), new DeleteHistory(A1));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void addAll_성공() {
        DeleteHistories actual = new DeleteHistories();
        actual.addAll(List.of(new DeleteHistory(Q1), new DeleteHistory(A1)));
        DeleteHistories expected = new DeleteHistories(new DeleteHistory(Q1), new DeleteHistory(A1));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void value_성공() {
        DeleteHistories deleteHistories = new DeleteHistories(new DeleteHistory(Q1), new DeleteHistory(A1));
        List<DeleteHistory> actual = deleteHistories.value();
        List<DeleteHistory> expected = List.of(new DeleteHistory(Q1), new DeleteHistory(A1));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void value_수정불가() {
        DeleteHistories deleteHistories = new DeleteHistories(new DeleteHistory(Q1), new DeleteHistory(A1));
        List<DeleteHistory> actual = deleteHistories.value();

        assertThatThrownBy(() -> {
            actual.add(new DeleteHistory(A2));
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
