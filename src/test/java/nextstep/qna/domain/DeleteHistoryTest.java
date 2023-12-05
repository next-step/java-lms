package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {
    @Test
    @DisplayName("[DeleteHistory.equals()] 동등 테스트")
    public void equalsTest() {
        assertThat(new DeleteHistory(ContentType.QUESTION, 13L, NsUserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistory(ContentType.QUESTION, 13L, NsUserTest.JAVAJIGI));

        assertThat(new DeleteHistory(ContentType.ANSWER, 20L, NsUserTest.SANJIGI))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, 20L, NsUserTest.SANJIGI));

        assertThat(new DeleteHistory(ContentType.QUESTION, 20L, NsUserTest.SANJIGI))
                .isNotEqualTo(new DeleteHistory(ContentType.ANSWER, 20L, NsUserTest.SANJIGI));
    }
}