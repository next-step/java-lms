package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    @Test
    @DisplayName("성공 - add 메서드가 삭제내역을 추가한다.")
    void addTest() {
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, 1L, Q1.getWriter());
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(deleteHistory);

        assertThat(deleteHistories.getDeleteHistories()).hasSize(1);
        assertThat(deleteHistories.getDeleteHistories()).containsExactly(deleteHistory);
    }
}