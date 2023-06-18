package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.saha.amit.service.RequestHandeler;

@Configuration
public class RouterConfig {
	
	@Autowired
	private RequestHandeler requestHandeler;
	
	@Bean
	public RouterFunction<ServerResponse> seRouterFunction(){
		return RouterFunctions.route()
				.GET("routes/square/{input}",requestHandeler::squareHandler )
				.build();
	}
}
