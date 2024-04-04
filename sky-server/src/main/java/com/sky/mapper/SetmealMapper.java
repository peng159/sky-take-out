package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealMapper {



    @Select("select count(id) from setmeal where category_id=#{id}")
    Integer countByCategoryId(Long id);

    @AutoFill(value = OperationType.UPDATE)
    void update(Setmeal setmeal);

    @AutoFill(value=OperationType.INSERT)
    void insert(Setmeal setmeal);

    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    @Select("select * from setmeal where id=#{id}")
    Setmeal getById(Long id);

    @Delete("delete from setmeal where id=#{setmealId}")
    void deleteById(Long setmealId);
}
