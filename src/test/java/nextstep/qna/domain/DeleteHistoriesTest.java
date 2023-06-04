package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DeleteHistoriesTest {

    public static Question question = new Question(3L, NsUserTest.JAVAJIGI, new QuestionContents("title1", "contents1"));

    @Test
    @DisplayName("[요구사항 1] DeleteHistory 추가에 따른 사이즈 확인")
    void 요구사항_1() {
        // when : DeleteHistory 단건 추가
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.addDeleteHistory(question.toHistory());

        // then : 사이즈 확인
        assertThat(deleteHistories.getDeleteHistories().size()).isEqualTo(1);
    }
}
