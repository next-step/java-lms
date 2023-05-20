package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("작성자가 아닌 사람은 삭제할 수 없다.")
    @Test
    public void deleteTest() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI, 0))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("삭제를 성공하면, 올바른 삭제 이력을 반환한다.")
    @Test
    public void shouldReturnProperDeleteHistory() throws CannotDeleteException {
        List<DeleteHistory> delete = Q1.delete(NsUserTest.JAVAJIGI, 0);
        assertThat(delete).contains(
                new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
