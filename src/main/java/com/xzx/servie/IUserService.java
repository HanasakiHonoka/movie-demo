package com.xzx.servie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzx.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzx
 * @since 2020-07-18
 */
public interface IUserService extends IService<User> {

    User login(User user);

    User getUserByName(String username);
}
