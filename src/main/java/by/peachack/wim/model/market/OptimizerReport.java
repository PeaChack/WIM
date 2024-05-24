package by.peachack.wim.model.market;

import by.peachack.wim.model.inventory.PrimeSet;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class OptimizerReport {
    private Map<PrimeSet, Float> greedyMetrics;
    private Map<PrimeSet, Float> mediumMetrics;
    private Map<PrimeSet, Float> generousMetrics;

}
