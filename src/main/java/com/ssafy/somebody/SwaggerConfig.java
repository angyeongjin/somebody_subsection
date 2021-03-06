package com.ssafy.somebody;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}
	private Predicate<String> postPaths() {
		return or(regex("/attend/posts.*"), regex("/attend.*"),
		regex("/auction/posts.*"), regex("/auction.*"),
		regex("/member/posts.*"), regex("/member.*"),
		regex("/notice/posts.*"), regex("/notice.*"),
		regex("/feedback/posts.*"), regex("/feedback.*"),
		regex("/pay/posts.*"), regex("/pay.*")
		);
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Some8ody")
				.description("Some8ody - Capacity Auction Site")
				.termsOfServiceUrl("https://edu.ssafy.com")
				.contact(
						new Contact("Ha Seung Hyun", "https://edu.ssafy.com",
								"ssafy@ssafy.com"))
				.license("SSAFY License 0.9")
				.licenseUrl("https://edu.ssafy.com/license.html")
				.version("0.9")
				.build();
	}
}
//http://localhost:8888/swagger-ui.html