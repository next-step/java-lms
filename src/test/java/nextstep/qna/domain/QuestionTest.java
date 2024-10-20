package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


    @DisplayName("작성자와 이름이 다른 경우 예외")
    @Test
    void 작성자_이름_다름_예외() {
        assertThatThrownBy(() -> Q1.deletedQna(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }



}
