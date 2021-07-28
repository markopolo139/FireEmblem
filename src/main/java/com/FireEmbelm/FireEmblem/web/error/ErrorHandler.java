package com.FireEmbelm.FireEmblem.web.error;

import com.FireEmbelm.FireEmblem.app.exceptions.CharacterAlreadyMovedException;
import com.FireEmbelm.FireEmblem.business.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private HttpServletRequest mHttpServletRequest;

    private ResponseEntity<Object> convertToResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.httpStatus);
    }

    @ExceptionHandler(CharacterLevelException.class)
    public ResponseEntity<Object> characterLevelExceptionHandler(CharacterLevelException exception) {
        ApiError apiError = ApiError.builder()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .setSuggestedAction("Select other character, which have good level")
                .build();

        return convertToResponseEntity(apiError);
    }

    @ExceptionHandler(EquipmentLimitException.class)
    public ResponseEntity<Object> equipmentLimitExceptionHandler(EquipmentLimitException exception) {
        ApiError apiError = ApiError.builder()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .setSuggestedAction("Select other character, which have space in inventory (less than 6 item)")
                .build();

        return convertToResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidEquipmentException.class)
    public ResponseEntity<Object> invalidEquipmentExceptionHandler(InvalidEquipmentException exception) {
        ApiError apiError = ApiError.builder()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .setSuggestedAction("Select valid for action item from equipment")
                .build();

        return convertToResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidRankException.class)
    public ResponseEntity<Object> invalidRankExceptionHandler(InvalidRankException exception) {
        ApiError apiError = ApiError.builder()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .setSuggestedAction("Select valid rank")
                .build();

        return convertToResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidSpotException.class)
    public ResponseEntity<Object> invalidSpotExceptionHandler(InvalidSpotException exception) {
        ApiError apiError = ApiError.builder()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .setSuggestedAction("Select valid spot, depending on situation")
                .build();

        return convertToResponseEntity(apiError);
    }

    @ExceptionHandler(NoWeaponException.class)
    public ResponseEntity<Object> noWeaponExceptionHandler(NoWeaponException exception) {
        ApiError apiError = ApiError.builder()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .setSuggestedAction("Select character, that have weapon equipped")
                .build();

        return convertToResponseEntity(apiError);
    }

    @ExceptionHandler(OutOfRangeException.class)
    public ResponseEntity<Object> outOfRangeExceptionHandler(OutOfRangeException exception) {
        ApiError apiError = ApiError.builder()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .setSuggestedAction("Select spot in range of selected action (attack or heal)")
                .build();

        return convertToResponseEntity(apiError);
    }

    @ExceptionHandler(PromoteException.class)
    public ResponseEntity<Object> promoteExceptionHandler(PromoteException exception) {
        ApiError apiError = ApiError.builder()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .setSuggestedAction("Select character that has not promoted to higher class")
                .build();

        return convertToResponseEntity(apiError);
    }

    @ExceptionHandler(TooSmallAmountOfMoneyException.class)
    public ResponseEntity<Object> tooSmallAmountOfMoneyExceptionHandler(TooSmallAmountOfMoneyException exception) {
        ApiError apiError = ApiError.builder()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .setSuggestedAction("Select item you can afford or quit shop")
                .build();

        return convertToResponseEntity(apiError);
    }

    @ExceptionHandler(CharacterAlreadyMovedException.class)
    public ResponseEntity<Object> characterAlreadyMovedExceptionHandler(CharacterAlreadyMovedException exception) {
        ApiError apiError = ApiError.builder()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .setSuggestedAction("Select character that has not moved yet, or start new turn")
                .build();

        return convertToResponseEntity(apiError);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<Object> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException exception) {
        ApiError apiError = ApiError.builder()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .setSuggestedAction("Select item in array bound")
                .build();

        return convertToResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException exception) {
        List<ApiSubError> apiSubErrors = exception.getConstraintViolations().stream()
                .map(i -> new ApiSubError(
                                "Check the rejected value",
                                "Validation failed for " + i.getInvalidValue() + " - " + i.getMessage()
                        )
                ).collect(Collectors.toList());

        ApiError apiError = ApiError.builder().setApiSubErrors(apiSubErrors)
                .setErrorMessage("Validation failed")
                .setSuggestedAction("Check sub errors")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .build();

        return convertToResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {

        List<ApiSubError> apiSubErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(i -> new ApiSubError(
                        "Check the rejected value",
                        "Validation failed for " + i.getRejectedValue() + " - " + i.getDefaultMessage()
                        )
                ).collect(Collectors.toList());

        ApiError apiError = ApiError.builder().setApiSubErrors(apiSubErrors)
                .setErrorMessage("Validation failed")
                .setSuggestedAction("Check sub errors")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .build();

        return convertToResponseEntity(apiError);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {

        ApiError apiError = ApiError.builder()
                .setHttpStatus(status)
                .setErrorMessage(ex.getMessage())
                .setSuggestedAction("Correct errors below")
                .build();

        return convertToResponseEntity(apiError);
    }
}
