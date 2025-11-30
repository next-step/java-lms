package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void writer가_null이면_예외() {
        assertThatThrownBy(() -> new Answer(null, QuestionTest.Q1, "contents"))
            .isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    void question이_null이면_예외() {
        assertThatThrownBy(() -> new Answer(NsUserTest.JAVAJIGI, null, "contents"))
            .isInstanceOf(NotFoundException.class);
    }

    @Test
    void 본인이면_isOwner_true() {
        assertThat(A1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void 다른사람이면_isOwner_false() {
        assertThat(A1.isOwner(NsUserTest.SANJIGI)).isFalse();
    }

    @Test
    void 삭제하면_isDeleted_true() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "contents");
        answer.delete();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    void 삭제_기록_생성() {
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "contents");
        DeleteHistory history = answer.createDeleteHistory();
        assertThat(history).isNotNull();
    }
}
