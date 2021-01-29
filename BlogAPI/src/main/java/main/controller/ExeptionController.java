package main.controller;

import main.api.response.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExeptionController {


        @ExceptionHandler(MaxUploadSizeExceededException.class)
        public ResponseEntity handleMaxSizeException(
                MaxUploadSizeExceededException exc, WebRequest request) {

            ResultResponse responseEr = new ResultResponse();
            Map<String,Object> errors = new HashMap<>();
            errors.put("image","Размер файла больше 5 МБ");
            responseEr.setErrors(errors);
            return new ResponseEntity<>(responseEr, HttpStatus.BAD_REQUEST);
        }

}
