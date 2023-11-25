package ru.clevertec.application.component;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.clevertec.application.mapper.OfferMapper;
import ru.clevertec.application.repository.OfferRepository;
import ru.clevertec.application.service.OfferService;

@TestConfiguration
@ComponentScan(basePackageClasses = {OfferMapper.class})
public class ComponentTestConfiguration {

    @Bean
    public OfferRepository offerRepository() {
        return Mockito.mock(OfferRepository.class);
    }

    @Bean
    public OfferService offerService(OfferRepository offerRepository, OfferMapper offerMapper) {
        return new OfferService(offerRepository, offerMapper);
    }
}
