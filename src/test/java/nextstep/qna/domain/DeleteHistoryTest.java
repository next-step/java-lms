package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class DeleteHistoryTest {

    @Test
    void shouldTwoHistoriesBeEqual() {
        DeleteHistory deleteHistory1 = new DeleteHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now());
        DeleteHistory deleteHistory2 = new DeleteHistory(ContentType.QUESTION, NsUserTest.JAVAJIGI.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now());

        assertThat(deleteHistory1.equals(deleteHistory2)).isTrue();
    }
}
