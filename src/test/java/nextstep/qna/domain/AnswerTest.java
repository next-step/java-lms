package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 다른사람_쓴_답변_삭제_불가() {
        assertThatThrownBy(() -> {
            A1.checkCanDelete(A2.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 답변삭제와_삭제히스토리생성() {
        DeleteHistory deleteHistory = A1.makeDeleteHistory();
        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory).isNotNull();
    }

    @Test
    void 작성자확인() {
        assertThat(A2.isOwner(A2.getWriter())).isTrue();
    }
}
