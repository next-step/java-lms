package nextstep.lms.domain;

public class CoverImage {
    private final FileNameStructure fileNameStructure;
    private final FileMetadata fileMetadata;

    public CoverImage(FileNameStructure fileNameStructure, FileMetadata fileMetadata) {
        this.fileNameStructure = fileNameStructure;
        this.fileMetadata = fileMetadata;
    }
}
