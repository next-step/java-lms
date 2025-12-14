package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.qna.exception.unchecked.WrongRequestException;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    @Test
    void 요청자가_답변작성자와_동일한지_확인할_수_있다() {
        long requesterId = 1L;
        Question question = new Question(1L, "title1", "contents1");
        Answer answer = new Answer(requesterId, question, "Answers Contents1");

        assertThat(answer.isOwner(requesterId)).isTrue();
    }

    @Test
    void 삭제된_답변객체는_삭제이력을_만들수_있다() {
        Question question = new Question(1L, "title1", "contents1");
        Answer answer = new Answer(1L, question, "Answers Contents1");
        answer.putOnDelete(1L);

        assertThat(answer.createAnswerDeleteHistory(LocalDateTime.now())).isNotNull();
    }

    @Test
    void 삭제되지_않은_질문객체를_삭제이력_객체로_만들수_없다() {
        Question question = new Question(1L, "title1", "contents1");
        Answer answer = new Answer(1L, question, "Answers Contents1");

        assertThatThrownBy(
                () -> answer.createAnswerDeleteHistory(LocalDateTime.now())
        ).isInstanceOf(WrongRequestException.class);
    }


}
