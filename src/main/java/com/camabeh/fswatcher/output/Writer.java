package com.camabeh.fswatcher.output;

import com.camabeh.fswatcher.Event;

import java.io.BufferedOutputStream;
import java.io.PrintStream;

public abstract class Writer {

    protected PrintStream out;

    enum Format {
        CSV,
        PRETTY,
        JSON,
        XML
    }

    public Writer(PrintStream out) {
        this.out = out;
    }

    public Writer() {
        out = new PrintStream(new BufferedOutputStream(System.out));
    }

    public abstract void write(Event event);

    public abstract void close();

    public static Format parse(String formatType) {
        switch (formatType) {
            case "csv":
                return Format.CSV;
            case "json":
                return Format.JSON;
            case "xml":
                return Format.XML;
            // Pretty printer is default
            case "pretty":
            default:
                return Format.PRETTY;
        }
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
