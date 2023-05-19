package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;


public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 로그인_사용자와_질문한_사람이_같은지_확인() {
        assertThatThrownBy(() -> {
            Q1.validateOwner(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

}
