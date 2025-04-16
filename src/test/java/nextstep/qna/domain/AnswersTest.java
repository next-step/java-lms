package nextstep.qna.domain;

import static nextstep.qna.CommonTestFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswersTest {
    public Answers differentWriterSet = new Answers();
    public Answers sameWriterSet = new Answers();

    @BeforeEach
    void setUp() {
        differentWriterSet.add(A1);
        differentWriterSet.add(A2);
        differentWriterSet.add(A3);
        sameWriterSet.add(A1);
        sameWriterSet.add(A3);
    }

    @Test
    void 답변자가_전부_같지_않을경우_예외() {
        assertAll(
            () -> assertDoesNotThrow(
                () -> sameWriterSet.deleteAllBy(NsUserTest.JAVAJIGI)
            ),
            () -> assertThatThrownBy(
                () -> differentWriterSet.deleteAllBy(NsUserTest.JAVAJIGI)
            ).isInstanceOf(CannotDeleteException.class)
        );
    }

    @Test
    void 답변자가_모두_같을경우_삭제() {
        sameWriterSet.deleteAllBy(NsUserTest.JAVAJIGI);
        assertAll(
            () -> assertTrue(A1.isDeleted()),
            () -> assertTrue(A3.isDeleted())
        );
    }
}
