package com.example.sagicoupon.ExceptionHandler;

import com.example.sagicoupon.enums.ErrorType;
import com.example.sagicoupon.exceptions.ServerException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler
    @ResponseBody
    public ErrorBean toResponse(Throwable throwable, HttpServletResponse response) {

        if (throwable instanceof ServerException) {

            ServerException appException = (ServerException) throwable;

            ErrorType errorType = appException.getErrorType();
            int errorNumber = errorType.getErrorNumber();
            String errorMessage = errorType.getterOfMessage();
            response.setStatus(errorNumber);

            ErrorBean errorBean = new ErrorBean(errorNumber, errorMessage);
            if (appException.getErrorType().isShowStackTrace()) {
                appException.printStackTrace();
            }

            return errorBean;
        }

        response.setStatus(ErrorType.GENERAL_ERROR.getErrorNumber());

//        String errorMessage = throwable.getMessage();
        ErrorBean errorBean = new ErrorBean(ErrorType.GENERAL_ERROR.getErrorNumber(), ErrorType.GENERAL_ERROR.getterOfMessage());
        throwable.printStackTrace();

        return errorBean;
    }
}