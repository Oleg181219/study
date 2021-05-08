package diplom.blog.service;

import diplom.blog.configs.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        init();
    }


    @Override
    public void init() {
        try {
            if (Files.notExists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }


    @Override
    public String store(MultipartFile file) {
        final var FILE_PATTERN = Pattern.compile("^(.*)(.)(png|jpe?g)$");

        Path fullFilePath;
        var filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file: " + filename);
            }

            if (filename.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory: "
                                + filename);
            }

            if (!FILE_PATTERN.matcher(filename).matches()) {
                throw new StorageException("Can store PNG & JPE?G images only: " + filename);
            }

            try (var inputStream = file.getInputStream()) {
                final var randomSubPath = Paths.get(getRandomPath());
                final var fullUploadPath = this.rootLocation.resolve(randomSubPath);
                fullFilePath = fullUploadPath.resolve(filename);

                Files.createDirectories(fullUploadPath);

                Files.copy(inputStream, fullFilePath,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file: " + filename, e);
        }

        System.out.println("fullFilePath.toString()  -- " + fullFilePath.toString());
        var response = this.rootLocation.relativize(fullFilePath).toString().replace('\\', '/');
        System.out.println("response  --  " + response);
        return response;

    }


    @Override
    public Path load(String filename) {
        Path file = Paths.get(filename.startsWith("/") ? "/" : "")
                .resolve(rootLocation)
                .relativize(Paths.get(filename));
        return rootLocation.resolve(file);
    }


    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public Path getRootLocation() {
        return rootLocation;
    }


    @Override
    public boolean delete(String filename) {
        boolean result = false;

        try {
            result = Files.deleteIfExists(load(filename));
        } catch (NoSuchFileException e) {
            throw new StorageException("No such file exists: " + filename, e);
        } catch (IOException e) {
            throw new StorageException("Invalid permissions for file: " + filename, e);
        }

        return result;
    }

    private static String getRandomPath() {
        StringBuilder sb = new StringBuilder();

        for (int iteration = 0; iteration < 3; iteration++) {
            for (int ch = 0; ch < 2; ch++) {
                sb.append((char) (new Random().nextInt('z' - 'a') + 'a'));
            }
            sb.append("/");
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static class StorageException extends RuntimeException {

        public StorageException(String message) {
            super(message);
        }

        public StorageException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
