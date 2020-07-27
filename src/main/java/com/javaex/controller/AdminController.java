package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AdminService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
@RequestMapping("/{id}/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/basic")
	public String adminGetbasicInfo(@PathVariable String id, Model model) {
		System.out.println("/admin/basic");
		
		BlogVo blogVo = adminService.getBlogInfo(id);
		
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-basic";
	}
	
	@RequestMapping("/updateBasic")
	public String updateBasic(@PathVariable String id, @RequestParam("logoFile") MultipartFile logoFile, @RequestParam("blogTitle") String blogTitle) {
		System.out.println("/admin/update");
		
		adminService.updateBasic(id, logoFile, blogTitle);
		
		return "redirect:/"+id+"/admin/basic";
	}
	
	
	@RequestMapping("/category")
	public String adminGetCateInfo(@PathVariable String id, Model model) {
		System.out.println("/admin/category");
		
		BlogVo blogVo = adminService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> cateList= adminService.adminCateList(id);
		model.addAttribute("cateList", cateList);
		
		return "blog/admin/blog-admin-cate";
	}
	
	@ResponseBody
	@RequestMapping("/addCategory")
	public CategoryVo addCategory(@PathVariable String id, CategoryVo categoryVo) {
		System.out.println("/admin/addCategory API");
		
		return adminService.addCategory(categoryVo);
	}
	
	@ResponseBody
	@RequestMapping("/delCategory")
	public int delCategory(@PathVariable String id, int cateNo) {
		System.out.println("/admin/delCategory API");
		
		return adminService.delCategory(cateNo);
	}	
	
	@RequestMapping("/writeForm")
	public String adminWriteForm(@PathVariable String id, Model model) {
		System.out.println("/admin/writeForm");
		
		BlogVo blogVo = adminService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> cateList = adminService.getCategoryList(id);
		model.addAttribute("cateList", cateList);
		
		return "blog/admin/blog-admin-write";
	}
	
	@RequestMapping("/write")
	public String adminWrite(@PathVariable String id, @ModelAttribute PostVo postVo) {
		System.out.println("/admin/writeForm");
		
		adminService.writePost(postVo);
		
		return "redirect:/"+id+"/admin/writeForm";
	}
	
	
	
}
