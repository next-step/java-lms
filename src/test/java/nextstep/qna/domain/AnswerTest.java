package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 다른_사용자가_작성한_답변이_존재하면_에러발생(){
        assertThrows(CannotDeleteException.class, () -> {
            A1.delete(NsUserTest.SANJIGI);
        });
    }

    @Test
    void 답변삭제() throws CannotDeleteException {
        A1.delete(A1.getWriter());
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void 작성자가_null이면_에러발생(){
        Assertions.assertThrows(UnAuthorizedException.class, () -> {
            new Answer(null, QuestionTest.Q1, "Contents1");
        });
    }

    @Test
    void 질문이_null이면_에러발생(){
        Assertions.assertThrows(NotFoundException.class, () -> {
            new Answer(NsUserTest.JAVAJIGI, null, "Contents1");
        });
    }
}
