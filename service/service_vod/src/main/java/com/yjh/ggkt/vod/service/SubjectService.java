package com.yjh.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjh.ggkt.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author yjh
 * @since 2023-02-17
 */
public interface SubjectService extends IService<Subject> {
    //查询下一层课程分类
    List<Subject> selectSubjectList(Long id);

    /**
     * 导出
     * @param response
     */
    void exportData(HttpServletResponse response);

    void importDictData(MultipartFile file);
}
