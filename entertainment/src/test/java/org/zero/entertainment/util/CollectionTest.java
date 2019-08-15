package org.zero.entertainment.util;

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
        List<Object> list = new ArrayList<>(map1.values());
        List<Product> collect1 = list.stream().map(l -> (Product) l).collect(Collectors.toList());
    }
}

@Data
@AllArgsConstructor
class Product {
    private Integer id;

    private Integer count;
}
