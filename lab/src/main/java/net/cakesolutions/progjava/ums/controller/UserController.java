package net.cakesolutions.progjava.ums.controller;

import net.cakesolutions.progjava.ums.domain.User;
import net.cakesolutions.progjava.ums.repository.DepartmentRepository;
import net.cakesolutions.progjava.ums.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author anirvanchakraborty
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String index (final Model model, @RequestParam(value = "q", required = false) String query) {
        Page<User> userPage;
        if (StringUtils.hasLength(query)) {
            userPage = this.userRepository.findByFullNameLike(query, new PageRequest(0, 100));
            model.addAttribute("q", query);
        } else {
            userPage = this.userRepository.findAll(new PageRequest(0, 100));
        }
        model.addAttribute("users", userPage.getContent());
        return "user/index";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String add (final Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("departments", this.departmentRepository.findAll(new PageRequest(0, 100)).getContent());
        return "user/edit";
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public String edit (final Model model, @PathVariable Long id) {
        model.addAttribute("user", this.userRepository.findOne(id));
        model.addAttribute("departments", this.departmentRepository.findAll(new PageRequest(0, 100)).getContent());
        return "user/edit";
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public String save (@ModelAttribute("user") User user, BindingResult result) {
        this.userRepository.save(user);
        return "redirect:/users.html";
    }

    @RequestMapping(value = "/users/delete/{username}", method = RequestMethod.DELETE)
    public String delete (@PathVariable String username) {
        // code her
        return "redirect:/users.html";
    }

	@RequestMapping(value = "/users/populate", method = RequestMethod.GET)
	public String populateDb(final Model model) {
		Collection<User> users = prepareUsers();
		model.addAttribute(users);
		return "user/index";
	}

	private Collection<User> prepareUsers() {
		// code here
		return new ArrayList<User>();
	}
}
