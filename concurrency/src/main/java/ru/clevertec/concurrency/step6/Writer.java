package ru.clevertec.concurrency.step6;

public class Writer {
    private final StringBuffer stringBuilder;

    public Writer(StringBuffer stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public void write(String message) {
        stringBuilder.append(message)
                .append(":")
                .append(message)
                .append(":")
                .append(message)
                .append("\n");
    }
}
