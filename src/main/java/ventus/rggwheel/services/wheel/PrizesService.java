package ventus.rggwheel.services.wheel;

import ventus.rggwheel.model.PrizeEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PrizesService {
    List<PrizeEnum> currentPrizesPermutation;

    public PrizesService() {
        this.currentPrizesPermutation = Arrays.asList(PrizeEnum.values());
        Collections.shuffle(this.currentPrizesPermutation);
    }

    public void shufflePrizes(){
        Collections.shuffle(currentPrizesPermutation);
    }

    public PrizeEnum getPrize(int ordinal){
        return currentPrizesPermutation.get(ordinal);
    }

    public List<String> labels(){
        return currentPrizesPermutation.stream().map(PrizeEnum::getLabel).collect(Collectors.toList());
    }
}
