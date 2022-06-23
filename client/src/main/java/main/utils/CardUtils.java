package main.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public class CardUtils {
    private static String tempFolder = "/Users/alenaryzova/Documents/cards/cards/client/src/main/resources/temp/";

    public static FileSystemResource createTempFileSystemResource(MultipartFile multipartFile) throws IOException {
        File fileQuestion = new File(tempFolder + multipartFile.getName() + ".png");
        multipartFile.transferTo(fileQuestion);
        return new FileSystemResource(fileQuestion);
    }
}
