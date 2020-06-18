package ru.job4j.wget.filedownloader;

import java.io.File;

public interface FileDownloader extends Runnable {
    void download();
}
