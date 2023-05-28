package nextstep.qna.domain;

import net.bytebuddy.asm.Advice;
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
    @DisplayName("삭제 내역 남기는 것 테스트")
    public void deleteHistory() {
        List<DeleteHistory> deleteHistories = As1.createDeleteHistory();
        assertThat(deleteHistories.size()).isEqualTo(1);
    }
}
