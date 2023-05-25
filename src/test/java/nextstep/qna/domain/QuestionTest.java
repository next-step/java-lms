package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 답변삭제불가_다른작성자의답변존재() {
        Question actual = new Question(NsUser.GUEST_USER, "title3", "contents3");
        actual.addAnswer(new Answer(NsUserTest.JAVAJIGI, actual, "question1"));

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> actual.delete(NsUser.GUEST_USER))
            .withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 답변삭제가능() {
        Question actual = new Question(NsUser.GUEST_USER, "title3", "contents3");
        actual.addAnswer(new Answer(NsUser.GUEST_USER, actual, "question1"));

        assertThatNoException().isThrownBy(() -> actual.delete(NsUser.GUEST_USER));
    }

    @Test
    void 질문삭제권한없음() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
            .withMessageMatching("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 질문삭제권한존재() {
        assertThatNoException().isThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI));
    }

}
