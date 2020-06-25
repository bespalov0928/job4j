package ru.job4j.wget.filedownloader;

import ru.job4j.wget.printer.Printer;
import ru.job4j.wget.progressbar.ConsoleProgressBarThread;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

public class HttpFileDownloader implements FileDownloader, DownloadStatus {
    private static final String CONNECTION_FAILED = "Connection failed. Response code is: %s.";
    private static final String STARTING_DOWNLOAD = "Starting download file %s from %s.%s";
    private static final long TIME_LIMIT_MILLIS = 1000L;

    private static final int BUFFER_SIZE = 4096;
    private final String url;
    private final int maxBytesPerTimeLimit;
    private final Printer printer;

    private int fileSize;
    private volatile int downloadedSize;

    public HttpFileDownloader(String url, int maxBytesPerTimeLimit, Printer printer) {
        this.url = url;
        this.maxBytesPerTimeLimit = maxBytesPerTimeLimit;
        this.printer = printer;
    }

    @Override
    public void download() {
        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                printer.print(format(CONNECTION_FAILED, conn.getResponseCode()));
                return;
            }

            fileSize = conn.getContentLength();
            printer.print(format(STARTING_DOWNLOAD, getFileNameFromUrl(), this.url, lineSeparator()));

            try (InputStream inputStream = conn.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(getFileNameFromUrl())) {
                int bytesRead;
                int bytesPerTimeLimit = 0;
                byte[] buffer = new byte[BUFFER_SIZE];
                Thread progressBar = new ConsoleProgressBarThread(this, printer);
                progressBar.start();
                long startTime = System.currentTimeMillis();
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    bytesPerTimeLimit += bytesRead;
                    synchronized (this) {
                        downloadedSize += bytesRead;
                    }
                    outputStream.write(buffer, 0, bytesRead);
                    if (bytesPerTimeLimit >= maxBytesPerTimeLimit
                            && ((System.currentTimeMillis() - startTime) < TIME_LIMIT_MILLIS)) {
                        Thread.sleep(TIME_LIMIT_MILLIS - (System.currentTimeMillis() - startTime));
                        bytesPerTimeLimit = 0;
                        startTime = System.currentTimeMillis();
                    }
                }
                progressBar.interrupt();
                progressBar.join();
                printer.print(format("File %s downloaded.", getFileNameFromUrl()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                conn.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        download();
    }

    private String getFileNameFromUrl() {
        return this.url.substring(this.url.lastIndexOf('/') + 1);
    }

    @Override
    public int fileSize() {
        return fileSize;
    }

    @Override
    public synchronized int downloadedSize() {
        return downloadedSize;
    }
}
