package ru.job4j.wget.progressbar;

import ru.job4j.wget.filedownloader.DownloadStatus;
import ru.job4j.wget.printer.Printer;

import static java.lang.String.format;

public class ConsoleProgressBarThread extends Thread implements ProgressBar {
    private final static String PROGRESS_TMPL = "Downloaded %d kb from %d kb \r";
    private final DownloadStatus status;
    private final Printer printer;

    public ConsoleProgressBarThread(DownloadStatus status, Printer printer) {
        this.status = status;
        this.printer = printer;
    }

    @Override
    public void run() {
        render();
    }

    @Override
    public void render() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                printer.print(format(PROGRESS_TMPL, status.downloadedSize() / 1000, status.fileSize() / 1000));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        printer.print(System.lineSeparator());
    }
}
