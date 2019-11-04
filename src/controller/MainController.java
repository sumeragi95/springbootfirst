package fj.king.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fj.king.config.viewConfig;
import fj.king.mapper.IdentityMapper;
import fj.king.model.Identity;
import fj.king.model.IdentityForm;

@Controller
@RequestMapping("app")
public class MainController {
	private static int currentPage = 1;
	private static int pageSize  = viewConfig.ItemPerPage;
	public static List<Identity> empty = new ArrayList<Identity>();
	@Autowired
	IdentityMapper identityMapper;
	
	@RequestMapping(value= "/add", method = RequestMethod.POST)
	public String save(@ModelAttribute @Valid Identity newIdentity, BindingResult bindingResult, Model model)
	{
		if(bindingResult.hasErrors())
		return "addIdentity";
		identityMapper.insert(newIdentity);
		model.addAttribute("identity", newIdentity);
		return "redirect:/app/index";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String showAddPage(Model model)
	{
		Identity identity = new Identity();
		model.addAttribute("identity", identity);
		return "addIdentity";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String showEditPage(Model model,@PathVariable("id") int id_nv)
	{
		Identity identity = identityMapper.findById(id_nv);
		model.addAttribute("identity", identity);
		return "editIdentity";
	}
@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute @Valid Identity newIdentity, BindingResult bindingResult, Model model)
	{
		if(bindingResult.hasErrors())
			return "editIdentity";
			identityMapper.update(newIdentity);
			model.addAttribute("identity", newIdentity);
			return "redirect:/app/index";
	}
	
	
	// LIST WITH PAGINATION
	@RequestMapping(value= {"/index"}, method = RequestMethod.GET)
	public String indexPage(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("pageSize") Optional<Integer> size) 
	{
		page.ifPresent((p) -> { 
			if(p>0) 
				currentPage = p;
			});
		size.ifPresent((s) -> { 
			if(s>0) 
				pageSize = s;
			});
		List<Identity> list = this.identityMapper.findAll((currentPage-1)*pageSize, pageSize);
		int totalItem = this.identityMapper.countTotalRecords();
		int NumberPages= totalItem/pageSize + ((totalItem%pageSize == 0) ? 0 : 1);
		System.out.println(
				new StringBuilder()
				.append("*************")
				.append(System.getProperty("line.separator"))
				.append("TOTAL RECORDS: ")
				.append(totalItem)
				.append(System.getProperty("line.separator"))
				.append("NUMBER OF PAGE: ")
				.append(NumberPages)
				.append(System.getProperty("line.separator"))
				.append("ITEMS PER PAGE: ")
				.append(pageSize)
				.append(System.getProperty("line.separator"))
				.append("*************")
				.append(System.getProperty("line.separator"))
				.append("CURRENT PAGE: ")
				.append(currentPage));
		model.addAttribute("identities", list);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", NumberPages);
		model.addAttribute("currentPage", currentPage);
		return "index";
	}
