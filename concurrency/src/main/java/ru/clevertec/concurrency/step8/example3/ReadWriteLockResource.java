package ru.clevertec.concurrency.step8.example3;


public class ReadWriteLockResource {
    private StringBuilder stringBuilder;

    public ReadWriteLockResource(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public void write(String value) {
        System.out.println(Thread.currentThread() + " write to stringBuilder");
        stringBuilder.append(value)
                .append(" ");
    }

    public String read() {
        return stringBuilder.toString();
    }
}
