package by.epam.kvirykashvili.javalabtask.web.controller;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "action/error";

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(IOException.class)
    public ModelAndView handleNotFound() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exceptionName", "Page not found");
        mav.addObject("exceptionMessage", "Requested page not exist");
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("exceptionName", e.getClass().getName());
        mav.addObject("exceptionMessage", e.getLocalizedMessage());
        mav.addObject("exceptionUrl", request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}