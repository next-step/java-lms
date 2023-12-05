package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.service.QnAService;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문 데이터의 상태를 삭제 상태로 변경한다")
    @Test
    void changeDeleted() {
        Q1.changeDeleted(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문을 삭제할 떄 로그인된 유저와 질문의 작성자가 다를 때 예외를 던진다")
    @Test
    void deleteQuestionByWrongUser() {
        assertThatThrownBy(() -> Q1.changeDeleted(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
