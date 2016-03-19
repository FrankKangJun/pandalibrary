package com.panda.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.panda.model.Comment;
import com.panda.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	private CommentService commentservice;

	@Autowired
	public void setCommentservice(CommentService commentservice) {
		this.commentservice = commentservice;
	}
	
	@RequestMapping("getAllComment")
	public @ResponseBody JSONArray getAllComment()
	{
		List<Comment> comments = new ArrayList<Comment>();
		comments = commentservice.getAllComment();
		JSONArray jsonArray=JSON.parseArray(JSON.toJSONStringWithDateFormat(comments, "yyyy-MM-dd"));
		return jsonArray;
	}
}
