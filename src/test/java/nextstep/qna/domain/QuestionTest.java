package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문자와 로그인 사용자가 다르면 삭제가 불가능하다")
    @Test
    void checkDeletePermission() throws CannotDeleteException {
        assertThatThrownBy(() -> Q1.checkDeletePermission(NsUserTest.SANJIGI))
                        .isInstanceOf(CannotDeleteException.class)
                        .hasMessage("질문을 삭제할 권한이 없습니다.");

    }


    @DisplayName("질문의 삭제 상태 플래그를 true로 변경한다")
    @Test
    void markDeleted(){
        Q1.markDeleted();

        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

    
    

}
