package cc.chengheng.webflux.函数式自己创建服务器.service;

import cc.chengheng.webflux.函数式自己创建服务器.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    // 一个元素用Mono，多个元素用 Flux

    Mono<User> getUserById(int id);

    Flux<User> getAllUser();

    Mono<Void> saveUserInfo(Mono<User> user);
}
