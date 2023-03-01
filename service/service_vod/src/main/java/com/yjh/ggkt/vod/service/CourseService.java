package com.yjh.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjh.ggkt.model.vod.Course;
import com.yjh.ggkt.vo.vod.CourseQueryVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yjh
 * @since 2023-02-17
 */
public interface CourseService extends IService<Course> {

    Map<String, Object> findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo);
}
