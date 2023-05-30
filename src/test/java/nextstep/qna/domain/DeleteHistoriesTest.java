package nextstep.qna.domain;

import nextstep.qna.domain.enums.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {
    private DeleteHistories deleteHistories1;
    private DeleteHistories deleteHistories2;

    @BeforeEach
    public void beforeEach() {
        deleteHistories1 = DeleteHistories.create();
        deleteHistories2 = DeleteHistories.create();
        deleteHistories2.add(DeleteHistory.ofAnswer(AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()));
    }

    @DisplayName("DeleteHistories 객체가 잘 생성되는지 확인")
    @Test
    void 객체가_정상적으로_생성되는지_확인() {
        assertThat(deleteHistories1).isInstanceOf(DeleteHistories.class);
    }

    @DisplayName("DeleteHistory 객체가 한건이 잘 추가되는지 확인")
    @Test
    void DeleteHistory_객체_한건이_정상적으로_추가되는지_확인() {
        deleteHistories1.add(DeleteHistory.ofAnswer(AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()));
        assertThat(deleteHistories1.getDeleteHistories()).hasSize(1);
    }

    @DisplayName("DeleteHistory 객체끼리 합성이 되는지 확인")
    @Test
    void DeleteHistory_객체끼리_합성이_되는지_확인() {
        deleteHistories1.concat(deleteHistories2);
        assertThat(deleteHistories1.getDeleteHistories()).hasSize(1);
    }
}
