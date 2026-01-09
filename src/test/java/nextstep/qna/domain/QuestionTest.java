package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 로그인_사용자와_질문_작성자가_같고_답변이_없는_경우_질문을_삭제할_수_있다() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");

        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }
    
    @Test
    void 로그인_사용자와_질문_작성자가_다른_경우_질문을_삭제할_수_없다(){
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");

        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
    
    @Test
    void 질문자와_모든_답변자가_같은_경우_질문을_삭제할_수_있다() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "answers contents1"));
        assertThatNoException().isThrownBy(() -> {
            question.delete(NsUserTest.JAVAJIGI);
        });

        assertThat(question.isDeleted()).isTrue();
    }
    
    @Test
    void 질문자와_다른_답변자가_있는_경우_질문을_삭제할_수_없다() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");
        question.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answers contents1"));

        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);

        assertThat(question.isDeleted()).isFalse();
    }
    
    @Test
    void 질문을_삭제할_경우_DeleteHistory_목록을_반환한다() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        question.addAnswer(new Answer(1L, NsUserTest.JAVAJIGI, question, "answers contents1"));

        question.delete(NsUserTest.JAVAJIGI);
        List<DeleteHistory> deleteHistories = question.deleteHistories();

        assertThat(deleteHistories).hasSize(2);
    }
}
