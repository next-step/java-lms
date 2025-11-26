package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 글쓴이가_로그인한_유저와_같지않으면_에러발생(){
        assertThrows(CannotDeleteException.class, () -> {
            Q1.validateDeletable(NsUserTest.SANJIGI);
        });
    }

    @Test
    void 질문_답글_삭제_목록_조회(){
        List<DeleteHistory> deleteHistories = Q1.delete();
        assertThat(deleteHistories).hasSize(1);
    }




}
