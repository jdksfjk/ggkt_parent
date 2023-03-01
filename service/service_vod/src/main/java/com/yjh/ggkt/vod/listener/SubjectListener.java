package com.yjh.ggkt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yjh.ggkt.model.vod.Subject;
import com.yjh.ggkt.vo.vod.SubjectEeVo;
import com.yjh.ggkt.vod.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {

    @Autowired
    private SubjectMapper dictMapper;
    @Override
    public void invoke(SubjectEeVo subjectEeVo, AnalysisContext analysisContext) {
        //调用方法添加数据库
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectEeVo,subject);
        dictMapper.insert(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
