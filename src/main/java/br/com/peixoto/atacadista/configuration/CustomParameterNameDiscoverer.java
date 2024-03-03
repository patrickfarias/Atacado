package br.com.peixoto.atacadista.configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.bind.annotation.RequestParam;

public class CustomParameterNameDiscoverer implements ParameterNameDiscoverer {

    @Override
    public String[] getParameterNames(Method method) {
        return parameterNames(method);
    }

    @Override
    public String[] getParameterNames(Constructor<?> constructor) {
        return parameterNames(constructor);
    }

    private String[] parameterNames(Executable executable) {
        final List<String> parameterNames = new ArrayList<>();
        Stream.of(executable.getParameters()).forEach((Parameter param) -> {
            final RequestParam requestParamAnnotation = param.getAnnotation(RequestParam.class);

            final String paramName = requestParamAnnotation == null || StringUtils.isBlank(requestParamAnnotation.name())
                    ? param.getName()
                    : requestParamAnnotation.name();

            parameterNames.add(paramName);
        });
        return parameterNames.toArray(new String[0]);
    }

}