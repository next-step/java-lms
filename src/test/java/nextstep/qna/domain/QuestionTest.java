package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("delete()")
    public void delete() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI);
        Assertions.assertThat(deleteHistories.get(0)).isEqualTo(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("throwsCannotDeleteExceptionIfCannotDelete()")
    public void throwsCannotDeleteExceptionIfCannotDelete() {
        Assertions.assertThatThrownBy(() -> {
            Q2.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
