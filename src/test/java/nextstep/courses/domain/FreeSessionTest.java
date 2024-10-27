package nextstep.courses.domain;

import nextstep.courses.domain.vo.session.CoverImage;
import nextstep.courses.domain.vo.session.DateRange;
import nextstep.courses.domain.vo.session.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.vo.session.CoverImageTest.*;
import static nextstep.courses.domain.vo.session.DateRangeTest.END;
import static nextstep.courses.domain.vo.session.DateRangeTest.START;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

public class FreeSessionTest {

    private FreeSession freeSession;

    @BeforeEach
    void setUp() {
        freeSession = new FreeSession(1L,
                1L,
                new DateRange(START, END),
                new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT),
                Status.PREPARE);
    }

//    @Test
//    void create() {
//        assertThatNoException().isThrownBy(() -> {
//            FreeSession freeSession = new FreeSession(1L);
//            FreeSession addedStudentsFreeSession = new FreeSession(1L, JAVAJIGI);
//        });
//    }

    @Test
    void register_성공() {
        FreeSession actual = new FreeSession(1L,
                1L,
                new DateRange(START, END),
                new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT),
                Status.PREPARE);
        freeSession.register(JAVAJIGI);
        assertThat(actual).isNotEqualTo(freeSession);

        actual.register(JAVAJIGI);
        assertThat(actual).isEqualTo(freeSession);
    }
}