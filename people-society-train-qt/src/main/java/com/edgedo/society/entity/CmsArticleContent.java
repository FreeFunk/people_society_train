package com.edgedo.society.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("cms_article_content")
public class CmsArticleContent implements Serializable{
	
		
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
	 * 属性描述:所属企业
	 */
	@TableField(value="COMP_ID",exist=true)
	java.lang.String compId;
	
	/**
	 * 属性描述:主图
	 */
	@TableField(value="MAIN_PHOTO",exist=true)
	java.lang.String mainPhoto;
	
	/**
	 * 属性描述:标题
	 */
	@TableField(value="TITLE",exist=true)
	java.lang.String title;
	
	/**
	 * 属性描述:发布日期
	 */
	@TableField(value="PUBLISH_TIME",exist=true)
	java.util.Date publishTime;
	
	/**
	 * 属性描述:内容
	 */
	@TableField(value="CONTENT",exist=true)
	java.lang.String content;
	
	/**
	 * 属性描述:摘要
	 */
	@TableField(value="SUMMARY",exist=true)
	java.lang.String summary;
	
	/**
	 * 属性描述:点击次数
	 */
	@TableField(value="CLICK_NUM",exist=true)
	java.lang.Integer clickNum;
	
	/**
	 * 属性描述:文章类型id
	 */
	@TableField(value="CLS_ID",exist=true)
	java.lang.String clsId;
	
	/**
	 * 属性描述:关键字
	 */
	@TableField(value="KEY_WORDS",exist=true)
	java.lang.String keyWords;
	
	/**
	 * 属性描述:是否推荐
	 */
	@TableField(value="IS_RECOMOND",exist=true)
	java.lang.String isRecomond;
	
	/**
	 * 属性描述:推荐序号
	 */
	@TableField(value="RECOMOND_SORT",exist=true)
	java.math.BigDecimal recomondSort;
	
	/**
	 * 属性描述:文章状态
	 */
	@TableField(value="ART_STATE",exist=true)
	java.lang.String artState;
	
	/**
	 * 属性描述:审核状态
	 */
	@TableField(value="SH_STATE",exist=true)
	java.lang.String shState;
	
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
	
	
	public java.lang.String getCompId(){
		return this.compId;
	}
	
	public void setCompId(java.lang.String compId){
		this.compId=compId;
	}
	
	
	public java.lang.String getMainPhoto(){
		return this.mainPhoto;
	}
	
	public void setMainPhoto(java.lang.String mainPhoto){
		this.mainPhoto=mainPhoto;
	}
	
	
	public java.lang.String getTitle(){
		return this.title;
	}
	
	public void setTitle(java.lang.String title){
		this.title=title;
	}
	
	
	public java.util.Date getPublishTime(){
		return this.publishTime;
	}
	
	public void setPublishTime(java.util.Date publishTime){
		this.publishTime=publishTime;
	}
	
	
	public java.lang.String getContent(){
		return this.content;
	}
	
	public void setContent(java.lang.String content){
		this.content=content;
	}
	
	
	public java.lang.String getSummary(){
		return this.summary;
	}
	
	public void setSummary(java.lang.String summary){
		this.summary=summary;
	}
	
	
	public java.lang.Integer getClickNum(){
		return this.clickNum;
	}
	
	public void setClickNum(java.lang.Integer clickNum){
		this.clickNum=clickNum;
	}
	
	
	public java.lang.String getClsId(){
		return this.clsId;
	}
	
	public void setClsId(java.lang.String clsId){
		this.clsId=clsId;
	}
	
	
	public java.lang.String getKeyWords(){
		return this.keyWords;
	}
	
	public void setKeyWords(java.lang.String keyWords){
		this.keyWords=keyWords;
	}
	
	
	public java.lang.String getIsRecomond(){
		return this.isRecomond;
	}
	
