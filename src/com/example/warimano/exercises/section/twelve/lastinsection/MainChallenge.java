package com.example.warimano.exercises.section.twelve.lastinsection;

import java.util.Map;

public class MainChallenge {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem tmp = new StockItem("bread", 0.86, 100);
        stockList.addStock(tmp);

        tmp = new StockItem("cake", 1.10, 7);
        stockList.addStock(tmp);

        tmp = new StockItem("car", 12.50, 2);
        stockList.addStock(tmp);

        tmp = new StockItem("chair", 62.0, 10);
        stockList.addStock(tmp);

        tmp = new StockItem("cup", 0.50, 200);
        stockList.addStock(tmp);
        tmp = new StockItem("cup", 0.45, 7);
        stockList.addStock(tmp);

        tmp = new StockItem("door", 72.95, 4);
        stockList.addStock(tmp);

        tmp = new StockItem("juice", 2.50, 36);
        stockList.addStock(tmp);

        tmp = new StockItem("phone", 96.99, 35);
        stockList.addStock(tmp);

        tmp = new StockItem("towel", 2.40, 80);
        stockList.addStock(tmp);

        tmp = new StockItem("vase", 8.76, 40);
        stockList.addStock(tmp);

        System.out.println(stockList);

        for (String s : stockList.Items().keySet()) {
            System.out.println(s);
        }

        Basket timsBasket = new Basket("Tim");

        sellItem(timsBasket, "car", 1);
        System.out.println(timsBasket);

        sellItem(timsBasket, "car", 1);
        System.out.println(timsBasket);

        if (sellItem(timsBasket, "Car", 1) != 1) {
            System.out.println("no more cars in stock");
        }
        sellItem(timsBasket, "spanner", 5);

        sellItem(timsBasket, "juice", 4);
        sellItem(timsBasket, "cup", 12);
        sellItem(timsBasket, "bread", 1);

        Basket basket = new Basket("customer");
        sellItem(basket, "cup", 100);
        sellItem(basket, "juice", 5);
        sellItem(basket, "cup", 1);
        removeItem(basket, "cup", 1);
        System.out.println(basket);

        removeItem(timsBasket, "car", 1);
        removeItem(timsBasket, "cup", 9);
        removeItem(timsBasket, "car", 1);
        System.out.println("cars removed " + removeItem(timsBasket, "car", 1));
        System.out.println(timsBasket);

        removeItem(timsBasket, "bread", 1);
        removeItem(timsBasket, "cup", 3);
        removeItem(timsBasket, "juice", 4);
        removeItem(timsBasket, "cup", 3);
        System.out.println(timsBasket);

        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(basket);
        System.out.println(stockList);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stockList);

        StockItem car = stockList.Items().get("car");
        if (car != null) car.adjustStock(20000);
        if (car != null) stockList.get("car").adjustStock(-10000);
        System.out.println(stockList);

        checkOut(timsBasket);
        System.out.println(timsBasket);
    }

    public static int sellItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell item " + item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell item " + item);
            return 0;
        }
        if (basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unReserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkOut(Basket basket) {
        for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}
