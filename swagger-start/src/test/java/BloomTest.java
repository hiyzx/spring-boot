import cn.hutool.bloomfilter.BitMapBloomFilter;
import cn.hutool.bloomfilter.BloomFilterUtil;

/**
 * @author yezhaoxing
 * @date 2019/10/11
 */
public class BloomTest {

    public static void main(String[] args){
        BitMapBloomFilter bitMap = BloomFilterUtil.createBitMap(5);
        for (int i = 0; i < 10000; i++) {
            bitMap.add(String.valueOf(i));
        }
        boolean contains = bitMap.contains(String.valueOf(10));

    }
}
