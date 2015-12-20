package com.panda.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.mapper.CommentMapper;
import com.panda.model.Comment;
import com.panda.service.CommentService;

@Service("CommentService")
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<Comment> getAllComment() {
		// TODO Auto-generated method stub
		return commentMapper.getAllComment();
	}

}
