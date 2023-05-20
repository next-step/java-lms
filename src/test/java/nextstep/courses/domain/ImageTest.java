package nextstep.courses.domain;

import nextstep.courses.exception.InvalidImageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImageTest {

    private static String INVALID_URI = "htttpps://edu.nextstep.camp/";
    private static String VALID_URI = "https://edu.nextstep.camp/";
    private static Long VALID_SIZE = Image.getMaxSize() - 1;
    private static Long INVALID_SIZE = Image.getMaxSize() + 1;
    private static String VALID_TYPE_STRING = "GIF";
    private static String INVALID_TYPE = "BMP";
    private Image validImage;

    @BeforeEach
    void setUp() throws URISyntaxException, InvalidImageException {
        validImage = Image.create("test", VALID_URI, VALID_SIZE, VALID_TYPE_STRING);
    }


    @DisplayName("유효하지 않은 이미지 URI일 경우 예외를 던진다.")
    @Test
    void create_with_invalid_uri_should_throw_exception() {
        assertThrows(InvalidImageException.class, () ->
                Image.create("test", INVALID_URI, 100L, VALID_TYPE_STRING)
        );
    }

    @DisplayName("이미지 크기가 최대 크기를 초과하는 경우 예외를 던진다.")
    @Test
    void create_with_exceeding_max_size_should_throw_exception() {
        assertThrows(InvalidImageException.class, () ->
                Image.create("test", VALID_URI, INVALID_SIZE, VALID_TYPE_STRING)
        );
    }

    @DisplayName("이미지가 정상적으로 생성된다.")
    @Test
    void create_with_valid_data_should_create_image() {
        assertNotNull(validImage);
    }

    @DisplayName("이미지의 이름이 올바로 설정된다.")
    @Test
    void create_with_valid_data_should_set_image_name() {
        assertThat(validImage.getName()).isEqualTo("test");
    }

    @DisplayName("이미지의 URI가 올바로 설정된다.")
    @Test
    void create_with_valid_data_should_set_image_uri() {
        assertThat(validImage.getUri()).isEqualTo(URI.create(VALID_URI));
    }

    @DisplayName("이미지의 크기가 올바로 설정된다.")
    @Test
    void create_with_valid_data_should_set_image_size() {
        assertThat(validImage.getSize()).isEqualTo(VALID_SIZE);
    }

    @DisplayName("이미지의 형식이 올바로 설정된다.")
    @Test
    void create_with_valid_data_should_set_image_type() {
        assertThat(validImage.getType().toString()).isEqualTo(VALID_TYPE_STRING);
    }

}