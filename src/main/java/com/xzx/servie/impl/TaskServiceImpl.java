package com.xzx.servie.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.entity.Task;
import com.xzx.mapper.TaskMapper;
import com.xzx.servie.ITaskService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzx
 * @since 2020-09-09
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
