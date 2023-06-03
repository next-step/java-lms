package nextstep.courses.domain;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgFile {
    private static final String TYPE_1 = "이미지는 필수 값입니다. 입력을 확인해 주세요.";
    private static final String TYPE_2 = "이미지 이미지 파일 형식이 아닙니다.";
    private static final Pattern DELIMITER = Pattern.compile(".*\\.(jpeg|jpg|png|gif)$");
    private String imgFile;

    public ImgFile(String imgFile) {
        urlValidationCheck(imgFile);
        this.imgFile = imgFile;
    }

    void urlValidationCheck(String url) {
        if (Objects.isNull(url)) {
            throw new IllegalArgumentException(TYPE_1);
        }

        if (!isImageUrlPattern(url)) {
            throw new IllegalArgumentException(TYPE_2);
        }

    }

    public static boolean isImageUrlPattern(String imageUrl) {
        Matcher matcher = DELIMITER.matcher(imageUrl);
        return matcher.matches();
    }
}
