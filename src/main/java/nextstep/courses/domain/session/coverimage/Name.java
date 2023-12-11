package nextstep.courses.domain.session.coverimage;

import nextstep.courses.exception.ImageFileInfoException;

import java.util.Optional;

import static nextstep.courses.domain.session.coverimage.Extension.*;

public class Name {

    public static final String EXTENSION_DELIMITER = ".";
    private String fileName;
    private Extension extension;

    public Name(String fileName) throws ImageFileInfoException {
        if (isNone(fileName)) {
            throw new ImageFileInfoException("파일 이름이 존재하지 않습니다.");
        }

        this.fileName = fileName;
        this.extension = extractExtension(fileName);
    }

    private boolean isNone(String fileName) {
        return fileName == null || fileName.isBlank();
    }

    private Extension extractExtension(String fileName) throws ImageFileInfoException {
        String stringExt = extractStringExt(fileName);

        return extension(stringExt);
    }

    private String extractStringExt(String fileName) throws ImageFileInfoException {
        return Optional.ofNullable(fileName)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(fileName.lastIndexOf(EXTENSION_DELIMITER) + 1))
            .orElseThrow(() -> new ImageFileInfoException("확장자가 존재하지 않습니다"));
    }

    public String getFileName() {
        return this.fileName;
    }
}
