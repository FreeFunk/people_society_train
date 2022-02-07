package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("cms_article_cls")
public class CmsArticleCls implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:创建时间
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;
	
	/**
	 * 属性描述:分类名称
	 */
	@TableField(value="CLS_NAME",exist=true)
	java.lang.String clsName;
	
	/**
	 * 属性描述:排序号
	 */
	@TableField(value="SORT_NUM",exist=true)
	java.math.BigDecimal sortNum;
	
	/**
	 * 属性描述:分类状态
	 */
	@TableField(value="CLS_STATE",exist=true)
	java.lang.String clsState;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;
	
	
	
	
	
	
	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date createTime){
		this.createTime=createTime;
	}
	
	
	public java.lang.String getClsName(){
		return this.clsName;
	}
	
	public void setClsName(java.lang.String clsName){
		this.clsName=clsName;
	}
	
	
	public java.math.BigDecimal getSortNum(){
		return this.sortNum;
	}
	
	public void setSortNum(java.math.BigDecimal sortNum){
		this.sortNum=sortNum;
	}
	
	
	public java.lang.String getClsState(){
		return this.clsState;
	}
	
	public void setClsState(java.lang.String clsState){
		this.clsState=clsState;
	}
	
	
	public java.lang.String getDataState(){
		return this.dataState;
	}
	
	public void setDataState(java.lang.String dataState){
		this.dataState=dataState;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", createTime=").append(createTime);			
			sb.append(", clsName=").append(clsName);			
			sb.append(", sortNum=").append(sortNum);			
			sb.append(", clsState=").append(clsState);			
			sb.append(", dataState=").append(dataState);			
        sb.append("]");
        return sb.toString();
    }

   
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CmsArticleCls other = (CmsArticleCls) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getClsName() == null ? other.getId() == null : this.getClsName().equals(other.getClsName()))		
				        		&&(this.getSortNum() == null ? other.getId() == null : this.getSortNum().equals(other.getSortNum()))		
				        		&&(this.getClsState() == null ? other.getId() == null : this.getClsState().equals(other.getClsState()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getClsName() == null) ? 0 : getClsName().hashCode());		
		        result = prime * result + ((getSortNum() == null) ? 0 : getSortNum().hashCode());		
		        result = prime * result + ((getClsState() == null) ? 0 : getClsState().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
