package org.zero.fastdfs.util;

import lombok.extern.slf4j.Slf4j;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.zero.fastdfs.dto.FastDFSFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yezhaoxing
 * @date 2019/5/18
 */
@Slf4j
public class FastDFSClient {

	private static TrackerServer trackerServer;
	private static StorageServer storageServer;
	private static StorageClient storageClient;

	static {
		try {
			String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
			ClientGlobal.init(filePath);
			TrackerClient trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageServer = trackerClient.getStoreStorage(trackerServer);
		} catch (Exception e) {
			log.error("FastDFS Client Init Fail!", e);
		}
	}

	public static String[] upload(FastDFSFile file) {
		log.info("File Name: " + file.getName() + "File Length:" + file.getContent().length);

		NameValuePair[] meta_list = new NameValuePair[1];
		meta_list[0] = new NameValuePair("author", file.getAuthor());

		long startTime = System.currentTimeMillis();
		String[] uploadResults = null;
		try {
			storageClient = new StorageClient(trackerServer, storageServer);
			uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
		} catch (IOException e) {
			log.error("IO Exception when uploadind the file:" + file.getName(), e);
		} catch (Exception e) {
			log.error("Non IO Exception when uploadind the file:" + file.getName(), e);
		}
		log.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");

		if (uploadResults == null) {
			log.error("upload file fail, error code:" + storageClient.getErrorCode());
		}
		String groupName = uploadResults[0];
		String remoteFileName = uploadResults[1];

		log.info("upload file successfully!!!" + "group_name:" + groupName + ", remoteFileName:" + " " + remoteFileName);
		return uploadResults;
	}

	public static FileInfo getFile(String groupName, String remoteFileName) {
		try {
			storageClient = new StorageClient(trackerServer, storageServer);
			return storageClient.get_file_info(groupName, remoteFileName);
		} catch (IOException e) {
			log.error("IO Exception: Get File from Fast DFS failed", e);
		} catch (Exception e) {
			log.error("Non IO Exception: Get File from Fast DFS failed", e);
		}
		return null;
	}

	public static InputStream downFile(String groupName, String remoteFileName) {
		try {
			storageClient = new StorageClient(trackerServer, storageServer);
			byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
			InputStream ins = new ByteArrayInputStream(fileByte);
			return ins;
		} catch (IOException e) {
			log.error("IO Exception: Get File from Fast DFS failed", e);
		} catch (Exception e) {
			log.error("Non IO Exception: Get File from Fast DFS failed", e);
		}
		return null;
	}

	public static void deleteFile(String groupName, String remoteFileName)
			throws Exception {
		storageClient = new StorageClient(trackerServer, storageServer);
		int i = storageClient.delete_file(groupName, remoteFileName);
		log.info("delete file successfully!!!" + i);
	}

}
