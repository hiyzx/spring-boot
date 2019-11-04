package org.zero.entertainment.util;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author yezhaoxing
 * @date 2019/7/17
 */
public class CollectionTest {

    public static void main(String[] args) {
        String num = "188";
        String[] split1 = num.split("-");


        Product p1 = new Product(1, 1);
        Product p2 = new Product(2, 2);
        Product p3 = new Product(2, 3);
        Product p4 = new Product(3, 2);
        List<Product> products1 = Arrays.asList(p1, p2, p3, p4);
        Map<Integer, Integer> map5 = new HashMap<>();
        for (Product product : products1) {
            map5.merge(product.getId(), product.getCount(), Integer::sum);
        }




        for (int i = 0; i < 5; i++) {
            if (i == 1) {
                break;
            }
            System.out.println(i);
        }

        List<List<Integer>> list = new ArrayList<>();
        List<Integer> ints1 = new ArrayList<>();
        ints1.add(1);
        ints1.add(11);
        ints1.add(111);
        List<Integer> ints2 = new ArrayList<>();
        ints2.add(2);
        ints2.add(22);
        ints2.add(222);
        list.add(ints1);
        list.add(ints2);
        List<Integer> collect2 = list.stream().flatMap(List::stream).collect(Collectors.toList());

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.set(Calendar.DAY_OF_MONTH, 1);
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);

        currentCalendar.set(Calendar.DAY_OF_MONTH, 2);

        String format = DateUtil.format(currentCalendar.getTime(), "yyyy-MM-dd HH:mm:ss");

        String hello = "hello";
        String[] split = hello.split("/");

        System.out.println(split[0]);
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        Product product1 = new Product(1, 1);
        Product product2 = new Product(2, 2);
        Product product3 = new Product(1, 3);
        List<Product> products = new ArrayList<>(3);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        Optional<Product> first = products.stream().filter(product -> product.getId() == 1).findFirst();
        Map<Integer, List<Product>> collect = products.stream().collect(Collectors.groupingBy(Product::getId));

        Map<Integer, Integer> map = new HashMap<>(3);
        products.forEach(p -> map.merge(p.getId(), p.getCount(), Integer::sum));
        System.out.println(123);

        Map<Integer, Object> map1 = new HashMap<>();
        map1.put(1, product1);
        map1.put(2, product2);
        System.out.println(map1.size());
        List<Object> list1 = new ArrayList<>(map1.values());
        List<Product> collect1 = list1.stream().map(l -> (Product)l).collect(Collectors.toList());
    }
}

@Data
@AllArgsConstructor
class Product {
    private Integer id;

    private Integer count;
}