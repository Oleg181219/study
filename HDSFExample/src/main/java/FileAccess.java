

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FileAccess {

    private static final String NO_CONTENT = "no content";

    private FileSystem fileSystem;

    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath
     */
    public FileAccess(String rootPath) {
        initFileSystem(rootPath);
    }

    private void initFileSystem(String rootPath) {
        try {
            Configuration configuration = new Configuration();
            configuration.set("dfs.client.use.datanode.hostname", "true");
            configuration.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
            System.setProperty("HADOOP_USER_NAME", "root");
            fileSystem = FileSystem.get(new URI(rootPath), configuration);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(String path) throws IOException {
        Path file = new Path(fileSystem.getUri() + "/" + path);
        fileSystem.create(file);
        System.out.println("Файл [" + path + "] создан.");
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) throws IOException {
        Path file = new Path(fileSystem.getUri() + "/" + path);
        FSDataOutputStream fileOutputStream = null;

        if (fileSystem.exists(file)) {
            fileOutputStream = fileSystem.append(file);
        } else {
            fileOutputStream = fileSystem.create(file);
        }
        fileOutputStream.writeBytes(content);
        fileOutputStream.close();
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws IOException {
        Path file = new Path(fileSystem.getUri() + "/" + path);
        if (!fileSystem.exists(file) | fileSystem.isDirectory(file)) {
            return NO_CONTENT;
        }
        FSDataInputStream fs = fileSystem.open(file);
        String result = IOUtils.toString(fs);
        if (result.isEmpty()) {
            return NO_CONTENT;
        }
        return result;
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws IOException {
        Path file = new Path(fileSystem.getUri() + "/" + path);
        if (fileSystem.exists(file)) {
            fileSystem.delete(file, true);
            System.out.println("Файл [" + path + "] удален.");
        }
        System.out.println("Файл [" + path + "] не найден.");
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public FileStatus isDirectory(String path) throws IOException {
        //вариант 1. Метод не deprecated
        return fileSystem.getFileStatus(new Path(fileSystem.getUri() + "/" + path));
        //вариант 2 более приличный вывод.
//        return fileSystem.isDirectory(new Path(fileSystem.getUri() + "/" + path));
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) throws IOException {
        Path directory = new Path(fileSystem.getUri() + "/" + path);
        List<String> result = new ArrayList<>();
        if (fileSystem.isDirectory(directory)) {

            RemoteIterator<LocatedFileStatus> remoteIterator = fileSystem.listFiles(directory, true);

            while (remoteIterator.hasNext()) {
                LocatedFileStatus locatedFileStatus = remoteIterator.next();
                result.add(locatedFileStatus.getPath().toUri().getPath());
            }
            return result;
        }
        return new ArrayList<>();
    }
}
