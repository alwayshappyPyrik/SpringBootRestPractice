package com.example.springbootrestpractice.config;

import com.example.springbootrestpractice.model.AuthorizationUser;
import com.example.springbootrestpractice.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Objects;

@Configuration
public class UserAuthorizationWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CustomParamResolver());
    }

    private class CustomParamResolver implements HandlerMethodArgumentResolver {

        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            return parameter.hasParameterAnnotation(AuthorizationUser.class);
        }

        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
            User user = (User) webRequest.getAttribute("user", RequestAttributes.SCOPE_REQUEST);
            if (user != null) {
                if (Objects.equals(parameter.getParameterName(), "name")) {
                    return user.getName();
                } else if (Objects.equals(parameter.getParameterName(), "password")) {
                    return user.getPassword();
                }
            }
            return null;
        }
    }
}
