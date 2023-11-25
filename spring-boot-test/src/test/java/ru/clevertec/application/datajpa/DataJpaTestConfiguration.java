package ru.clevertec.application.datajpa;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.clevertec.application.mapper.OfferMapper;
import ru.clevertec.application.repository.OfferRepository;
import ru.clevertec.application.service.OfferService;

@TestConfiguration
@ComponentScan(basePackages = {"ru.clevertec.application.mapper"})
public class DataJpaTestConfiguration {

    @Bean
    public OfferService offerService(OfferRepository offerRepository, OfferMapper offerMapper) {
        return new OfferService(offerRepository, offerMapper);
    }
}
