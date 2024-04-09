package nextstep.courses.domain;

import java.util.Objects;

public class SessionImageType {

    private static final String GIF = "gif";
    private static final String JPG = "jpg";
    private static final String JPEG = "jpeg";
    private static final String PNG = "png";
    private static final String SVG = "svg";
    private String type;

    public SessionImageType(String type) {
        checkSupportType(type);
        this.type = type;
    }

    private void checkSupportType(String type) {
        if (type.equals(JPG) || type.equals(JPEG) || type.equals(PNG) || type.equals(GIF) || type.equals(SVG)) {
            return;
        }
        throw new IllegalArgumentException("지원하지 않는 이미지 파일입니다.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof SessionImageType))
            return false;
        SessionImageType imageType = (SessionImageType) object;
        return Objects.equals(type, imageType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
