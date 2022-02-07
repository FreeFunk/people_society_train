<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${modelpackage}.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import ${modelpackage}.entity.${className};
import ${modelpackage}.queryvo.${className}Query;
import ${modelpackage}.queryvo.${className}View;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ${className}Mapper  extends BaseMapper<${className}>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<${className}View> listPage(${className}Query query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<${className}View> listByObj(${className}Query query);
	
	

}