package com.citi.training;

import com.citi.training.entities.BollingerBands;
import com.citi.training.entities.Strategy;
import com.citi.training.entities.TwoMovingAverages;
import com.citi.training.misc.Trend;
import com.citi.training.repositories.StrategyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class StrategyRepositoryTest {


    @Autowired
    private StrategyRepository strategyeRepository;

    @Test
    public void whenFindByName_thenReturnTwoMovingAvgsStrat() {
        // given
        Strategy strategyTMA = new TwoMovingAverages("goog", 500, "loss", 0.2, 2, 15, "strat1");
        strategyeRepository.save(strategyTMA);

        // when
        Strategy found = strategyeRepository.findById(strategyTMA.getId());

        // then
        assertThat(found.getId())
                .isEqualTo(strategyTMA.getId());

        //check constructor set values
        assertThat(found.getExitRule()).isEqualTo(strategyTMA.getExitRule());

        //check defaults
        assertThat(found.getExit()).isEqualTo(strategyTMA.getExit());
        assertThat(found.getExit()).isEqualTo(false);
        assertThat(((TwoMovingAverages)found).getCurrentTrend()).isEqualByComparingTo(Trend.FLAT);

        //check type
        assertThat(found).isExactlyInstanceOf(TwoMovingAverages.class);
        assertThat(found).isNotExactlyInstanceOf(BollingerBands.class);
        assertThat(((TwoMovingAverages)found).getLongAverageSeconds()).isEqualTo(((TwoMovingAverages) strategyTMA).getLongAverageSeconds());
        assertThat(((TwoMovingAverages)found).getShortAverageSeconds()).isEqualTo(((TwoMovingAverages) strategyTMA).getShortAverageSeconds());

    }

    @Test
    public void whenFindByName_thenReturnBollingerBandsStrat() {
        // given
        Strategy strategyBB = new BollingerBands("goog", 500, "loss", 0.2, 2, 1.5, "strat1");
        strategyeRepository.save(strategyBB);

        // when
        Strategy found = strategyeRepository.findById(strategyBB.getId());

        // then
        assertThat(found.getId())
                .isEqualTo(strategyBB.getId());

        //check defaults
        assertThat(found.getExit()).isEqualTo(strategyBB.getExit());
        assertThat(found.getExit()).isEqualTo(false);
        assertThat(((BollingerBands)found).getCurrentTrend()).isEqualByComparingTo(Trend.FLAT);
        assertThat(found.getFirstTrade()).isEqualTo(true);


        //check type
        assertThat(found).isExactlyInstanceOf(BollingerBands.class);
        assertThat(found).isNotExactlyInstanceOf(TwoMovingAverages.class);
        assertThat(((BollingerBands)found).getStandardDeviation()).isEqualTo(((BollingerBands) strategyBB).getStandardDeviation());
        assertThat(((BollingerBands)found).getAvgSeconds()).isEqualTo(((BollingerBands) strategyBB).getAvgSeconds());

    }

}