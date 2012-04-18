package com.nextinstruction.springjson.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A custom exception resolver that intercepts ajax exceptions and renders them appropiately.<br/>
 * The default instance will intercept all exceptions thrown from request URI paths of the
 * form:  <br/><br/>
 * <pre>{@code .*&#47;ajax.*}</pre>
 * <p> And return a marshalled Json document using {@link MappingJacksonJsonView} with an error and errorMessage.
 * <p> This behavior can be changed thru {@link #setAjaxView} and {@link #setAjaxUriPattern(String)}.
 * <p> To use this in a spring-mvc app simply create an instance of this bean in your application context.
 * The prescence of the bean will be detected and it's precedence will be above the default HandlerExceptionResolver.
 */
public class AjaxHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
    private View ajaxView = new MappingJacksonJsonView();
    private String ajaxUriPattern = ".*/ajax.*";

    public AjaxHandlerExceptionResolver() {
        super.setOrder(HIGHEST_PRECEDENCE);    // make sure we get invoked first in the resolver chain
    }

    public void setAjaxView(View ajaxView) {
        this.ajaxView = ajaxView;
    }

    public void setAjaxUriPattern(String ajaxUriPattern) {
        this.ajaxUriPattern = ajaxUriPattern;
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (request.getPathInfo().matches(ajaxUriPattern)) {
            return new ModelAndView(ajaxView, ImmutableMap.of("error", "something went wrong"));
        }
        return null;  // per resolver spec returning null allows the next resolver in the chain to be called
    }
}
