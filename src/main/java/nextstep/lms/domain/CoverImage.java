package nextstep.lms.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoverImage {
    private static final double MAX_FILE_MB_SIZE = 1.0;
    private static final List<String> EXTENSIONS = new ArrayList<>(Arrays.asList("gif","jpg","jpeg","png","svg"));
    private static final int MIN_WIDTH_PX_SIZE = 300;
    private static final int MIN_HEIGHT_PX_SIZE = 200;
    private static final double WIDTH_HEIGHT_RATIO = MIN_WIDTH_PX_SIZE / (double) MIN_HEIGHT_PX_SIZE;
    private String name;
    private String type;
    private double fileSize;
    private int width;
    private int height;

    public CoverImage(String name, double fileSize, int width, int height) {
        List<String> fileInformation = Parser.fileNameAndExtensionParsing(name);
        this.name = fileInformation.get(0);
        this.type = extensionChecking(fileInformation.get(1));
        this.fileSize = fileSizeChecking(fileSize);
        this.width = widthChecking(width);
        this.height = heightChecking(height);
        ratioCheck(width, height);
    }

    private String extensionChecking(String value) {
        if (EXTENSIONS.contains(value)) {
            return value;
        }
        throw new IllegalArgumentException("지원하지 않는 확장자입니다.");
    }

    private double fileSizeChecking(double value) {
        if (value <= MAX_FILE_MB_SIZE) {
            return value;
        }
        throw new IllegalArgumentException("파일이 너무 큽니다.");
    }

    private int widthChecking(int value) {
        if (value >= MIN_WIDTH_PX_SIZE) {
            return value;
        }
        throw new IllegalArgumentException("이미지 너비가 너무 작습니다.");
    }

    private int heightChecking(int value) {
        if (value >= MIN_HEIGHT_PX_SIZE) {
            return value;
        }
        throw new IllegalArgumentException("이미지 높이가 너무 작습니다.");
    }

    private void ratioCheck(int width, int height) {
        if ((width / (double) height) != WIDTH_HEIGHT_RATIO) {
            throw new IllegalArgumentException("이미지의 비율이 맞지 않습니다.");
        }
    }
}
