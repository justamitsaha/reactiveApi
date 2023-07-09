package webFluxDemo.com.saha.amit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.saha.amit.dto.Response;

public class GetSingleResponse extends BaseTest {
	
	@Autowired
	private WebClient webClient;
	
	@Test
	public void blockTest() {
		var response  =this.webClient
		.get()
		.uri("reactiveMath/square/{number}",5)
		.retrieve()
		.bodyToMono(Response.class)
		.block();
		
		System.out.println(response);
	}

}
