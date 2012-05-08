package net.cakesolutions.progjava.ums.controller;

import net.cakesolutions.progjava.ums.domain.Department;
import net.cakesolutions.progjava.ums.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public String index (final Model model, @RequestParam(value = "q", required = false) String query) {
        model.addAttribute("departments", this.departmentRepository.findAll(new PageRequest(0, 100)).getContent());
        return "department/index";
    }

    @RequestMapping(value = "/departments/add", method = RequestMethod.GET)
    public String add(final Model model) {
        model.addAttribute("department", new Department());
        return "department/edit";
    }

    @RequestMapping(value = "/departments/edit/{id}", method = RequestMethod.GET)
    public String edit(final Model model, @PathVariable Long id) {
        model.addAttribute("department", this.departmentRepository.findOne(id));
        return "department/edit";
    }

    @RequestMapping(value = "/departments/save", method = RequestMethod.POST)
    public String save (@ModelAttribute("department") Department department, BindingResult result) {
        this.departmentRepository.save(department);
        return "redirect:/departments.html";
    }

    @RequestMapping(value = "/departments/populate", method = RequestMethod.GET)
    public String populateDb(final Model model) {
        Collection<Department> departments = prepareDepartments();
        model.addAttribute(departments);
        return "redirect:/departments.html";
    }

    @RequestMapping(value = "/departments/deleteAll", method = RequestMethod.GET)
    public String deleteAll() {
        this.departmentRepository.deleteAll();
        return "redirect:/departments.html";
    }

    private Collection<Department> prepareDepartments() {
        List<Department> result = new ArrayList<Department>();
        Department departmentA = this.departmentRepository.save(new Department("Department A"));
        result.add(departmentA);
        Department departmentB = this.departmentRepository.save(new Department("Department B"));
        result.add(departmentB);
        return result;
    }
}
