package com.zero.web.controller;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zero.enums.CodeEnum;
import com.zero.vo.BaseReturnVo;
import com.zero.vo.ReturnVo;
import com.zero.web.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * 全局异常类配置
 */
@Controller
@ControllerAdvice
public class ExceptionController {
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.setSerializationInclusion(Include.NON_NULL);
	}

	@ResponseBody
	@RequestMapping(value = "/404", method = {GET, POST})
	public BaseReturnVo _404() {
		return new ReturnVo<>(CodeEnum.PAGE_NOT_FOUND, "Page Not Found");
	}

	@ResponseBody
	@RequestMapping(value = "/400", method = {GET, POST})
	public BaseReturnVo _400() {
		return new ReturnVo<>(CodeEnum.PAGE_NOT_FOUND, "Page Not Found");
	}

	@ResponseBody
	@RequestMapping(value = "/405", method = {GET, POST})
	public BaseReturnVo _405() {
		return new ReturnVo<>(CodeEnum.PAGE_NOT_FOUND, "Page Not Found");
	}

	@ResponseBody
	@RequestMapping(value = "/500", method = {GET, POST})
	public BaseReturnVo _500() {
		return new ReturnVo<>(CodeEnum.INTERNAL_SERVER_ERROR, "Internal Server Error");
	}

	@ExceptionHandler(BaseException.class)
	public ModelAndView resolveException(BaseException e) {
		ModelAndView mav = new ModelAndView();
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setExtractValueFromSingleKeyModel(true);
		mav.setView(view);
		view.setObjectMapper(MAPPER);
		BaseReturnVo returnVO = new BaseReturnVo(e.getCodeEnum(), e.getMsg());
		mav.addObject(returnVO);
		LOG.error(e.getMessage());
		return mav;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView resolveException(Exception e) {
		ModelAndView mav = new ModelAndView();
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setExtractValueFromSingleKeyModel(true);
		mav.setView(view);
		view.setObjectMapper(MAPPER);
		BaseReturnVo returnVO = new BaseReturnVo(CodeEnum.INTERNAL_SERVER_ERROR, "Internal Server Error");
		mav.addObject(returnVO);
		LOG.error(e.getMessage(), e);
		return mav;
	}
}
