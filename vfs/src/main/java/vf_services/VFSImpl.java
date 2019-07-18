package vf_services;

import base.VFS;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class VFSImpl implements VFS {
    private String root;

    public VFSImpl(String root) {
        this.root = root;
    }

    @Override
    public boolean isExist(String path) {
        File file = new File(root + path);
        return file.exists();
    }

    @Override
    public boolean isDirectory(String path) {
        File file = new File(root + path);
        return file.isDirectory();
    }

    @Override
    public String getAbsolutePath(String file) {
        return new File(root + file).getAbsolutePath();
    }

    @Override
    public byte[] getBytes(String file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(root + file, "r");
        byte[] bytes = new byte[(int) raf.length()];
        raf.write(bytes);
        return bytes;
    }

    @Override
    public String getUTF8Text(String file) throws IOException {
        InputStream inputStream = new FileInputStream(root + file);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

    @Override
    public Iterator<String> getIterator(String startDir) {
        return new FileIterator(startDir);
    }

    private class FileIterator implements Iterator<String> {
        private Queue<File> files = new LinkedList<>();

        public FileIterator(String path) {
            this.files.add(new File(root + path));
        }

        @Override
        public boolean hasNext() {
            return !files.isEmpty();
        }

        @Override
        public String next() {
            File file = files.peek();
            if (file.isDirectory()) {
                for (File subFile : file.listFiles()) {
                    files.add(subFile);
                }
            }
            return files.poll().getAbsolutePath();
        }
    }
}
