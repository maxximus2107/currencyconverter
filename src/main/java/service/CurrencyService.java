package service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.CurrencyConversion;

@Service
public class CurrencyService {

    private final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public double convert(String fromCurrency, String toCurrency, double amount) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + fromCurrency;
        CurrencyConversion response = (CurrencyConversion) restTemplate.getForObject(url, CurrencyConversion.class);
        assert response != null;
        double rate = response.getRates().get(toCurrency);
        return amount * rate;
    }
}
