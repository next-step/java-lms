package nextstep.qna.domain;

import net.bytebuddy.asm.Advice;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.*;

public class AnswersTest {

    public static final Answers As1 = new Answers(List.of(A1));
    public static final Answers As2 = new Answers(List.of(A1, A2));

    @Test
    @DisplayName("삭제 테스트")
    public void deleteHistory() throws CannotDeleteException {
        As1.deleteAnswers(NsUserTest.JAVAJIGI);
        assertThat(As1.getAnswers().stream().map(Answer::isDeleted)).isNotIn(false);
    }
}
