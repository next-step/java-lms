package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


    @Test
    @DisplayName("다른 사람의 글을 삭제하려고 하면 예외가 발생한다")
    void 작성하지_않은_글_삭제_시도_시_예외_발생() {
        assertThatThrownBy(() -> Q1.validIfUserCanDeletePost(Q2.getWriter()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("내가 쓴 글이고 답변이 없는 경우 바로 삭제 가능하다")
    void 내가_쓴_글_답변이_없는_경우_바로_삭제_가능() throws CannotDeleteException {
        final Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        assertThat(question.delete(Q1.getWriter())).hasSize(1);
    }

    @Test
    @DisplayName("답변 삭제 시 데이터의 상태를 삭제상태로 변경하는지 확인한다")
    void 삭제_시_삭제상태_변경_확인() throws CannotDeleteException {
        final Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        question.delete(Q1.getWriter());

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우 삭제 가능")
    void 질문자와_답변글의_모든_답변자가_같으면_삭제가능() throws CannotDeleteException {
        final Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        question.delete(Q1.getWriter());

        assertThat(question.isDeleted()).isTrue();
    }
    @Test
    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우 삭제 가능")
    void 질문자와_답변글의_답변자가_다르면_삭제_불가능() throws CannotDeleteException {
        final Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        question.addAnswer(new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents2"));

        assertThatThrownBy(
                () -> question.delete(Q1.getWriter()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

    }

    @Test
    @DisplayName("삭제한 질문글과 질문글에 있던 답변글 삭제 기록안에 있는지")
    void 질문글_삭제기록_확인() throws CannotDeleteException {
        final Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        List<DeleteHistory> deleteHistories = question.delete(Q1.getWriter());

        assertThat(deleteHistories).hasSize(3);
    }

}