	public void setIsRecomond(java.lang.String isRecomond){
		this.isRecomond=isRecomond;
	}
	
	
	public java.math.BigDecimal getRecomondSort(){
		return this.recomondSort;
	}
	
	public void setRecomondSort(java.math.BigDecimal recomondSort){
		this.recomondSort=recomondSort;
	}
	
	
	public java.lang.String getArtState(){
		return this.artState;
	}
	
	public void setArtState(java.lang.String artState){
		this.artState=artState;
	}
	
	
	public java.lang.String getShState(){
		return this.shState;
	}
	
	public void setShState(java.lang.String shState){
		this.shState=shState;
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
			sb.append(", compId=").append(compId);			
			sb.append(", mainPhoto=").append(mainPhoto);			
			sb.append(", title=").append(title);			
			sb.append(", publishTime=").append(publishTime);			
			sb.append(", content=").append(content);			
			sb.append(", summary=").append(summary);			
			sb.append(", clickNum=").append(clickNum);			
			sb.append(", clsId=").append(clsId);			
			sb.append(", keyWords=").append(keyWords);			
			sb.append(", isRecomond=").append(isRecomond);			
			sb.append(", recomondSort=").append(recomondSort);			
			sb.append(", artState=").append(artState);			
			sb.append(", shState=").append(shState);			
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
        CmsArticleContent other = (CmsArticleContent) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCompId() == null ? other.getId() == null : this.getCompId().equals(other.getCompId()))		
				        		&&(this.getMainPhoto() == null ? other.getId() == null : this.getMainPhoto().equals(other.getMainPhoto()))		
				        		&&(this.getTitle() == null ? other.getId() == null : this.getTitle().equals(other.getTitle()))		
				        		&&(this.getPublishTime() == null ? other.getId() == null : this.getPublishTime().equals(other.getPublishTime()))		
				        		&&(this.getContent() == null ? other.getId() == null : this.getContent().equals(other.getContent()))		
				        		&&(this.getSummary() == null ? other.getId() == null : this.getSummary().equals(other.getSummary()))		
				        		&&(this.getClickNum() == null ? other.getId() == null : this.getClickNum().equals(other.getClickNum()))		
				        		&&(this.getClsId() == null ? other.getId() == null : this.getClsId().equals(other.getClsId()))		
				        		&&(this.getKeyWords() == null ? other.getId() == null : this.getKeyWords().equals(other.getKeyWords()))		
				        		&&(this.getIsRecomond() == null ? other.getId() == null : this.getIsRecomond().equals(other.getIsRecomond()))		
				        		&&(this.getRecomondSort() == null ? other.getId() == null : this.getRecomondSort().equals(other.getRecomondSort()))		
				        		&&(this.getArtState() == null ? other.getId() == null : this.getArtState().equals(other.getArtState()))		
				        		&&(this.getShState() == null ? other.getId() == null : this.getShState().equals(other.getShState()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getCompId() == null) ? 0 : getCompId().hashCode());		
		        result = prime * result + ((getMainPhoto() == null) ? 0 : getMainPhoto().hashCode());		
		        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());		
		        result = prime * result + ((getPublishTime() == null) ? 0 : getPublishTime().hashCode());		
		        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());		
		        result = prime * result + ((getSummary() == null) ? 0 : getSummary().hashCode());		
		        result = prime * result + ((getClickNum() == null) ? 0 : getClickNum().hashCode());		
		        result = prime * result + ((getClsId() == null) ? 0 : getClsId().hashCode());		
		        result = prime * result + ((getKeyWords() == null) ? 0 : getKeyWords().hashCode());		
		        result = prime * result + ((getIsRecomond() == null) ? 0 : getIsRecomond().hashCode());		
		        result = prime * result + ((getRecomondSort() == null) ? 0 : getRecomondSort().hashCode());		
		        result = prime * result + ((getArtState() == null) ? 0 : getArtState().hashCode());		
		        result = prime * result + ((getShState() == null) ? 0 : getShState().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
