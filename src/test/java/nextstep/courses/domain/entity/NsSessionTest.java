package nextstep.courses.domain.entity;

import static nextstep.courses.domain.field.ImageType.GIF;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.field.CoverImage;

class NsSessionTest {

    CoverImage coverImage;

    @BeforeEach
    void init() {
        coverImage = CoverImage.of(CoverImage.SIZE_LIMIT, 300L, 200L, GIF.getExtension());
    }

    @Test
    void 강의_시작일이_종료일보다_이후_날짜인_경우_실패하는_테스트() {
        LocalDate startDate = LocalDate.of(2023, Month.NOVEMBER, 30);
        LocalDate endDate = LocalDate.of(2023, Month.NOVEMBER, 29);
        assertThatThrownBy(() -> NsSession.freeOf(1L,
                                                  coverImage,
                                                  "free",
                                                  "open",
                                                  startDate,
                                                  endDate))
        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 강의_시작일과_종료일을_입력하지_않는_경우_실패하는_테스트() {
        LocalDate startDate = LocalDate.of(2023, Month.NOVEMBER, 30);
        LocalDate endDate = LocalDate.of(2023, Month.NOVEMBER, 29);
        LocalDate testDate = LocalDate.of(2023, Month.NOVEMBER, 29);
        LocalDate testDate2 = LocalDate.of(2023, Month.NOVEMBER, 29);

        assertThatThrownBy(() -> NsSession.freeOf(1L,
                                                  coverImage,
                                                  "free",
                                                  "open",
                                                  startDate,
                                                  endDate))
        .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> NsSession.freeOf(1L,
                                                  coverImage,
                                                  "free",
                                                  "open",
                                                  null,
                                                  testDate))
        .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> NsSession.freeOf(1L,
                                                  coverImage,
                                                  "free",
                                                  "open",
                                                  null,
                                                  testDate2))
        .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> NsSession.freeOf(1L,
                                                  coverImage,
                                                  "free",
                                                  "open",
                                                  null,
                                                  null))
        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 강의_커버이미지가_없는_경우_실패하는_테스트() {
        LocalDate startDate = LocalDate.of(2023, Month.NOVEMBER, 29);
        LocalDate endDate = LocalDate.of(2023, Month.NOVEMBER, 30);
        assertThatThrownBy(() -> NsSession.freeOf(1L,
                                                  null,
                                                  "free",
                                                  "open",
                                                  startDate,
                                                  endDate))
        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 강의_상태가_부적절한_경우_실패하는_테스트() {
        LocalDate startDate = LocalDate.of(2023, Month.NOVEMBER, 29);
        LocalDate endDate = LocalDate.of(2023, Month.NOVEMBER, 30);
        assertThatThrownBy(() -> NsSession.freeOf(1L,
                                                  coverImage,
                                                  "free",
                                                  "evil-status",
                                                  startDate,
                                                  endDate))
        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 유료_또는_무료_강의가_아닌경우_실패하는_테스트() {
        LocalDate startDate = LocalDate.of(2023, Month.NOVEMBER, 29);
        LocalDate endDate = LocalDate.of(2023, Month.NOVEMBER, 30);
        assertThatThrownBy(() -> NsSession.freeOf(1L,
                                                  coverImage,
                                                  "evil-type",
                                                  "open",
                                                  startDate,
                                                  endDate))
        .isInstanceOf(IllegalArgumentException.class);
    }
}
