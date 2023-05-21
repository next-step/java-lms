package nextstep.qna.domain;

import nextstep.qna.domain.enums.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {
    @DisplayName("DeleteHistory 객체가 잘 생성되는지 확인")
    @Test
    void 객체가_정상적으로_생성되는지_확인() {
        assertThat(DeleteHistory.of(ContentType.ANSWER, QuestionTest.Q1.getId(), QuestionTest.Q1.getDetail().getWriter(), LocalDateTime.now())).isInstanceOf(DeleteHistory.class);
    }
}
