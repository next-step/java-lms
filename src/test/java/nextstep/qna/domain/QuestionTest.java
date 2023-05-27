package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QuestionTest {

    @Test
    public void delete_success() {
        // given
        final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1" );
        final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        final Answer A2 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");

        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        // when
        Q1.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문자와 답변글의 모든 답변자 다른 경우, 삭제할 수 없습니다.")
    public void delete_fail_case1() {
        // given
        final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1" );
        final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        final Answer A3 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents1");

        Q1.addAnswer(A1);
        Q1.addAnswer(A3);

        // then
        assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(UnAuthorizedException.class)
                .hasMessage("질문자와 답변글의 모든 답변자 다른 경우, 삭제할 수 없습니다.");

        assertThat(Q1.isDeleted()).isFalse();
        assertThat(A1.isDeleted()).isFalse();
        assertThat(A3.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("질문을 삭제할 권한이 없습니다.")
    public void delete_fail_case2() {
        // given
        final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1" );
        final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        final Answer A2 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");

        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        // when
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(UnAuthorizedException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");

        assertThat(Q1.isDeleted()).isFalse();
        assertThat(A1.isDeleted()).isFalse();
        assertThat(A2.isDeleted()).isFalse();
    }
}
