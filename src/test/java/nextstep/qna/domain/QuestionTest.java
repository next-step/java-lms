package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void owner_여부_확인_테스트() {
        assertThat(Q1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(Q2.isOwner(NsUserTest.SANJIGI)).isTrue();
    }

    @Test
    void delete_가능_여부_테스트() throws CannotDeleteException {
        Q1.deleteQuestion(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(Q2.isDeleted()).isFalse();
    }

    @Test
    void deleteQuestion_question_owner_가_다를_경우_테스트_오류() {
        assertThatThrownBy(() -> Q1.deleteQuestion(Q2.getWriter())).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void deleteQuestion_answer_owner_가_다를_경우_테스트_오류() {
        Q1.addAnswer(new Answer(Q1.getWriter(), Q1, "test"));
        assertThatThrownBy(() -> Q1.deleteQuestion(Q2.getWriter())).isInstanceOf(CannotDeleteException.class);
    }
}
