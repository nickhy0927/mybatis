package com.mybatis.platform.attachment.service;

import com.mybatis.core.orm.common.mapper.BaseMapper;
import com.mybatis.core.orm.common.service.BaseService;
import com.mybatis.platform.attachment.dao.AttachmentMapper;
import com.mybatis.platform.attachment.entity.Attachment;

public interface AttachmentService extends BaseService<Attachment, String, AttachmentMapper> {

}
