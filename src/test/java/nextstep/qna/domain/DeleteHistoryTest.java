package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeleteHistoryTest {

    @Test
    @DisplayName("DeleteHistory 객체 생성 테스트")
    void DeleteHistory_객체_생성_테스트() {


        DeleteHistory deleteHistory = DeleteHistory.of(ContentType.QUESTION, 1, NsUserTest.JAVAJIGI);

        assertAll(
                () -> assertThat(deleteHistory).isNotNull(),
                () -> assertThat(deleteHistory).isInstanceOf(DeleteHistory.class)
        );

    }

}