package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    public final Answers answers = new Answers();

    @DisplayName("질문을 삭제하면 올바른 삭제 이력 리스트를 반환한다.")
    @Test
    public void 질문_삭제_성공하면_삭제_이력_반환_테스트() throws CannotDeleteException {
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A3);

        assertThat(answers.delete(NsUserTest.JAVAJIGI))
                .containsExactlyElementsOf(
                        List.of(
                                new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now()),
                                new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now())
                        )
                );
    }

    @DisplayName("다른 유저가 쓴 댓글이 있으면 삭제 시도할 경우 예외가 발생한다.")
    @Test
    public void 다른_유저가_쓴_댓글이_있는_경우_예외_발생_테스트() {
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
