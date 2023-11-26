package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {
    @Test
    @DisplayName("add 메서드를 사용하면, DeleteHistory 객체 하나를 저장한다.")
    void testAdd() {
        //given
        DeleteHistories deleteHistories = new DeleteHistories();
        final int sizeBeforeAdd = deleteHistories.size();

        //when
        deleteHistories.add(new DeleteHistory());
        final int sizeAfterAdd = deleteHistories.size();


        //then
        assertThat(sizeAfterAdd).isEqualTo(sizeBeforeAdd + 1);
    }

    @Test
    @DisplayName("size 메서드를 사용하면, 현재 저장된 DeleteHistory의 수를 반환한다.")
    void testSize() {
        //given
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(new DeleteHistory());
        deleteHistories.add(new DeleteHistory());

        //when
        int actualSize = deleteHistories.size();

        //then
        assertThat(actualSize).isEqualTo(2);
    }
}
