package nextstep.lms.domain;

public class Parser {
    private Parser() {

    }

    public static void fileNameFormatChecking(String value) {
        String[] fileNameAndExtension = value.split("\\.");
        fileNameValidate(fileNameAndExtension);
    }

    private static void fileNameValidate(String[] fileNameAndExtension) {
        if (fileNameAndExtension.length != 2) {
            throw new IllegalArgumentException("파일명이 올바르지않습니다.");
        }
    }
}
