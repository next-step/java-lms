package nextstep.courses.domain;

import static nextstep.courses.domain.Session.ALLOWED_EXT;
import static nextstep.courses.domain.Session.HEIGHT_MIN_SIZE;
import static nextstep.courses.domain.Session.WIDTH_MIN_SIZE;
import static nextstep.qna.domain.TestFixtures.FIXED_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import nextstep.courses.InvalidSessionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    @DisplayName("강의 데이터가 모두 유효하면 성공적으로 생성된다")
    void new_success() {
        assertThatNoException()
            .isThrownBy(
                () ->  new Session(
                    FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1),
                    generateSampleImage(WIDTH_MIN_SIZE, HEIGHT_MIN_SIZE, ALLOWED_EXT.get(0)),
                    FIXED_DATE_TIME
                )
            );
    }

    @Test
    @DisplayName("강의 종료일이 시작일보다 앞서면 예외가 발생한다")
    void new_fail_for_invalid_date() {
        assertThatThrownBy(
            () ->  new Session(
                FIXED_DATE_TIME.plusDays(1), FIXED_DATE_TIME,
                generateSampleImage(WIDTH_MIN_SIZE, HEIGHT_MIN_SIZE, ALLOWED_EXT.get(0)),
                FIXED_DATE_TIME
            )
        ).isInstanceOf(InvalidSessionException.class);
    }

    @Test
    @DisplayName("강의 커버 이미지 용량이 너무 크면 예외가 발생한다")
    void new_fail_for_image_file_size() {
        // TODO
    }

    @Test
    @DisplayName("지원하지 않는 이미지 확장자를 사용하면 예외가 발생한다")
    void new_fail_for_unsupported_ext() {
        assertThatThrownBy(
            () ->  new Session(
                FIXED_DATE_TIME.plusDays(1), FIXED_DATE_TIME,
                generateSampleImage(WIDTH_MIN_SIZE, HEIGHT_MIN_SIZE, "unsupported_ext"),
                FIXED_DATE_TIME
            )
        ).isInstanceOf(InvalidSessionException.class);
    }

    @Test
    @DisplayName("강의 커버 이미지 비율이 맞지 않으면 예외가 발생한다")
    void new_fail_for_invalid_image_ratio() {
        assertThatThrownBy(
            () ->  new Session(
                FIXED_DATE_TIME.plusDays(1), FIXED_DATE_TIME,
                generateSampleImage(WIDTH_MIN_SIZE, HEIGHT_MIN_SIZE + 1, "unsupported_ext"),
                FIXED_DATE_TIME
            )
        ).isInstanceOf(InvalidSessionException.class);
    }

    @Test
    @DisplayName("강의 커버 이미지 가로세로 크기가 너무 작으면 예외가 발생한다")
    void new_fail_for_min_image_size() {
        assertThatThrownBy(
            () ->  new Session(
                FIXED_DATE_TIME.plusDays(1), FIXED_DATE_TIME,
                generateSampleImage(WIDTH_MIN_SIZE-1, HEIGHT_MIN_SIZE, ALLOWED_EXT.get(0)),
                FIXED_DATE_TIME
            )
        ).isInstanceOf(InvalidSessionException.class);
        assertThatThrownBy(
            () ->  new Session(
                FIXED_DATE_TIME.plusDays(1), FIXED_DATE_TIME,
                generateSampleImage(WIDTH_MIN_SIZE, HEIGHT_MIN_SIZE-1, ALLOWED_EXT.get(0)),
                FIXED_DATE_TIME
            )
        ).isInstanceOf(InvalidSessionException.class);
    }

    // TODO 실제로 파일이 저장되어서 영 좋지 않은 것 같음. 다른 방법 고안하기
    private static File generateSampleImage(int width, int height, String ext) throws IOException {
        File file = new File("sample." + ext);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        ImageIO.write(image, ext, file);
        return file;
    }
}
