/**
 * 
 */
package ${model.packageName};

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyi.util.PaginationData;
import ${model.daoPackage}.${model.daoClass};
import ${model.modelPackage}.${model.modelClass};
import ${model.servicePackage}.${model.interfaceName};

/**
 * 业务层接口实现类
 * 
 * @author apple
 * 
 */
@Service("${model.variable}")
public class ${model.className} implements ${model.interfaceName} {
	
	@Resource(name="${model.daoVariable}")
	private ${model.daoClass} ${model.daoVariable};
	

	
	@Override
	public void add(${model.modelClass} ${model.variableModel}, String usrName) {
		
		${model.daoVariable}.insert(${model.variableModel});
	}

	
	@Override
	public void update(${model.modelClass} ${model.variableModel}, String usrName) {

		${model.daoVariable}.updateByPrimaryKey(${model.variableModel});

	}

	
	@Override
	@Transactional
	public void delete(Integer[] ids, String usrName) {
		for (int i = 0; i < ids.length; i++) {
			// TODO	check rule
			${model.daoVariable}.deleteByPrimaryKey(ids[i]);
		}

	}

	
	@Override
	public int findCount(${model.modelClass} ${model.variableModel}) {
		Map<String, Object> map = new HashMap<String, Object>();
		//if(StringUtil.isEmpty(demo.getName()) == false)
		//	map.put("name", demo.getName());
		int count = ${model.daoVariable}.countByExample(map);
		return count;
	}

	
	@Override
	public List<${model.modelClass}> findPageList(${model.modelClass} ${model.variableModel}, PaginationData page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//if(StringUtil.isEmpty(demo.getName()) == false)
		//	map.put("name", demo.getName());
			
		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		map.put("orderByClause", page.getOrder());
		List<${model.modelClass}> list = ${model.daoVariable}.selectByExample(map);
		
		return list;
	}

	
	@Override
	public ${model.modelClass} getByKey(Integer id) {
		${model.modelClass} ${model.variableModel} = ${model.daoVariable}.selectByPrimaryKey(id);
		return ${model.variableModel};
	}
	
	

}
