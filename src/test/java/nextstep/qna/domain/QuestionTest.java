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
    public void delete() throws Exception {
        Q1.addAnswer(AnswerTest.A1);
        assertThat(Q1.delete(NsUserTest.JAVAJIGI)).isEqualTo(List.of(DeleteHistoryTest.Q1DeleteHistory, DeleteHistoryTest.A1DeleteHistory));
    }

    @Test
    public void deleteByWronguser() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
