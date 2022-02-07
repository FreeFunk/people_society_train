package com.edgedo.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("sys_city")
public class SysCity implements Serializable{
	
		
	/**
	 * 属性描述:id
	 */
	@TableId(value="ID")
	java.lang.String id;
	
	/**
	 * 属性描述:name
	 */
	@TableField(value="NAME",exist=true)
	java.lang.String name;
	
	/**
	 * 属性描述:provinceId
	 */
	@TableField(value="PROVINCE_ID",exist=true)
	java.lang.String provinceId;
	
	/**
	 * 属性描述:provinceNane
	 */
	@TableField(value="PROVINCE_NANE",exist=true)
	java.lang.String provinceNane;
	
	/**
	 * 属性描述:orderNumber
	 */
	@TableField(value="ORDER_NUMBER",exist=true)
	java.math.BigDecimal orderNumber;
	
	/**
	 * 属性描述:status
	 */
	@TableField(value="STATUS",exist=true)
	java.lang.String status;
	
	
	
	
	
	
	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.lang.String getName(){
		return this.name;
	}
	
	public void setName(java.lang.String name){
		this.name=name;
	}
	
	
	public java.lang.String getProvinceId(){
		return this.provinceId;
	}
	
	public void setProvinceId(java.lang.String provinceId){
		this.provinceId=provinceId;
	}
	
	
	public java.lang.String getProvinceNane(){
		return this.provinceNane;
	}
	
	public void setProvinceNane(java.lang.String provinceNane){
		this.provinceNane=provinceNane;
	}
	
	
	public java.math.BigDecimal getOrderNumber(){
		return this.orderNumber;
	}
	
	public void setOrderNumber(java.math.BigDecimal orderNumber){
		this.orderNumber=orderNumber;
	}
	
	
	public java.lang.String getStatus(){
		return this.status;
	}
	
	public void setStatus(java.lang.String status){
		this.status=status;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", name=").append(name);			
			sb.append(", provinceId=").append(provinceId);			
			sb.append(", provinceNane=").append(provinceNane);			
			sb.append(", orderNumber=").append(orderNumber);			
			sb.append(", status=").append(status);			
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
        SysCity other = (SysCity) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getName() == null ? other.getId() == null : this.getName().equals(other.getName()))		
				        		&&(this.getProvinceId() == null ? other.getId() == null : this.getProvinceId().equals(other.getProvinceId()))		
				        		&&(this.getProvinceNane() == null ? other.getId() == null : this.getProvinceNane().equals(other.getProvinceNane()))		
				        		&&(this.getOrderNumber() == null ? other.getId() == null : this.getOrderNumber().equals(other.getOrderNumber()))		
				        		&&(this.getStatus() == null ? other.getId() == null : this.getStatus().equals(other.getStatus()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());		
		        result = prime * result + ((getProvinceId() == null) ? 0 : getProvinceId().hashCode());		
		        result = prime * result + ((getProvinceNane() == null) ? 0 : getProvinceNane().hashCode());		
		        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());		
		        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());		
		;
        return result;
    }
	

}
