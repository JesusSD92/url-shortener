package com.jesussd92.url_shortener.url_shortener.service;

public interface UrlService {

    String createShortUrl(String originalUrl);
    String getOriginalUrl(String shortUrl);
}
