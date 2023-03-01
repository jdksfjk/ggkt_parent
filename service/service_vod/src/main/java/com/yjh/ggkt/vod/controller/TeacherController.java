package com.yjh.ggkt.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjh.ggkt.model.vod.Teacher;
import com.yjh.ggkt.result.Result;
import com.yjh.ggkt.vo.vod.TeacherQueryVo;
import com.yjh.ggkt.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author yjh
 * @since 2023-02-14
 */
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping(value="/admin/vod/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //1.查询所有讲师
    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher()
    {
        // 调用Service中的方法
        List<Teacher> list = teacherService.list();
        return Result.ok(list).message("查询数据成功");
    }

    //2 逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name="id",value = "ID",required = true) @PathVariable Long id)
    {
        boolean isSuccess = teacherService.removeById(id);
        if(isSuccess)
        {
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    //3 条件查询并分页
    @ApiOperation("条件查询分页")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@ApiParam(name = "page")@PathVariable Long current,
                           @PathVariable Long limit,
                           @RequestBody(required = false) TeacherQueryVo teacherQueryVo)
    {
        //创建page对象，传递当前页和每页记录数
        Page<Teacher> pageParam = new Page<>(current, limit);
        if(teacherQueryVo==null) //查询全部
        {
            IPage<Teacher> pageModel = teacherService.page(pageParam,null);
            return Result.ok(pageModel);

        }else {
            //获取条件值
            String name = teacherQueryVo.getName();//讲师名称
            Integer level = teacherQueryVo.getLevel();//讲师级别
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();//开始时间
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();//结束时间
            //封装条件
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            if(!StringUtils.isEmpty(name)) {
                wrapper.like("name",name);
            }
            if(!StringUtils.isEmpty(level)) {
                wrapper.eq("level",level);
            }
            if(!StringUtils.isEmpty(joinDateBegin)) {
                wrapper.ge("join_date",joinDateBegin);
            }
            if(!StringUtils.isEmpty(joinDateEnd)) {
                wrapper.le("join_date",joinDateEnd);
            }
            //调用方法得到分页查询结果
            IPage<Teacher> pageModel = teacherService.page(pageParam, wrapper);
            return Result.ok(pageModel);
        }

    }

    //4、添加讲师
    @ApiOperation("添加讲师")
    @PostMapping("saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher)
    {
        boolean isSuccess = teacherService.save(teacher);
        if(isSuccess)
        {
            return Result.ok(null);
        }
        else {
            return Result.fail(null);
        }
    }

    //5、根据id查询讲师
    @ApiOperation(value = "根据id查询")
    @GetMapping("getTeacher/{id}")
    public Result get(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    //6、根据id修改讲师
    @ApiOperation(value = "修改讲师")
    @PutMapping("update")
    public Result updateById(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.updateById(teacher);
        if(isSuccess)
        {
            return Result.ok(null);
        }
        else {
            return Result.fail(null);
        }
    }

    //7、批量删除
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean isSuccess = teacherService.removeByIds(idList);
        if(isSuccess)
        {
            return Result.ok(null);
        }
        else {
            return Result.fail(null);
        }
    }




}

