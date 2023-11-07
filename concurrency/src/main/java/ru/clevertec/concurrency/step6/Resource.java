package ru.clevertec.concurrency.step6;

import java.util.stream.IntStream;

public class Resource {
    private final StringBuilder firstResource;
    private final StringBuilder secondResource;

    public Resource(StringBuilder firstResource, StringBuilder secondResource) {
        this.firstResource = firstResource;
        this.secondResource = secondResource;
    }

    public synchronized void appendToFirstResource(String value) {
        synchronized (firstResource) {
            IntStream.range(0, 10)
                    .forEach(i -> {
                        String appendValue = i + ":" + value + " ";
                        System.out.println("Append to first resource: " + appendValue);
                        firstResource.append(appendValue);
                    });
            firstResource.append("\n");
        }
    }

    public synchronized void appendToSecondResource(String value) {
        synchronized (secondResource) {
            IntStream.range(0, 10)
                    .forEach(i -> {
                        String appendValue = i + ":" + value + " ";
                        System.out.println("Append to second resource: " + appendValue);
                        secondResource.append(appendValue);
                    });
            secondResource.append("\n");
        }
    }
}
