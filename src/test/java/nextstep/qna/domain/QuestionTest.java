package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.*;
import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("성공 - 로그인 사용자와 질문한 사람이 같은 경우 삭제가 가능하다.")
    void success_delete_qna_condition() throws Exception {
        Q1.addAnswers(new Answers(
                List.of(javajigiAnswer(Q1), javajigiAnswer(Q1))
        ));
        List<DeleteHistory> deleteHistories = Q1.delete(JAVAJIGI);

        assertThat(deleteHistories).hasSize(3)
                .extracting("deletedBy")
                .containsOnly(JAVAJIGI, JAVAJIGI, JAVAJIGI);
    }

    @Test
    @DisplayName("실패 - 로그인 사용자와 질문한 사람이 다른 경우 삭제가 불가능하다.")
    void fail_delete_qna_condition() throws Exception {
        Assertions.assertThatThrownBy(() -> Q1.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("실패 - 서로 다른 답변 작성자가 있는 경우 질문을 삭제할수 없다.")
    void fail_delete_qna_different_writer_answer() throws Exception {
        Q2.addAnswers(new Answers(
                List.of(javajigiAnswer(Q2), sanjigiiAnswer(Q2), sanjigiiAnswer(Q2))
        ));

        Assertions.assertThatThrownBy(() -> Q2.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("성공 - 로그인 유저와 질문자가 같고, 질문만 존재하고 답변이 없을 경우 삭제가 가능하다.")
    void success_delete_qna_and_save_delete_history() throws Exception {
        Q1.addAnswers(new Answers(List.of()));
        List<DeleteHistory> deleteHistories = Q1.delete(JAVAJIGI);

        assertThat(deleteHistories).hasSize(1)
                .extracting("deletedBy")
                .containsOnly(JAVAJIGI);
    }

}
