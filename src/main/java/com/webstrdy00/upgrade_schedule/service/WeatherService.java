package com.webstrdy00.upgrade_schedule.service;

import com.webstrdy00.upgrade_schedule.dto.scheduleDto.WeatherRequestDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;
    private final String WEATHER_API_URL = "https://f-api.github.io/f-api/weather.json";

    public WeatherService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String getWeatherInfo(LocalDate date){
        // RestTemplate를 사용하여 날씨 API 호출
        WeatherRequestDto[] weatherRequestDtoList = restTemplate.getForObject(WEATHER_API_URL, WeatherRequestDto[].class);
        List<WeatherRequestDto> weatherList = Arrays.asList(weatherRequestDtoList);

        // 날짜 형식 변환 (LocalDate -> "MM-DD")
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MM-dd"));

        // 해당 날짜의 날짜 정보 찾기
        Optional<WeatherRequestDto> weatherInfo = weatherList.stream()
                .filter(w -> w.getDate().equals(formattedDate))
                .findFirst();

        return weatherInfo.map(WeatherRequestDto::getWeather).orElse("날씨 정보 없음");
    }
}
