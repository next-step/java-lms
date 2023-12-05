package nextstep.lms.domain;

public class FileMetadata {
    private static final long MAX_FILE_MB_SIZE = 1024 * 1024;

    private final long fileVolume;
    private final FileSize fileSize;

    public FileMetadata(Long fileVolume, FileSize fileSize) {
        this.fileVolume = fileVolumeChecking(fileVolume);
        this.fileSize = fileSize;
    }

    private long fileVolumeChecking(long volume) {
        if (volume <= MAX_FILE_MB_SIZE) {
            return volume;
        }
        throw new IllegalArgumentException("파일이 너무 큽니다.");
    }

    public long getFileVolume() {
        return fileVolume;
    }

    public int getWidth() {
        return fileSize.getWidth();
    }

    public int getHeight() {
        return fileSize.getHeight();
    }
}
