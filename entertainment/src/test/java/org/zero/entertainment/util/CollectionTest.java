package org.zero.entertainment.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author yezhaoxing
 * @date 2019/7/17
 */
public class CollectionTest {

    public static void main(String[] args) {

        long l1 = DateUtil.betweenDay(DateUtil.parseDate("1994-01-28"), DateUtil.parseDate("2019-12-01"), true);


        List<User> lp = new ArrayList<>();
        lp.add(new User(2, new BigDecimal(10)));
        lp.add(new User(1, new BigDecimal(122.73)));
        BigDecimal integer1 = lp.stream().map(User::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(integer1);


        final int[] arr1 = new int[2];
        arr1[0] = 2;
        System.out.println(arr1);
        arr1[1] = 2;
        System.out.println(arr1);

        System.out.println(~(-1L << 5));

        Snowflake snowflake = new Snowflake(1,1);
        System.out.println(snowflake.nextId());


        Product product30;
        List<Product> product1s = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            product30 = new Product(i, i);
            product1s.add(product30);
        }
        product1s.add(new Product(1, 1));
        Map<Integer, Integer> collect3 = product1s.stream().collect(Collectors.toMap(Product::getId, Product::getCount, (a, b) -> a));

        String code = "C165";
        System.out.println(code + String.format("%03d" , 1));
        System.out.println(code + String.format("%03d" , 11));
        System.out.println(code + String.format("%03d" , 111));
        System.out.println(code + String.format("%03d" , 1111));


        List<Integer> list123 = new ArrayList<>();
        list123.add(1);
        list123.add(2);
        list123.add(3);
        list123.add(3);
        list123.add(4);
        Iterator<Integer> iterator = list123.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if(next == 3){
                iterator.remove();
            }
        }

        for (Integer integer : list123) {
            System.out.println(integer);
        }


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
@NoArgsConstructor
class Product {
    private Integer id;

    private Integer count;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private Integer id;

    private BigDecimal salary;
}