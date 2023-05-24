package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 다른유저글_삭제실패() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 내글_삭제성공_답변도_같음() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI);
        assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories).hasSize(2)
                );
    }
}
