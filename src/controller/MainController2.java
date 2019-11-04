// ACTION SAVE 
	@RequestMapping(params = "save", value="/multiadd", method = RequestMethod.POST)
	public String saveList(@ModelAttribute @Valid IdentityForm form, BindingResult bindingResult, Model model)
	{
		System.out.println("INSIDE SAVE ACTION");	
		if(bindingResult.hasErrors())
		{
			System.out.println("ERROR TRACE!!!");
			//return "redirect:/app/multiadd";
			return "addidentities";
		}
//			form.getIdentities().forEach((newIdentity) -> {
//				identityMapper.insert(newIdentity);
//			});
		identityMapper.insertList(form.getIdentities());
			//model.addAttribute("identity", identityMapper.findAll());
			return "redirect:/app/index";
	}
	
	// ACTION REMOVE
	//REMOVE AN OBJECT FROM CURRENT LIST
	@RequestMapping(params = "removeItem", value="/multiadd", method = RequestMethod.POST)
	public String removeIdentity(@ModelAttribute IdentityForm form, @RequestParam("removeItem") int idx, Model model)
	{
		System.out.println("INSIDE REMOVE ITEM");
		if(form.getIdentities() != null)
		form.getIdentities().remove(idx);
		model.addAttribute("form", form);
		// render only #list fragment of the page.
		 //return "addidentities::#tbtdb";
		return "addidentities";
	}
	
	// ACTION ADD
		//ADD AN OBJECT FROM CURRENT LIST
		@RequestMapping(params="addItem", value="/multiadd", method = RequestMethod.POST)
		public String addIdentity(@ModelAttribute IdentityForm form, Model model)
		{
			System.out.println("INSIDE ADD ITEM");
			if(form.getIdentities() == null) form.setIdentities(empty);
			form.getIdentities().add(new Identity());
			model.addAttribute("form", form);
			// render only #list fragment of the page.
			//return "addidentities::#tbtdb";
			return "addidentities";
		}
	
}
