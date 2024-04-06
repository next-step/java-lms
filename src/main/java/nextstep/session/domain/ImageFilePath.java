package nextstep.session.domain;

import nextstep.session.type.ImageExtensionType;

import java.nio.file.Path;

public class ImageFilePath {

    private final String filePath;
    private final String fileName;
    private final ImageExtensionType extensionType;

    public ImageFilePath(String filePath, String fileName, String extensionType) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.extensionType = ImageExtensionType.of(extensionType);
    }

    public Path getFullFilePath() {
        return Path.of(filePath, getFullFileName());
    }

    private String getFullFileName() {
        return fileName + extensionType.getExtension();
    }
}
