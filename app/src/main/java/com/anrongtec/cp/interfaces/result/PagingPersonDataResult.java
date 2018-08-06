package com.anrongtec.cp.interfaces.result;

import com.anrongtec.cp.entity.PersonControlInfoEntity;

import java.util.List;

/**
 * 分页接口数据  格式
 */
public class PagingPersonDataResult<T>  {
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


	public String getResultCode() {
		return code;
	}

	public void setResultCode(String resultCode) {
		this.code = resultCode;
	}

	public String getResultMsg() {
		return msg;
	}

	public void setResultMsg(String resultMsg) {
		this.msg = resultMsg;
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
		private List<PersonControlInfoEntity> resultList;

		public PagingInfo getPagingInfo() {
			return pagingInfo;
		}

		public void setPagingInfo(PagingInfo pagingInfo) {
			this.pagingInfo = pagingInfo;
		}

		public List<PersonControlInfoEntity> getResultList() {
			return resultList;
		}

		public void setResultList(List<PersonControlInfoEntity> resultList) {
			this.resultList = resultList;
		}
	}

}
