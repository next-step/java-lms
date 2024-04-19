package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void 질문삭제_정상동작_확인테스트() throws CannotDeleteException {
        Question question = Q1.setDeleted(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

}
