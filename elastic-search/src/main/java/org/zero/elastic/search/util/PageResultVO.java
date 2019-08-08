package org.zero.elastic.search.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "分页对象")
public class PageResultVO<T> {
	private static final long serialVersionUID = 1L;
	/**
	 * 总条数
	 */
	@ApiModelProperty(value = "总条数")
	private long total;

	/**
	 * 数据集
	 */
	@ApiModelProperty(value = "数据集")
	private List<T> records;

	public PageResultVO() {

	}

	public PageResultVO(long total, List<T> list) {
		this.setRecords(list);
		this.setTotal(total);
	}
}
