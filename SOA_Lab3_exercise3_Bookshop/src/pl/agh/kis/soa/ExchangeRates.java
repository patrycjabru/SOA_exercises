package pl.agh.kis.soa;

import java.util.HashMap;

public class ExchangeRates {
    private static HashMap<String, Double> currencies;

    public static HashMap<String, Double> getCurrencies() {
        return currencies;
    }

    public static double getExchangeRate(String code) {
        if (currencies==null)
            init();
        double value = currencies.get(code);
        return value;
    }

    private static void init() {
        currencies = new HashMap<>();
        currencies.put("PLN", 1.00);
        currencies.put("EUR", 4.29);
        currencies.put("USD", 3.80);
    }
}
