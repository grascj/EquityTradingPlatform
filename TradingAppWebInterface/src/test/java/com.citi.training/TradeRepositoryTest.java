package com.citi.training;

import com.citi.training.entities.Order;
import com.citi.training.entities.Trade;
import com.citi.training.repositories.TradeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class TradeRepositoryTest {

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    public void addTrade_thenReturnByResult() {
        Order o = new Order("true", "25", "200", "goog", "XX123");
        Trade t = new Trade(o, "Filled", "123acd66fc", 28.6);
        tradeRepository.save(t);

        //get it back by result
        List<Trade> found  = tradeRepository.findByResult("Filled");
        assertThat(found.contains(t));

        Order o2 = new Order("true", "26", "200", "goog", "XX124");
        Trade t2 = new Trade(o2, "Filled", "123acd66fc", 30.6);
        tradeRepository.save(t2);

    }

    @Test
    public void addTrade_thenReturnById() {
        Order o = new Order("true", "25", "200", "goog", "XX123");
        Trade t = new Trade(o, "Filled", "123acd66fc", 28.6);
        tradeRepository.save(t);

        //get it back by result
        List<Trade> found  = tradeRepository.findByStrategyId("123acd66fc");
        assertThat(found.contains(t));


    }
}
