package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class DeleteHistoryTest {

    @DisplayName("모든 필드값이 동일한 DeleteHistory는 동일한 객체로 취급한다.")
    @Test
    void shouldTwoHistoriesBeEqual() {
        DeleteHistory deleteHistory1 = new DeleteHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now());
        DeleteHistory deleteHistory2 = new DeleteHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now());

        assertThat(deleteHistory1.equals(deleteHistory2)).isTrue();
    }
}
