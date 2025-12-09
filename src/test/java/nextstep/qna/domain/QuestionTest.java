package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


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
                new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1"));

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
        long requesterId = 1L;
        Question question = new Question(1L, "title1", "contents1");

        question.putOnDelete(requesterId);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void 질문의_모든답변을_삭제상태로_변경할_수_있다() {
        long requesterId = 1L;
        Question question = new Question(1L, "title1", "contents1");
        Answer firstAnswers = new Answer(1L, question, "Answers Contents1");
        question.addAnswer(firstAnswers);
        Answer secondAnswers = new Answer(1L, question, "Answers Contents2");
        question.addAnswer(secondAnswers);

        question.putOnAllAnswersDelete(requesterId);

        assertThat(firstAnswers.isDeleted()).isTrue();
        assertThat(secondAnswers.isDeleted()).isTrue();
    }

}
