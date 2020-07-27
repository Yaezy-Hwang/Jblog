package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.CommentVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;

	@RequestMapping("/{id}")
	public String goBlog(@RequestParam(value="crtCateNo", required = false, defaultValue="0") int crtCateNo,
					 	 @RequestParam(value="postNo", required = false, defaultValue="0") int postNo,
						 Model model, @PathVariable String id) {
		System.out.println("/블로그로 이동");
		
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> cateList = blogService.getCategoryList(id);
		model.addAttribute("cateList", cateList);
		
		
		List<PostVo> postList = blogService.getPostList(id, crtCateNo);
		model.addAttribute("postList", postList);

		PostVo postVo = blogService.getPost(id, crtCateNo, postNo);
		model.addAttribute("postVo", postVo);
		
		List<CommentVo> commentList = blogService.getCmtList(crtCateNo, postNo);
		model.addAttribute("commentList", commentList);
		
		return "blog/blog-main";
	}
	
	@RequestMapping("/{id}/logout")
	public String blogLogout(@PathVariable String id, HttpSession session) {
		System.out.println("/블로그에서 로그아웃");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/"+id;
	}
	
	@ResponseBody
	@RequestMapping("/{id}/comment")
	public CommentVo comment(@PathVariable String id, CommentVo commentVo) {
		System.out.println("/새 댓글 붙이기 API");
		
		return blogService.addCmt(commentVo);
	}
	
	
}
