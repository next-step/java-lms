package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteHistoryTest {

    @Test
    @DisplayName("질문 삭제 히스토리를 생성할 수 있다")
    public void question_delete_history() {
        assertThat(DeleteHistory.question(0L, NsUser.GUEST_USER)).extracting(DeleteHistory::contentType).isEqualTo(ContentType.QUESTION);
    }

}
