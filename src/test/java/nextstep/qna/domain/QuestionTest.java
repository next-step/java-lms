package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "contents1");
    public static final Answer A2 = new Answer(NsUserTest.JAVAJIGI, Q1, "contents2");

    @BeforeEach
    public void setUp() {
        Q1.addAnswer(A1);
    }

    @Test
    public void delete_deleted가_true로_변경된다() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_로그인_사용자와_질문자가_같으면_질문을_삭제할_수_있다() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_로그인_사용자와_질문자가_같지않으면_CannotDeleteException이_발생한다() throws CannotDeleteException {
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_답변이_없는경우_삭제가_가능하다() throws CannotDeleteException {
        Q2.delete(NsUserTest.SANJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_질문자와답변글의모든답변자같은경우삭제가가능하다() throws CannotDeleteException {
        Q1.addAnswer(A2);

        Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_질문을삭제할때답변또한삭제해야한다_답변의삭제또한삭제상태를변경한다() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void delete_질문과_답변삭제이력에대한정보를_deleteHistories를활용해남긴다() throws CannotDeleteException {
        DeleteHistories deleteHistories = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories).isInstanceOf(DeleteHistories.class);
    }
}
