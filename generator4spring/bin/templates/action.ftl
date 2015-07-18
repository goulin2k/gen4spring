/**
 * 
 */
package ${model.packageName};

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianyi.util.PaginationData;
import com.tiyi.tyapp.common.ReturnResult;
import com.tiyi.tyapp.common.utils.Constants;
import com.tiyi.tyapp.common.exception.KeyHasExistException;
import ${model.modelPackage}.${model.modelClass};
import ${model.servicePackage}.${model.modelClass}Service;

/**
 * Spring MVC控制类
 * 
 * @author generated
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/${model.variableModel}")
public class ${model.className} extends BaseAction {

	@Resource(name = "${model.variableModel}Service")
	protected ${model.modelClass}Service ${model.variableModel}Service;

	/**
	 * 点位查询列表页Controller
	 * 
	 * @param name
	 * @param ${model.variableModel}Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list.do")
	public ModelAndView index(
			${model.modelClass} ${model.variableModel},
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("${model.variableModel}/list");

		PaginationData page = super.getPage();
		if (page.getTotal() == 0) {
			page.setTotal(${model.variableModel}Service.findCount(${model.variableModel}));
			page.setPageNo(1);
		} else {
			page.setPageNo(pageNo.intValue());
		}
		page.setRows(${model.variableModel}Service.findPageList(${model.variableModel}, page));
		mav.addObject("page", page);
		mav.addObject("${model.variableModel}", ${model.variableModel});

		return mav;
	}

	@RequestMapping(value = "view.do")
	public ModelAndView add(
			@RequestParam(value = "${model.key}", required = false) Integer ${model.key},
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("${model.variableModel}/view");
		${model.modelClass} ${model.variableModel} = new ${model.modelClass}();
		if (${model.key} != null) {
			${model.variableModel} = ${model.variableModel}Service.getByKey(${model.key});
		}

		mav.addObject("${model.variableModel}", ${model.variableModel});
		return mav;
	}

	/**
	 * 保存点位信息Control
	 * 
	 * @param ${model.variableModel}
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save.do")
	public ModelAndView save(${model.modelClass} ${model.variableModel}, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("${model.variableModel}/list");
		String loginName = getLoginName(request);
		
		try {
			if (${model.variableModel}.getId() == null)
				${model.variableModel}Service.add(${model.variableModel}, loginName);
			else
				${model.variableModel}Service.update(${model.variableModel}, loginName);

			log.info(${model.variableModel});

			return index(null, null, null, null, request);
		} catch (KeyHasExistException e) {
			mav.addObject("errors", "编码：【" + e.getKey() + "】已经存在！");
			mav.addObject("${model.variableModel}", ${model.variableModel});
			mav.setViewName("${model.variableModel}/view");
			return mav;
		}
	}

	/**
	 * 删除指定点位信息
	 * 
	 * @param ${model.variableModel}Ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "remove.do")
	public ModelAndView remove(
			@RequestParam("${model.variableModel}Ids") String ${model.variableModel}IdString,
			HttpServletRequest request) {

		String[] ${model.variableModel}Ids = ${model.variableModel}IdString.split(",");
		${model.variableModel}Service.delete(stringArrayToIntegers(${model.variableModel}Ids), 
			getLoginName(request));
		return index(null, null, null, null, request);
	}

	
}
