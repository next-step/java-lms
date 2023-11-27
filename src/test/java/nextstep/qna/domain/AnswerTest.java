package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @DisplayName("답글 작성자와 사용자가 같은지 확인합니다.")
    @Test
    void isOwner() {
        // given
        // when
        boolean result = A1.isOwner(NsUserTest.JAVAJIGI);
        // then
        assertThat(result).isTrue();
    }

    @DisplayName("답글 작성자와 사용자가 다르면 false를 반환합니다.")
    @Test
    void falseOwner() {
        // given
        // when
        boolean result = A1.isOwner(NsUserTest.SANJIGI);
        // then
        assertThat(result).isFalse();
    }

    @DisplayName("답글의 삭제 상태를 변경합니다.")
    @Test
    void changeDelete() {
        // given
        // when
        A1.changeStatus(true);
        // then
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("deleteHistory 목록을 생성합니다.")
    @Test
    void makeDeleteHistory() {
        // given
        // when
        DeleteHistory deleteHistory = A1.writeDeleteHistory();
        // then
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI));
    }
}
