package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.fixture.QuestionFixture;
import nextstep.users.domain.NsUserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class QuestionTest {

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능해야 한다.")
    void testDeleteUserSameQuestioner() throws CannotDeleteException {
        // When
        final List<DeleteHistory> delete = QuestionFixture.Q1.delete(NsUserFixture.JAVAJIGI);

        // Then
        assertAll(
                () -> assertThat(QuestionFixture.Q1.isDeleted())
                        .isTrue(),
                () -> assertThat(delete)
                        .hasSize(1)
        );
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다를 경우 삭제 불가 예외가 발생한다.")
    void testDeleteUserDifferentQuestioner() {
        // When & Then
        assertThatThrownBy(() ->
                QuestionFixture.Q2.delete(NsUserFixture.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

}
