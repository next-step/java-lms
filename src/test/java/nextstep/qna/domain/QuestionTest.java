package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다
 *//**/
public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("권한이 없으면 삭제 실패")
    @Test
    void deleteWithNotEqualsLoginUser() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);

    }

    @DisplayName("질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.")
    @Test
    void deleteThenDeletedTypeIsTrue() throws Exception{
        Assertions.assertThat(Q1.isDeleted()).isFalse();
        Q1.delete(NsUserTest.JAVAJIGI);
        Assertions.assertThat(Q1.isDeleted()).isTrue();
    }

}
