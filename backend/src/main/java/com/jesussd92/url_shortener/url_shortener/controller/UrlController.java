package com.jesussd92.url_shortener.url_shortener.controller;

import com.jesussd92.url_shortener.url_shortener.dto.UrlDto;
import com.jesussd92.url_shortener.url_shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins= "*")
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlDto request) {
        String originalUrl = request.originalUrl();
        String shortUrl = urlService.createShortUrl(originalUrl);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView getOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        return new RedirectView(originalUrl);
    }
}
