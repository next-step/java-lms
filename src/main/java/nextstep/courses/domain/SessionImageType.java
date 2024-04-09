package nextstep.courses.domain;

import java.util.Objects;

public class ImageType {

    private String type;

    public ImageType(String type) {
        checkSupportType(type);
        this.type = type;
    }

    private void checkSupportType(String type) {
        if (type.equals("jpg") || type.equals("jpeg") || type.equals("png") || type.equals("gif") || type.equals("svg")) {
            return;
        }
        throw new IllegalArgumentException("지원하지 않는 이미지 파일입니다.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof ImageType))
            return false;
        ImageType imageType = (ImageType) object;
        return Objects.equals(type, imageType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
