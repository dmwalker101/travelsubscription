package demo.rest;

import javax.servlet.http.HttpServletResponse;
 
  
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import demo.model.RESTAPIErrorInfo;
import demo.exception.HTTP400Exception;
import demo.exception.HTTP404Exception;

/**
 * This class is used as a super class to be extended by all REST API
 * "controllers".
 */
public abstract class AbstractRestHandler   {

	  
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HTTP400Exception.class)
	public @ResponseBody RESTAPIErrorInfo handleDataStoreException(HTTP400Exception ex, WebRequest request,
			HttpServletResponse response) {
	 
		return new RESTAPIErrorInfo(ex, "Bad Request.");
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(HTTP404Exception.class)
	public @ResponseBody RESTAPIErrorInfo handleResourceNotFoundException(HTTP404Exception ex, WebRequest request,
			HttpServletResponse response) {
	 
		return new RESTAPIErrorInfo(ex, "Sorry system cannot find it");
	}

 

	public static <T> T checkResourceFound(final T resource) {
		if (resource == null) {
			throw new HTTP404Exception("resource not found");
		}
		return resource;
	}

}
