package cc.chengheng.webflux.函数式自己创建服务器.handler;

import cc.chengheng.webflux.函数式自己创建服务器.entity.User;
import cc.chengheng.webflux.函数式自己创建服务器.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    // 根据id查询
    public Mono<ServerResponse> getUserById(ServerRequest request) {
        Integer userId = Integer.valueOf(request.pathVariable("id"));

        Mono<User> userMono = this.userService.getUserById(userId);

        // 空值处理
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        // 使用Reactor操作符flatMap
       /* return userMono.flatMap(person -> {
           return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(*//*fromObject(person)*//*).switchIfEmpty(notFound);
        });*/

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userMono, User.class);
    }

    // 查询所有
    public Mono<ServerResponse> getAllusers(ServerRequest serverRequest) {
        Flux<User> users = this.userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, User.class);
    }

    // 添加
    public Mono<ServerResponse> saveUser(ServerRequest request) {
        // 得到user对象
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(this.userService.saveUserInfo(userMono));
    }
}
