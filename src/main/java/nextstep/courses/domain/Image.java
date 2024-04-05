package nextstep.courses.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Image {
    private final int size;
    private final int height;
    private final int width;
    private final String name;
    private final FileExtension type;

    private final int MAX_SIZE = 1_000_000;


    public Image(int size, int height, int width, String fileName) {
        if (isOverSize(size)) {
            throw new IllegalArgumentException("크기는 1MB 이하 여야합니다.");
        }

        if (!verifyHeight(height)) {
            throw new IllegalArgumentException("높이는 200픽셀 이상만 가능합니다.");
        }

        if (!verifyWidth(width)) {
            throw new IllegalArgumentException("너비는 300픽셀 이상만 가능합니다.");
        }

        if (!checkRatioThreeToTwo(width, height)) {
            throw new IllegalArgumentException("높이와 너비는 3:2 비율이여야 합니다. ");
        }

        this.size = size;
        this.height = height;
        this.width = width;
        this.name = "";
        this.type = convertType(fileName);
    }

    private boolean verifyWidth(int width) {
        return width >= 300;
    }

    private boolean verifyHeight(int height) {
        return height >= 200;
    }

    private FileExtension convertType(String fileName) {
        return FileExtension.from(fileName);
    }

    private String convertName(String fileName) {
        return "";
    }

    private static String[] fileSplit(String fileName) {
        return fileName.split("\\.");
    }

    private boolean isOverSize(int size) {
        return MAX_SIZE < size;
    }

    public static boolean checkRatioThreeToTwo(int width, int height) {
        if (width % 3 == 0 && height % 2 == 0) {
            if (width / 3 == height / 2) {
                return true;
            }
        }
        return false;
    }

    public enum FileExtension {
        GIF,
        JPG,
        JPEG,
        PNG,
        SVG,
        NONE;

        private static final Map<String, FileExtension> MAPPINGS;
        private static final int FILE_EXTENSION_INDEX = 1;

        static {
            MAPPINGS = Collections.unmodifiableMap(init());
        }

        private static Map<String, FileExtension> init() {
            Map<String, FileExtension> hashMap = new HashMap<>();
            Arrays.stream(FileExtension.values())
                    .forEach(item -> hashMap.put(item.name().toLowerCase(), item));
            return hashMap;
        }

        public static FileExtension from(String fileName) {
            if (isFileNameBlank(fileName)) {
                return NONE;
            }

            String[] stringSplit = fileSplit(fileName);
            if (isFileContent(stringSplit)) {
                return NONE;
            }

            return MAPPINGS.getOrDefault(stringSplit[FILE_EXTENSION_INDEX], NONE);
        }

        private static boolean isFileContent(String[] stringSplit) {
            return stringSplit.length != 2;
        }

        private static boolean isFileNameBlank(String fileName) {
            return fileName == null || fileName.isBlank();
        }
    }
}
