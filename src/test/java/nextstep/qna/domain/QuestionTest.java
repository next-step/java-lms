package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("성공 - 로그인 사용자와 질문한 사람이 같은 경우 삭제가 가능하다.")
    void success_delete_qna_condition() throws Exception {
        Q1.addAnswers(new Answers(
                List.of(new Answer(JAVAJIGI, Q1), new Answer(JAVAJIGI, Q1))
        ));
        Q1.delete(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("실패 - 로그인 사용자와 질문한 사람이 다른 경우 삭제가 불가능하다.")
    void fail_delete_qna_condition() throws Exception {
        Assertions.assertThatThrownBy(() -> Q1.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("성공 - qna 삭제 시 질문지와 댓글에 대한 삭제 히스토리가 생성된다")
    void success_delete_qna_and_save_delete_history() throws Exception {
        Q2.addAnswers(new Answers(
                List.of(new Answer(JAVAJIGI, Q2), new Answer(SANJIGI, Q2), new Answer(HONUX, Q2))
        ));


        List<DeleteHistory> deleteHistories = Q2.deleteHistory();

        assertThat(deleteHistories).hasSize(4);
    }

}
