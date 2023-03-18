package com.xzq.contentService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzq.content.model.entity.MqMessageHistory;
import com.xzq.contentService.mapper.MqMessageHistoryMapper;
import com.xzq.contentService.service.MqMessageHistoryService;
import org.springframework.stereotype.Service;

/**
* @author 谢志强
* @description 针对表【mq_message_history】的数据库操作Service实现
* @createDate 2023-03-14 23:12:12
*/
@Service
public class MqMessageHistoryServiceImpl extends ServiceImpl<MqMessageHistoryMapper, MqMessageHistory>
    implements MqMessageHistoryService {

}




