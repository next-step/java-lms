package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {
    @Test
    @DisplayName("DeleteHistories 생성")
    void create() {
        // given
        DeleteHistories deleteHistories = new DeleteHistories(
                new DeleteHistory(), new DeleteHistory()
        );

        // when
        assertThat(deleteHistories.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("DeleteHistory 추가")
    void add() {
        // given
        DeleteHistories deleteHistories = new DeleteHistories();
        DeleteHistory deleteHistory = new DeleteHistory();

        // when
        deleteHistories.add(deleteHistory);

        // then
        assertThat(deleteHistories).isEqualTo(new DeleteHistories(deleteHistory));
    }

    @Test
    @DisplayName("DeleteHistories 병합")
    void addAll() {
        // given
        DeleteHistories deleteHistories = new DeleteHistories();
        DeleteHistory deleteHistory = new DeleteHistory();
        DeleteHistory deleteHistory2 = new DeleteHistory();

        // when
        deleteHistories.addAll(new DeleteHistories(deleteHistory, deleteHistory2));

        // then
        assertThat(deleteHistories).isEqualTo(new DeleteHistories(deleteHistory, deleteHistory2));
    }

}