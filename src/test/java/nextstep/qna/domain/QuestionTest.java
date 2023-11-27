package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
        boolean result = question.delete(NsUserTest.JAVAJIGI);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("질문에 답변이 없는 경우 삭제할 수 있다.")
    void 질문에_답변이_없는_경우_삭제할_수_있다() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        boolean result = question.delete(NsUserTest.JAVAJIGI);
        assertThat(result).isTrue();
    }
}
