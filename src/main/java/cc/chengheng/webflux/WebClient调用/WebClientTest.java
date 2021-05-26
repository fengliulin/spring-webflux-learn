package cc.chengheng.webflux.WebClient调用;

import cc.chengheng.webflux.函数式自己创建服务器.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class WebClientTest {
    public static void main(String[] args) {
        // 调用服务器地址
        WebClient webClient = WebClient.create("http://127.0.0.1:60077");

        // 根据id查询
        String id = "1";
        User userResult = webClient.get().uri("/users/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class)
                .block();

        System.out.println(userResult.getName());

        // 查询所有
        Flux<User> userFlux = webClient.get().uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(User.class);

        userFlux.map(User::getName)
                .buffer()
                .doOnNext(System.out::println)
                .blockFirst();

        System.out.println();
    }
}
