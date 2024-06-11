package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.CurrencyService;

@Controller
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // Return the name of the HTML template to be rendered
    }

    @PostMapping("/convert")
    public String convert(@RequestParam String fromCurrency, 
                          @RequestParam String toCurrency, 
                          @RequestParam double amount, 
                          Model model) {
        try {
            double result = currencyService.convert(fromCurrency, toCurrency, amount);
            model.addAttribute("result", result);
        } catch (Exception e) {
            model.addAttribute("error", "Error converting currency: " + e.getMessage());
        }
        // Return the same page after processing the form
        return "index";
    }
}
