package by.peachack.wim.controller;

import by.peachack.wim.model.market.OptimizerReport;
import by.peachack.wim.service.market.DealService;
import by.peachack.wim.service.market.ItemPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;
    private final ItemPriceService itemPriceService;


    @GetMapping
    public OptimizerReport getDeals(Authentication authentication, @RequestParam(name = "trade_count") Integer tradeCount){
        String username = authentication.getName();
        return dealService.findDeals(username, tradeCount);
    }

}
