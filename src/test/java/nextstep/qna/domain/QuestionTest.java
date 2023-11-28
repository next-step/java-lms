package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.qna.service.QnAService;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 로그인한_사용자와_질문한_사용자가_같은경우_삭제_가능하다() {
        // when
        Q1.delete(NsUserTest.JAVAJIGI);

        // when
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void 로그인한_사용자와_질문한_사용자가_다른경우_삭제할_수_없다() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(UnAuthorizedException.class)
                .hasMessage("삭제 권한이 존재하지 않습니다.");
    }
}
