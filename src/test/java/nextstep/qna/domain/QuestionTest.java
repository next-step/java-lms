package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.qna.domain.AnswerTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName(value = "답변이 없을 경우 삭제 상태 변경 가능")
    void test1() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName(value = "질문자와 답변자가 동일할 경우 질문, 답변 삭제 상태 변경 가능")
    void test2() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName(value = "질문자가 아닌 다른 유저가 질문을 삭제할 경우 예외처리 검사")
    void test3() {
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName(value = "삭제 후 삭제 이력 정보 확인")
    void test4() throws CannotDeleteException {
        List<DeleteHistory> delete = Q2.delete(NsUserTest.SANJIGI);

        assertThat(delete).contains(new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.SANJIGI, LocalDateTime.now()));
    }

    @Test
    @DisplayName(value = "질문자와 답변자가 다를 경우 삭제 불가")
    void test5() {
        Q1.addAnswer(A2);
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
