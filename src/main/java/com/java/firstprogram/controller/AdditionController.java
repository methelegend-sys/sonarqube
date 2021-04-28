package com.java.firstprogram.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdditionController {
	
	@RequestMapping(value="/add",params="b1")
	public ModelAndView addNumber(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv=new ModelAndView();
		int c=0;
		
		int a = Integer.parseInt(request.getParameter("t1"));
		int b = Integer.parseInt(request.getParameter("t2"));
		
		c=a+b;
		
		
		mv.addObject("result",c);
		mv.setViewName("add-result");
		
		return mv;
		
	}
	@RequestMapping(value="/add",params="b2")
	public ModelAndView subNumber(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		int c=0;
		
		int a = Integer.parseInt(request.getParameter("t1"));
		int b = Integer.parseInt(request.getParameter("t2"));
		
		c=a-b;
		
		
		mv.addObject("result",c);
		mv.setViewName("add-result");
		
		return mv;
	}
	@RequestMapping(value="/add",params="b3")
	public ModelAndView divNumber(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		float c=0;
		
		int a = Integer.parseInt(request.getParameter("t1"));
		int b = Integer.parseInt(request.getParameter("t2"));
		
		c=a/b;
		
		
		mv.addObject("result",c);
		mv.setViewName("add-result");
		
		return mv;
	}
	@RequestMapping(value="/add",params="b4")
	public ModelAndView mulNumber(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		int c=0;
		
		int a = Integer.parseInt(request.getParameter("t1"));
		int b = Integer.parseInt(request.getParameter("t2"));
		
		c=a*b;
		
		
		mv.addObject("result",c);
		mv.setViewName("add-result");
		
		return mv;
	}

}
