import cn.hutool.core.io.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zero.fastdfs.FastDFSApplication;
import org.zero.fastdfs.util.FastDFSClient;

import java.io.InputStream;

/**
 * @author yezhaoxing
 * @date 2019/5/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FastDFSApplication.class)
public class FastDFSTest {

	@Test
	public void test() {
		/*File file = new File("/Users/zero/Documents/jcclub-tmp.sql");
		byte[] bytes = FileUtil.readBytes(file);

		FastDFSFile fastDFSFile = new FastDFSFile("jcclub.sql", bytes, "sql", "", "");
		FastDFSClient.upload(fastDFSFile);
*/
		InputStream in = FastDFSClient.downFile("group1", "M00/00/00/rB4ACFzfeA6ACFz8AAAkeon-1Sg832.sql");
		FileUtil.writeFromStream(in, "/Users/zero/Documents/jcclub-download.sql");


	}

}
