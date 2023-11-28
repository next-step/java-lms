package nextstep.lms.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private Parser() {

    }
    public static List<String> fileNameAndExtensionParsing(String value) {
        String[] fileNameAndExtension = value.split("\\.");
        fileNameValidate(fileNameAndExtension);
        return new ArrayList<>(Arrays.asList(fileNameAndExtension));
    }

    private static void fileNameValidate(String[] fileNameAndExtension) {
        if (fileNameAndExtension.length != 2) {
            throw new IllegalArgumentException("파일명이 올바르지않습니다.");
        }
    }
}
