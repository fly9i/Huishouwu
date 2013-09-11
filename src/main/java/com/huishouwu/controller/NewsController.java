package com.huishouwu.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishouwu.dao.NewsDao;
import com.huishouwu.pojo.News;
import com.huishouwu.pojo.User;

@Controller
public class NewsController {
	
	@Resource
	private NewsDao newsDao;
	
	@RequestMapping("news/add")
	@ResponseBody
	public String addNew(@RequestParam String title,@RequestParam String author,@RequestParam String content){
		newsDao.addNews(title, content, new Date(), author);
		return "{code:200,des:'文章添加成功'}";
	}
	
	
	@RequestMapping("news/{id}")
	public String getNewsById(@PathVariable int id,Model m){

		News news=newsDao.getNewsById(id).get(0);
		m.addAttribute("title", news.getTitle());
		m.addAttribute("news",news);
		return "news_one";
	}
}
