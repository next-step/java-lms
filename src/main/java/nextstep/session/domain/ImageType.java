package nextstep.session.domain;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg"),
    ;
    private String info;

    ImageType(String info) {
        this.info = info;
    }

    public static ImageType extractByURL(String imageURL) {
        Pattern pattern = Pattern.compile("(.+?)\\.([^.]+)$");
        Matcher matcher = pattern.matcher(imageURL);

        if (matcher.find()) {
            String imageType = matcher.group(2);
            ImageType[] values = ImageType.values();
            return Arrays.stream(values)
                    .filter(value -> value.info.equals(imageType))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 파일 형식입니다."));
        }
        throw new IllegalArgumentException("유효하지 않은 파일 형식입니다.");
    }
}
