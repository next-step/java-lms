package nextstep.qna.domain;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 답변을_삭제한다() throws CannotDeleteException {
        assertThat(A1.delete(JAVAJIGI)).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), A1.getCreatedDate()));
    }

    @Test
    void 답변을_삭제할_떼_다른_사람이_쓴_답변이_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> A1.validateOwnership(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
