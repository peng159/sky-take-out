package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Insert("insert into employee (name, username, password, phone, sex, id_number, status, create_time, update_time, update_user) " +
            "values" +
            "(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{updateUser})")
    void insert(Employee employee);
//    分页查询
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void update(Employee employee);

    @Select("select * from employee where id=#{id}")
    Employee getById(Long id);
}
