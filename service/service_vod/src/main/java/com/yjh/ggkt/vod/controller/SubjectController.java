package com.yjh.ggkt.vod.controller;


import com.yjh.ggkt.model.vod.Subject;
import com.yjh.ggkt.result.Result;
import com.yjh.ggkt.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author yjh
 * @since 2023-02-17
 */
@Api(tags = "课程分类管理")
@RestController
@RequestMapping(value="/admin/vod/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    //查询下一层课程分类
    //根据parent_id
    @ApiOperation("查询下一层的课程分类")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable Long id) {
        List<Subject> list = subjectService.selectSubjectList(id);
        return Result.ok(list);
    }
    // 数据字典的导出
    @ApiOperation(value="导出")
    @GetMapping(value = "exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }
    //数据字典的导入
    @ApiOperation(value = "导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        subjectService.importDictData(file);
        return Result.ok();
    }

}

