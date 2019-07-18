package base;

import java.io.IOException;
import java.util.Iterator;

public interface VFS {
    boolean isExist(String path);

    boolean isDirectory(String path);

    String getAbsolutePath(String file);

    byte[] getBytes(String file) throws IOException;

    String getUTF8Text(String file) throws IOException;

    Iterator<String> getIterator(String startDir);
}
