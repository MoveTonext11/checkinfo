package com.anrongtec.cp.interfaces.result;

import com.anrongtec.cp.entity.CarControlInfoEntity;

import java.util.List;

/**
 * 分页接口数据  格式
 */
public class PagingCarDataResult<T>  {
	/**
	 * 返回码
	 */
	private String code;

	/**
	 * 返回信息
	 */
	private String msg;

	/**
	 * 返回的数据
	 */
	private PaingData data;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public PaingData getData() {
		return data;
	}

	public void setData(PaingData data) {
		this.data = data;
	}

	public  class PaingData{
		/**
		 * 分页信息
		 */
		private PagingInfo pagingInfo;

		/**
		 * 结果集合
		 */
		private List<CarControlInfoEntity> resultList;

		public PagingInfo getPagingInfo() {
			return pagingInfo;
		}

		public void setPagingInfo(PagingInfo pagingInfo) {
			this.pagingInfo = pagingInfo;
		}

		public List<CarControlInfoEntity> getResultList() {
			return resultList;
		}

		public void setResultList(List<CarControlInfoEntity> resultList) {
			this.resultList = resultList;
		}
	}

}
