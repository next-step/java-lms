package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문 데이터를 soft delete 한다.")
    void 질문_데이터를_SOFT_DELETE_할_수_있다() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        boolean result = question.delete();
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.")
    void 로그인_사용자와_질문한_사람이_같은_경우_삭제_가능() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문에 답변이 없는 경우 삭제할 수 있다.")
    void 질문에_답변이_없는_경우_삭제할_수_있다() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문의 질문자와 여러개의 답변글의 사용자가 같은 경우 삭제가 가능하다")
    void 질문의_질문자와_여러개의_답변의_답변자가_같은_경우_삭제_가능() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents2");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 삭제할 수 없다.")
    void 로그인_사용자와_질문한_사람이_다른_경우_삭제_불가능() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문의 질문자와 여러개의 답변글의 사용자가 다른 경우 삭제할 수 없다")
    void 질문의_질문자와_여러개의_답변의_답변자가_다른_경우_삭제_불가능() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answer2 = new Answer(NsUserTest.SANJIGI, question, "Answers Contents2");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
