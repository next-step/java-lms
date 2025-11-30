package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void deleteByQuestionQuestion() throws CannotDeleteException {
      Q1.deleteBy(NsUserTest.JAVAJIGI);
      assertThat(Q1.isDeleted()).isTrue();
    }
}
