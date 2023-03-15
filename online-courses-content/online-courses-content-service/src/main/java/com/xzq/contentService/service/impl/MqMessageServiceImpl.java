package com.xzq.contentService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzq.content.model.entity.MqMessage;
import com.xzq.contentService.mapper.MqMessageMapper;
import com.xzq.contentService.service.MqMessageService;
import org.springframework.stereotype.Service;

/**
* @author 谢志强
* @description 针对表【mq_message】的数据库操作Service实现
* @createDate 2023-03-14 23:12:12
*/
@Service
public class MqMessageServiceImpl extends ServiceImpl<MqMessageMapper, MqMessage>
    implements MqMessageService {

}




