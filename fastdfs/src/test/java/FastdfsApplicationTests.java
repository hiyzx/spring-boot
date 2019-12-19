import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.proto.storage.DownloadFileWriter;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zero.fastdfs.FastDFSApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FastDFSApplication.class)
public class FastdfsApplicationTests {

    private File file = null;
    private Set<MateData> metaDataSet = null;

    private static final String groupName = "group1";

    // fastDFS存储文件的path，这个path路径需要你执行测试方法中上传后，获取的path粘贴过来，用于查询、删除的
    private static final String path = "M00/00/00/rBKURl3rCeCAGzMCAABFgSQxmnM302.png";

    // 默认文件名称，你所需要上传的图片名称
    private static final String filename = "01.jpg";

    // 默认文件格式，后缀名,设置上传后在fastdfs存储的格式，你可以改成其它格式图片，fastdfs只支持几种常用格式的，自己百度可以查查，jpg和png都是可以的
    private static final String fileExtName = "png";

    // 带组名的path
    private static final String filePath = groupName + path;

    @Before
    public void newFile() {
        metaDataSet = createMetaData();
        file = new File("C:\\Users\\GVT\\Desktop\\充值截图.png");
    }

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    /**
     * 测试1--图片上传
     */
    @Test
    public void testUpload() throws FileNotFoundException {
        // 上传图片
        StorePath storePath =
            storageClient.uploadFile(new FileInputStream(file), file.length(), fileExtName, metaDataSet);
        printlnPath(storePath);
    }

    /**
     * 测试2--图片上传缩略图
     */
    @Test
    public void testCrtThumbImage() throws FileNotFoundException {
        // 上传图片的缩略图
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(new FileInputStream(file), file.length(),
            fileExtName, metaDataSet);
        String fullPath = thumbImageConfig.getThumbImagePath(storePath.getFullPath());
        System.out.println("【图片缩略图带有组名的路径】:" + fullPath);
        printlnPath(storePath);
    }

    /**
     * 查询
     */
    @Test
    public void testQuery() {
        FileInfo fileInfo = storageClient.queryFileInfo(groupName, path);
        System.out.println("图片信息如下：\n" + fileInfo.getCrc32() + "\n" + new Date(fileInfo.getCreateTime()) + "\n"
            + fileInfo.getFileSize() + "\n" + fileInfo.getSourceIpAddr());
    }

    /**
     * 删除
     */
    @Test
    public void testDel() {
        storageClient.deleteFile(filePath);
    }

    /**
     * 删除(效果同上删除)
     */
    @Test
    public void testDel2() {
        storageClient.deleteFile(groupName, path);
    }

    /**
     * 下载文件
     */
    @Test
    public void downLoadFile() {
        DownloadFileWriter callback = new DownloadFileWriter(filename);
        storageClient.downloadFile(groupName, path, callback);
        // StorageDownloadCommand<String> stringStorageDownloadCommand = new
        // StorageDownloadCommand<>(variables.groupName, variables.path, callback);
        // String fileName = commandTestBase.executeStoreCmd(stringStorageDownloadCommand);
    }

    /**
     * 创建元信息
     *
     * @return
     */
    private Set<MateData> createMetaData() {
        Set<MateData> metaDataSet = new HashSet<MateData>();
        metaDataSet.add(new MateData("Author", "zero"));
        metaDataSet.add(new MateData("CreateDate", "2019-12-11"));
        return metaDataSet;
    }

    private void printlnPath(StorePath storePath) {
        // 组名
        System.out.println("【组名】:" + storePath.getGroup());
        // 带组名的文件地址
        System.out.println("【带组名的文件地址】:" + storePath.getFullPath());
        // 不带组名的文件地址
        System.out.println("【不带组名的文件地址】:" + storePath.getPath());
    }

}