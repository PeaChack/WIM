package by.peachack.wim.controllers;

import by.peachack.wim.model.market.OptimizerReport;
import by.peachack.wim.services.market.DealsService;
import by.peachack.wim.services.market.ItemPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/deals")
@RequiredArgsConstructor
public class DealFinderController {
    private final DealsService dealsService;
    private final ItemPriceService itemPriceService;


    @GetMapping
    public OptimizerReport getDeals(Authentication authentication, @RequestParam(name = "trade_count") Integer tradeCount){
        String username = authentication.getName();
        return dealsService.findDeals(username, tradeCount);
    }

}
