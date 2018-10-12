package com.mybatis.platform.attachment.dao;

import com.mybatis.platform.attachment.entity.Attachment;
import org.springframework.stereotype.Repository;
import com.mybatis.core.orm.common.mapper.BaseMapper;

@Repository
public interface AttachmentMapper extends BaseMapper<Attachment,String> {

}