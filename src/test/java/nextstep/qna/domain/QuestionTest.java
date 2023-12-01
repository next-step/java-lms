package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void ownerIs_예외_케이스() {
        Assertions.assertThatThrownBy(() -> Q1.canDeleteBy(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void isDeletedBy_정상_케이스() throws CannotDeleteException {
        Q1.isDeletedBy(NsUserTest.JAVAJIGI);
        Assertions.assertThat(Q1.isDeleted()).isTrue();
    }
}
