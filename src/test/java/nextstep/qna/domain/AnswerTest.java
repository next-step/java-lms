package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName(value = "Answer 생성자 작성자 정보 예외처리 검사")
    void test1() {
        assertThatThrownBy(() -> {
            new Answer(null, null, QuestionTest.Q1, "Answers Contents1");
        }).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    @DisplayName(value = "Answer 생성자 질문 정보 예외처리 검사")
    void test2() {
        assertThatThrownBy(() -> {
            new Answer(null, NsUserTest.JAVAJIGI, null, "Answers Contents1");
        }).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName(value = "답변 삭제 후 삭제 정보 검사")
    void test3() {
        DeleteHistory delete = A1.delete(NsUserTest.JAVAJIGI);

        assertThat(delete).isEqualTo(new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    @DisplayName(value = "답변 삭제 후 삭제 상태 참으로 변환 검사")
    void test4() {
        A1.delete(NsUserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName(value = "작성자와 다른 사용자가 답변 작성 시 삭제 불가")
    void test5() {
        A1.delete(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> {
            A1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
