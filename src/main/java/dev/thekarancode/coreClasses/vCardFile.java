package dev.thekarancode.coreClasses;

import dev.thekarancode.customExceptions.UnsupportedCharacterException;
import dev.thekarancode.customExceptions.vCardNotAddedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class vCardFile {

    /*Default File Properties*/
    public static final String defaultFileName = "vCardFile";
    private static final String defaultFileExtension = ".vcf";
    private static final Path defaultFileDirectory = Paths.get(System.getProperty("user.home")).resolve("Downloads/vCardel Output");
    /*File Content*/
    public final ArrayList<vCard> vCardList = new ArrayList<>();
    /*File Properties*/
    private String fileName;
    private Path fileDirectory;
    private boolean timeStampEnabled;
    private Path filePath;


    /*Constructor*/
    public vCardFile() {
        this(defaultFileName, defaultFileDirectory, true);
    }

    public vCardFile(String fileName, String fileDirectory, boolean timeStampEnabled) {
        this(fileName, Paths.get(fileDirectory), timeStampEnabled);
    }

    public vCardFile(String fileName, Path fileDirectory, boolean timeStampEnabled) {
        this.fileName = (fileName != null) ? (timeStampEnabled ? fileName + getTimestamp() : fileName) : (timeStampEnabled ? defaultFileName + getTimestamp() : defaultFileName);
        this.fileDirectory = (fileDirectory != null) ? fileDirectory : defaultFileDirectory;
        this.timeStampEnabled = timeStampEnabled;
        this.filePath = this.fileDirectory.resolve(this.fileName);
    }

    /*Getters*/
    public String getFileName() {
        return fileName;
    }

    public String getFileDirectory() {
        return fileDirectory.toString();
    }

    public boolean isTimeStampEnabled() {
        return timeStampEnabled;
    }


    /*Setters*/
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileDirectory(Path fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public void setTimeStampEnabled(boolean timeStampEnabled) {
        this.timeStampEnabled = timeStampEnabled;
    }

    /*Primary Methods*/
    public void add_vCard(vCard vCard) {
        this.vCardList.add(vCard);
    }

    public void crete_vCardFile() throws IOException, UnsupportedCharacterException {
        if (vCardList.isEmpty()) {
            throw new vCardNotAddedException("There is no vCard added in the file.");
        }
        createFile();

        StringBuilder vCardFileContent = new StringBuilder();

        for (vCard vCard : vCardList) {
            vCardFileContent
                    .append(vCard.compose_vCard())
                    .append("\n\n");
        }

        byte[] vCardFileContentAsArray = vCardFileContent.toString().getBytes();

        Files.write(filePath, vCardFileContentAsArray, StandardOpenOption.WRITE);

    }

    public boolean deleteLast_vCard() {
        if (!vCardList.isEmpty()) {
            vCardList.removeLast();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteAll_vCard() {
        if (!vCardList.isEmpty()) {
            vCardList.clear();
            return true;
        } else {
            return false;
        }
    }


    /*Utility Methods*/
    private String getTimestamp() {
        String timeStamp = Instant.now().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyyMMdd@HHmmss"));
        return " {" + timeStamp + "}";
    }

    private void createFile() throws IOException {
        int attemptNum = 0;
        String originalFileName = fileName;

        Files.createDirectories(fileDirectory);

        do {
            attemptNum++;
            fileName = attemptNum > 1 ? originalFileName + " {" + (attemptNum - 1) + "}" : fileName;
            filePath = Paths.get(fileDirectory + "\\" + (fileName.isBlank() ? defaultFileName : fileName) + defaultFileExtension);

            System.out.println("Attempt number " + attemptNum + " to create file: " + filePath.toAbsolutePath() + ".");
        } while (Files.exists(filePath));

        Files.createFile(filePath);
    }


}
