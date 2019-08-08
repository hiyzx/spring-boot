package org.zero.elastic.search.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zero.elastic.search.dto.EsEntity;
import org.zero.elastic.search.dto.Student;
import org.zero.elastic.search.util.EsUtil;

/**
 * @author yezhaoxing
 * @date 2019/8/8
 */
@Service
public class StudentService {

    private static final String INDEX = "student";

    @Autowired
    private EsUtil esUtil;

    public void saveOrUpdate(Student student) {
        esUtil.insertOrUpdateOne(INDEX, new EsEntity<>(student.getId(), student));
    }
}
