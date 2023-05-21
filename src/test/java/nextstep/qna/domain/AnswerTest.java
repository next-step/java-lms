package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @DisplayName("다른 유저가 쓴 답변이 있으면 삭제할 수 없고 예외가 발생한다.")
    @Test
    public void 다른_유저_답변이_있는_경우_예외_발생_테스트() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변을 삭제하면 올바른 삭제 이력을 반환한다.")
    @Test
    public void 답변_삭제_성공하면_삭제_이력_반환_테스트() throws CannotDeleteException {
        DeleteHistory delete = A1.delete(NsUserTest.JAVAJIGI);
        assertThat(delete).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
