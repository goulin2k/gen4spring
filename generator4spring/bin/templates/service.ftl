package ${model.packageName};

import java.util.List;

import com.tianyi.util.PaginationData;
import ${model.modelPackage}.${model.modelClass};

/**
 * 业务层接口
 * 
 * @author apple
 * 
 */
public interface ${model.className} {
	
	
	public void add(${model.modelClass} ${model.variableModel}, String usrName);
	
	
	public ${model.modelClass} getByKey(Integer ${model.variableModel}Id);
	
	
	public void update(${model.modelClass} ${model.variableModel}, String usrName);
	
	
	public void delete(Integer[] ${model.variableModel}Ids, String usrName);
	
	
	public int findCount(${model.modelClass} query${model.modelClass});
	
	
	public List<${model.modelClass}> findPageList(${model.modelClass} query${model.modelClass}, PaginationData page);
	

}
