package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 로그인사용자가_질문작성자가_아닌_경우_CannotDeleteException() {
        assertThatThrownBy(() -> {
            Q1.validateDeletable(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 로그인사용자가_질문작성자인_경우_삭제가능() throws CannotDeleteException {
        Q1.validateDeletable(NsUserTest.JAVAJIGI);
    }

    @Test
    void 질문자와_답변자가_다른_경우() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> {
            Q1.validateQuestionAndAnswersDeletable(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 질문자와_답변글의_모든_답변자가_같은_경우_삭제가능() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.validateQuestionAndAnswersDeletable(NsUserTest.JAVAJIGI);
    }

}
