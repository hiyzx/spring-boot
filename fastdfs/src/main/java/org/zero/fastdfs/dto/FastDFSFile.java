package org.zero.fastdfs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FastDFSFile {
	private String name;
	private byte[] content;
	private String ext;
	private String md5;
	private String author;
}