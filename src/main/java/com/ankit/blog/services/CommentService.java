package com.ankit.blog.services;

import com.ankit.blog.payloads.CommentDto;

public interface CommentService {

	
	CommentDto createComment (CommentDto commentDto, Integer PostId);
	
	void deleteComment (Integer commentId);
	
}
