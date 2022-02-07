<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${modelpackage}.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import ${modelpackage}.entity.${className};
import ${modelpackage}.mapper.${className}Mapper;
import ${modelpackage}.queryvo.${className}Query;
import ${modelpackage}.queryvo.${className}View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ${className}Service {
	
	
	@Autowired
	private ${className}Mapper ${camelClassName}Mapper;

	public List<${className}View> listPage(${className}Query ${camelClassName}Query){
		List list = ${camelClassName}Mapper.listPage(${camelClassName}Query);
		${camelClassName}Query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(${className} ${camelClassName}) {
		${camelClassName}.setId(Guid.guid());
		${camelClassName}Mapper.insert(${camelClassName});
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(${className} ${camelClassName}) {
		${camelClassName}Mapper.updateById(${camelClassName});
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(${className} ${camelClassName}) {
		${camelClassName}Mapper.updateAllColumnById(${camelClassName});
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return ${camelClassName}Mapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return ${camelClassName}Mapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public ${className} loadById(String id) {
		return ${camelClassName}Mapper.selectById(id);
	}
	

}
