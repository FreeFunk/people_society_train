package com.edgedo.sys.entity;


import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("sys_provice")
public class SysProvice implements Serializable{
	
		
	/**
	 * 属性描述:id
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:name
	 */
	@TableField(value="NAME",exist=true)
	java.lang.String name;
	
	/**
	 * 属性描述:area
	 */
	@TableField(value="AREA",exist=true)
	java.lang.String area;
	
	/**
	 * 属性描述:orderNumber
	 */
	@TableField(value="ORDER_NUMBER",exist=true)
	java.math.BigDecimal orderNumber;
	
	
	
	
	
	
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
	
	
	public java.lang.String getArea(){
		return this.area;
	}
	
	public void setArea(java.lang.String area){
		this.area=area;
	}
	
	
	public java.math.BigDecimal getOrderNumber(){
		return this.orderNumber;
	}
	
	public void setOrderNumber(java.math.BigDecimal orderNumber){
		this.orderNumber=orderNumber;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", name=").append(name);			
			sb.append(", area=").append(area);			
			sb.append(", orderNumber=").append(orderNumber);			
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
        SysProvice other = (SysProvice) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getName() == null ? other.getId() == null : this.getName().equals(other.getName()))		
				        		&&(this.getArea() == null ? other.getId() == null : this.getArea().equals(other.getArea()))		
				        		&&(this.getOrderNumber() == null ? other.getId() == null : this.getOrderNumber().equals(other.getOrderNumber()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());		
		        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());		
		        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());		
		;
        return result;
    }
	


}
