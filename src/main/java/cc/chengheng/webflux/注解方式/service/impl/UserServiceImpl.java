package cc.chengheng.webflux.注解方式.service.impl;


import cc.chengheng.webflux.注解方式.entity.User;
import cc.chengheng.webflux.注解方式.service.UserService;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;


@Repository
public class UserServiceImpl implements UserService {

    private final Map<Integer, User> users = new HashMap<>();

    public UserServiceImpl() {
        users.put(1, new User("feng1", "男", 20));
        users.put(2, new User("feng2", "男", 21));
        users.put(3, new User("feng3", "男", 22));
        users.put(4, new User("feng4", "男", 23));
    }

    // 根据id查询
    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(users.get(id));
    }

    // 查询多个用户
    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(users.values());
    }

    // 添加用户
    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        return userMono.doOnNext(person -> {
            // 向map集合里放值
            int id = users.size() + 1;
            users.put(id, person);
        }).thenEmpty(Mono.empty());
    }
}
