package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteHistoryTest {

    @Test
    @DisplayName("DeleteHistory 객체 생성 테스트")
    void DeleteHistory_객체_생성_테스트() {


        DeleteHistory deleteHistory = DeleteHistory.of(ContentType.QUESTION, 1, NsUserTest.JAVAJIGI.getUserId());

        assertAll(
                () -> assertThat(deleteHistory).isNotNull(),
                () -> assertThat(deleteHistory).isInstanceOf(DeleteHistory.class)
        );

    }


    @Test
    @DisplayName("ContentType가 null 일 경우 예외를 던진다 ")
    void ContentType가_null_인_경우() {


        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> DeleteHistory.of(null, 1, NsUserTest.JAVAJIGI.getUserId()));
        assertEquals("컨텐츠 타입에 값이 입력되질 않았어요 :(", exception.getMessage());

    }


    @Test
    @DisplayName("deletedBy 가 null 일 경우 예외를 던진다  ")
    void deletedBy가_null_인경우() {
        Throwable exception = Assertions.assertThrows(UnAuthorizedException.class, () -> DeleteHistory.of(ContentType.QUESTION, 1, null));
        assertEquals("삭제자에 값이 입력되질 않았어요 :(", exception.getMessage());

    }

    @Test
    @DisplayName("contentId가 0일 경우 예외를 던진다")
    void contentId가_0인경우() {

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> DeleteHistory.of(ContentType.QUESTION, 0, NsUserTest.JAVAJIGI.getUserId()));
        assertEquals("유효하지 않는 컨텐츠 아이디에요 :( [ 입력 값 : 0]", exception.getMessage());

    }

}