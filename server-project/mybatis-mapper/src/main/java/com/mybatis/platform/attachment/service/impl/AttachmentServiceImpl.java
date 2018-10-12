package com.mybatis.platform.attachment.service.impl;

import org.springframework.stereotype.Service;

import com.mybatis.core.orm.common.service.impl.BaseServiceImpl;
import com.mybatis.platform.attachment.dao.AttachmentMapper;
import com.mybatis.platform.attachment.entity.Attachment;
import com.mybatis.platform.attachment.service.AttachmentService;

@Service
public class AttachmentServiceImpl extends BaseServiceImpl<Attachment, String, AttachmentMapper> implements AttachmentService {

}
