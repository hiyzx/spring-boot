package org.zero.elastic.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.elastic.search.dto.Student;
import org.zero.elastic.search.repository.StudentService;

/**
 * @author yezhaoxing
 * @since 2018/11/05
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("save")
    public String save(@RequestBody Student student) {
        studentService.saveOrUpdate(student);
        return "success";
    }
}
