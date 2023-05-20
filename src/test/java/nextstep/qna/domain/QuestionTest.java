package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문삭제불가검증() {
        assertThatThrownBy(() -> {
            Q1.checkDeletability(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class).hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 질문삭제가능검증() throws CannotDeleteException {
        Q1.checkDeletability(NsUserTest.JAVAJIGI);
    }

    @Test
    void 질문삭제테스트() {
        List<DeleteHistory> deleteHistories = Q1.delete();

        Assertions.assertThat(Q1.isDeleted()).isTrue();
        Assertions.assertThat(deleteHistories.size()).isEqualTo(1);
    }


}
