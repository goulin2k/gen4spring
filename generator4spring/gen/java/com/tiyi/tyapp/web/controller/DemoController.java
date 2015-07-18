/**
 * 
 */
package com.tiyi.tyapp.web.controller;

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
import com.tiyi.tyapp.model.Demo;
import com.tiyi.tyapp.service.DemoService;

/**
 * Spring MVC控制类
 * 
 * @author generated
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseAction {

	@Resource(name = "demoService")
	protected DemoService demoService;

	/**
	 * 点位查询列表页Controller
	 * 
	 * @param name
	 * @param demoId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list.do")
	public ModelAndView index(
			Demo demo,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("demo/list");

		PaginationData page = super.getPage();
		if (page.getTotal() == 0) {
			page.setTotal(demoService.findCount(demo));
			page.setPageNo(1);
		} else {
			page.setPageNo(pageNo.intValue());
		}
		page.setRows(demoService.findPageList(demo, page));
		mav.addObject("page", page);
		mav.addObject("demo", demo);

		return mav;
	}

	@RequestMapping(value = "view.do")
	public ModelAndView add(
			@RequestParam(value = "id", required = false) Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("demo/view");
		Demo demo = new Demo();
		if (id != null) {
			demo = demoService.getByKey(id);
		}

		mav.addObject("demo", demo);
		return mav;
	}

	/**
	 * 保存点位信息Control
	 * 
	 * @param demo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save.do")
	public ModelAndView save(Demo demo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("demo/list");
		String loginName = getLoginName(request);
		
		try {
			if (demo.getId() == null)
				demoService.add(demo, loginName);
			else
				demoService.update(demo, loginName);

			log.info(demo);

			return index(null, null, null, null, request);
		} catch (KeyHasExistException e) {
			mav.addObject("errors", "编码：【" + e.getKey() + "】已经存在！");
			mav.addObject("demo", demo);
			mav.setViewName("demo/view");
			return mav;
		}
	}

	/**
	 * 删除指定点位信息
	 * 
	 * @param demoIds
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "remove.do")
	public ModelAndView remove(
			@RequestParam("demoIds") String demoIdString,
			HttpServletRequest request) {

		String[] demoIds = demoIdString.split(",");
		demoService.delete(stringArrayToIntegers(demoIds), 
			getLoginName(request));
		return index(null, null, null, null, request);
	}

	
}
