package nextstep.lms.domain;

import nextstep.lms.enums.ExtensionEnum;

public class FileNameStructure {
    private final String name;
    private final String extension;

    public FileNameStructure(String name, String extension) {
        this.name = name;
        this.extension = extensionChecking(extension);
    }

    private String extensionChecking(String value) {
        if (ExtensionEnum.contains(value)) {
            return value;
        }
        throw new IllegalArgumentException("지원하지 않는 확장자입니다.");
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }
}
