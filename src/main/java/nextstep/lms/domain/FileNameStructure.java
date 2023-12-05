package nextstep.lms.domain;

import nextstep.lms.enums.ExtensionEnum;

public class FileNameStructure {
    private final String name;
    private final String extension;

    public FileNameStructure(String name) {
        this.name = Parser.getBaseName(name);
        this.extension = extensionChecking(Parser.getExtension(name));
    }

    private String extensionChecking(String value) {
        if (ExtensionEnum.contains(value)) {
            return value;
        }
        throw new IllegalArgumentException("지원하지 않는 확장자입니다.");
    }
}
