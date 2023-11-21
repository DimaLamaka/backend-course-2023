package ru.clevertec.springbootmetricstarter.service;

import java.util.Map;

public interface MetricSender {

    void sendMetrics(Map<String, String> metrics);
}
