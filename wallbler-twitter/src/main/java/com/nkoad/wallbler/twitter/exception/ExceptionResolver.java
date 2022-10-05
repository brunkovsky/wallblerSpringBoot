package com.nkoad.wallbler.twitter.exception;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ExceptionResolver extends DefaultHandlerExceptionResolver {

    @SneakyThrows
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("=============");
        log.warn("request: " + request);
        log.warn("ex: " + ex);
        System.out.println("=============");
//        return null;
//        return injectHttpStatusToResponse(response, HttpStatus.valueOf(400), ex.getMessage());
        logger.info("aaaaa", ex);
        response.sendError(400);
        return new ModelAndView();
//        return super.doResolveException(request, response, handler, ex);
    }

    private ModelAndView injectHttpStatusToResponse(HttpServletResponse response, HttpStatus httpStatus, String message) {
        try {
            response.sendError(httpStatus.value(), message);
            return new ModelAndView();
        } catch (IOException e) {
            log.warn("Failed to inject http status to response: {}", e.getMessage());
        }
        return null;
    }

}
