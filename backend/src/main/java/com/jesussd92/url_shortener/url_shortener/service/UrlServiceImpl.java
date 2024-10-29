package com.jesussd92.url_shortener.url_shortener.service;

import com.jesussd92.url_shortener.url_shortener.entity.Url;
import com.jesussd92.url_shortener.url_shortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 8;
    private final Random random = new Random();

    @Override
    public String createShortUrl(String originalUrl) {
        String shortUrl;

        do {
            shortUrl = generateShortUrl(originalUrl);
        }while(urlRepository.existsByShortUrl(shortUrl));

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);

        urlRepository.save(url);
        return baseUrl + "/" + shortUrl;
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        Optional<Url> urlOptional = urlRepository.findByShortUrl(shortUrl);
        return urlOptional.map(Url::getOriginalUrl).orElse(null);
    }

    private String generateShortUrl(String originalUrl) {
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            shortUrl.append(CHARACTERS.charAt(randomIndex));
        }
        return shortUrl.toString();
    }
}
