package com.manufacture.expertservice.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Import(BeanValidatorPluginsConfiguration.class)
@Configuration
public class Config {

    @Bean
    public Docket swaggerConfiguration() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.manufacture.expertservice"))
                .paths(PathSelectors.ant("/error").negate())
                .build()
                .apiInfo(apiInfo());
        docket.useDefaultResponseMessages(false);
        return appendTags(docket);
    }

    private Docket appendTags(Docket docket) {
        return docket.tags(
                new Tag(DescriptionVariables.COMPANY,
                        "Controller used to get, create, update and delete all registered companies"),
                new Tag(DescriptionVariables.EVALUATION,
                        "Controller used to get, create, update and delete all registered evaluations"),
                new Tag(DescriptionVariables.ExpertRequest,
                        "Controller used to get, create, update and delete all sent expert requests"),
                new Tag(DescriptionVariables.ExpertTest,
                        "Controller used to get, create, update and delete all saved expert tests"),
                new Tag(DescriptionVariables.FILES,
                        "Controller used to upload files"),
                new Tag(DescriptionVariables.Order,
                        "Controller used to get, create, update and delete all orders"),
                new Tag(DescriptionVariables.SURVEY, "Controller used to get, create, update and delete all survey data")

        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Microservices API")
                .description("Microservices API")
                .version("1.0")
                .build();
    }
}