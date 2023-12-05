package nextstep.lms.domain;

public class Parser {
    public static final char EXTENSION_SEPARATOR = '.';
    public static final int NOT_FOUND = -1;

    private Parser() {

    }

    public static String getBaseName(final String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("파일명이 올바르지않습니다.");
        }
        return removeExtension(fileName);
    }

    public static String getExtension(final String fileName) {
        int index = indexOfExtension(fileName);
        return fileName.substring(index + 1);
    }

    private static String removeExtension(String fileName) {
        final int index = indexOfExtension(fileName);

        return fileName.substring(0, index);
    }

    private static int indexOfExtension(String fileName) {
        int index = fileName.lastIndexOf(EXTENSION_SEPARATOR);
        if (index == NOT_FOUND) {
            throw new IllegalArgumentException("확장자가 없습니다.");
        }
        return index;
    }
}
