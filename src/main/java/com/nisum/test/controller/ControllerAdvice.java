package com.nisum.test.controller;

import com.nisum.test.dto.RestResponse;
import com.nisum.test.exception.ResourceNotFoundException;
import com.nisum.test.exception.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {


  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Throwable.class)
  @ResponseBody
  public RestResponse handleException(Throwable exception) {
    return getResponse(exception.getMessage());
  }
  


  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = ResourceNotFoundException.class)
  @ResponseBody
  public RestResponse userBadRequestRuntimeException(ResourceNotFoundException resourceNotFoundException) {
    return getResponse(resourceNotFoundException.getMessage());
  }


  @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler(value = UnprocessableEntityException.class)
  @ResponseBody
  public RestResponse userBadRequestRuntimeException(UnprocessableEntityException unprocessableEntityException) {
    return getResponse(unprocessableEntityException.getMessage());
  }


  private RestResponse getResponse(String message) {
    RestResponse response = new RestResponse();
    response.setMessage(message);
    return response;
  }

}
