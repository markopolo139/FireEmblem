package com.FireEmbelm.FireEmblem.web.error;

import org.springframework.http.HttpStatus;

import java.util.Collection;

public class ApiError {

    public static final String SUGGESTED_ACTION = "Contact with admin";

    public static final String DEFAULT_ERROR = "Something's gone wrong";

    public String suggestedAction = SUGGESTED_ACTION;

    public String errorMessage = DEFAULT_ERROR;

    public HttpStatus httpStatus;

    public Collection<ApiSubError> apiSubErrors;

    public static Builder builder() {
        return new Builder();
    }

    public ApiError(
            String suggestedAction, String errorMessage, HttpStatus httpStatus, Collection<ApiSubError> apiSubErrors
    ) {
        this.suggestedAction = suggestedAction;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.apiSubErrors = apiSubErrors;
    }

    private ApiError() {}

    public static class Builder {
        private ApiError mApiError = new ApiError();

        public Builder setSuggestedAction(String suggestedAction) {
            mApiError.suggestedAction = suggestedAction;
            return this;
        }

        public Builder setErrorMessage(String errorMessage) {
            mApiError.errorMessage = errorMessage;
            return this;
        }

        public Builder setHttpStatus(HttpStatus httpStatus) {
            mApiError.httpStatus = httpStatus;
            return this;
        }

        public Builder setApiSubErrors(Collection<ApiSubError> apiSubErrors) {
            mApiError.apiSubErrors = apiSubErrors;
            return this;
        }

        public ApiError build() {
            return mApiError;
        }
    }
}
