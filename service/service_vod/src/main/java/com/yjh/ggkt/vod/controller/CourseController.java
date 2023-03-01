package com.yjh.ggkt.vod.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjh.ggkt.model.vod.Course;
import com.yjh.ggkt.result.Result;
import com.yjh.ggkt.vo.vod.CourseQueryVo;
import com.yjh.ggkt.vod.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author yjh
 * @since 2023-02-17
 */
@Api(tags = "课程管理接口")
@RestController
@RequestMapping(value="/admin/vod/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result courseList(@ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
                            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit,
                            @ApiParam(name = "courseVo", value = "查询对象", required = false) CourseQueryVo courseQueryVo)
    {
        Page<Course> pageParam = new Page<>(page, limit);
        Map<String,Object> map = courseService.findPage(pageParam, courseQueryVo);
        return Result.ok(map);
    }
}

