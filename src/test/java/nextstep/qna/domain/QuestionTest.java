package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.qna.exception.unchecked.CannotDeleteException;
import nextstep.qna.exception.unchecked.WrongRequestException;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    void 요청자가_질문자와_동일한지_확인할_수_있다() {
        long requesterId = 1L;
        Question question = new Question(1L, "title1", "contents1");

        assertThat(question.isOwner(requesterId)).isTrue();
    }

    @Test
    void 질문에_답변이_있는지_확인할_수_있다() {
        Question question = new Question(1L, "title1", "contents1");
        question.addAnswer(
                new Answer(1L, question, "Answers Contents1"));

        assertThat(question.hasAnswers()).isTrue();
    }

    @Test
    void 질문에_답변이_없는지_확인할_수_있다() {
        Question question = new Question(1L, "title1", "contents1");

        assertThat(question.hasAnswers()).isFalse();
    }

    @Test
    void 질문의_답변들이_질문자가_작성한_답변만_존재하는지_확인할_수_있다() {
        Question question = new Question(1L, "title1", "contents1");
        question.addAnswer(new Answer(1L, question, "Answers Contents1"));
        question.addAnswer(new Answer(1L, question, "Answers Contents2"));

        assertThat(question.isAllSameContentsWriter()).isTrue();
    }

    @Test
    void 질문의_답변들이_여러사람이_작성한_답변이_존재하는지_확인할_수_있다() {
        Question question = new Question(1L, "title1", "contents1");
        question.addAnswer(new Answer(1L, question, "Answers Contents1"));
        question.addAnswer(new Answer(2L, question, "Answers Contents2"));

        assertThat(question.isAllSameContentsWriter()).isFalse();
    }

    @Test
    void 질문을_삭제상태로_변경할_수_있다() {
        Question question = new Question(1L, "title1", "contents1");

        question.putOnDelete(1L);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void 비정상적인_요청자번호로_질문을_삭제시도할_수_없다() {
        Question question = new Question(1L, "title1", "contents1");

        assertThatThrownBy(
                () -> question.putOnDelete(-1L)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 요청자와_질문자가_다르면_질문을_삭제시도할_수_없다() {
        Question question = new Question(1L, "title1", "contents1");

        assertThatThrownBy(
                () -> question.putOnDelete(2L)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 답변중_질문자와_다른유저의_답변이_있다면_질문을_삭제시도할_수_없다() {
        Question question = new Question(1L, "title1", "contents1");
        question.addAnswer(new Answer(1L, question, "Answers Contents1"));
        question.addAnswer(new Answer(2L, question, "Answers Contents2"));

        assertThatThrownBy(
                () -> question.putOnDelete(1L)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문의_모든답변을_삭제상태로_변경할_수_있다() {
        Question question = new Question(1L, "title1", "contents1");
        Answer firstAnswers = new Answer(1L, question, "Answers Contents1");
        question.addAnswer(firstAnswers);
        Answer secondAnswers = new Answer(1L, question, "Answers Contents2");
        question.addAnswer(secondAnswers);

        question.putOnAllAnswersDelete(1L);

        assertThat(firstAnswers.isDeleted()).isTrue();
        assertThat(secondAnswers.isDeleted()).isTrue();
    }

    @Test
    void 삭제된_질문객체는_삭제이력을_만들수_있다() {
        Question question = new Question(1L, "title1", "contents1");
        question.putOnDelete(1L);

        assertThat(question.createQuestionDeleteHistory(LocalDateTime.now())).isNotNull();
    }

    @Test
    void 삭제되지_않은_질문객체를_삭제이력_객체로_만들수_없다() {
        Question question = new Question(1L, "title1", "contents1");

        assertThatThrownBy(
                () -> question.createQuestionDeleteHistory(LocalDateTime.now())
        ).isInstanceOf(WrongRequestException.class);
    }
}
