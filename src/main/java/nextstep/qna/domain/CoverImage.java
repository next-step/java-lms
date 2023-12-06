package nextstep.qna.domain;

import java.util.Arrays;
import java.util.List;

public class CoverImage {
    private static List<String> POSSIBLE_IMAGE_TYPE = Arrays.asList("gif", "jpg", "JPEG", "png", "svg");
    private String url;
    private long sizeInBytes;
    private String type;
    private Pixel width;
    private Pixel height;

    private CoverImage(String url, long sizeInBytes, String type, Pixel width, Pixel height) {
        widthHeightCheck(width, height);
        ratioCheck(width, height);
        typeCheck(type);
        sizeCheck(sizeInBytes);
        this.url = url;
        this.sizeInBytes = sizeInBytes;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    private void widthHeightCheck(Pixel width, Pixel height) {
        if (width.getSize() > 300) {
            throw new IllegalArgumentException("너비는 300보다 클 수 없습니다. width:: " + width.getSize());
        }

        if (height.getSize() > 200) {
            throw new IllegalArgumentException("높이는 200보다 클 수 없습니다. height:: " + height.getSize());
        }
    }

    public static CoverImage of(String url, long sizeInBytes, String type, Pixel width, Pixel height) {
        return new CoverImage(url, sizeInBytes, type, width, height);
    }

    private void ratioCheck(Pixel width, Pixel height) {
        if ((width.getSize() * 2) != (height.getSize() * 3)) {
            throw new IllegalArgumentException("비율이 맞지 않습니다. width:: " + width.getSize() + ", height:: " + height.getSize());
        }
    }

    private void typeCheck(String type) {
        if (!POSSIBLE_IMAGE_TYPE.contains(type)) {
            throw new IllegalArgumentException("이미지 타입이 맞지 않습니다. type:: " + type);
        }
    }

    private void sizeCheck(long sizeInBytes) {
        // TODO check size
        if (sizeInBytes > 1024 * 1024) {
            throw new IllegalArgumentException("이미지 사이즈가 맞지 않습니다. sizeInBytes:: " + sizeInBytes);
        }
    }

    public String getUrl() {
        return url;
    }
}
