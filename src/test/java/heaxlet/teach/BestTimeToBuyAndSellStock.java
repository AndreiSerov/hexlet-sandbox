package heaxlet.teach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BestTimeToBuyAndSellStock {


    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day
     * in the future to sell that stock.
     *
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int best = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            best = Math.max(best, price - minPrice);
        }

        return best;
    }


    @Test
    void test1() {
        assertEquals(
                5,
                maxProfit(new int[]{7, 1, 5, 3, 6, 4})
        );
    }

    @Test
    void test2() {
        assertEquals(
                0,
                maxProfit(new int[]{7, 6, 4, 3, 1})
        );
    }
}
