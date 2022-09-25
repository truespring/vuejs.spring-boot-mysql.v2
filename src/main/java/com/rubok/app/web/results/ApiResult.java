package com.rubok.app.web.results;

import org.springframework.util.Assert;

import java.io.Serial;
import java.util.HashMap;

public class ApiResult extends HashMap<String, Object> {

  @Serial
  private static final long serialVersionUID = 877825499039674411L;

  private static final String MESSAGE_KEY = "message";
  private static final String ERROR_CODE_KEY = "errorReferenceCode";

  public static ApiResult blank() {
    return new ApiResult();
  }

  public static ApiResult message(final String message) {
    Assert.hasText(message, "Parameter `message` must not be blank");

    ApiResult apiResult = new ApiResult();
    apiResult.put(MESSAGE_KEY, message);
    return apiResult;
  }

  public static ApiResult error(final String message, final String errorReferenceCode) {
    Assert.hasText(message, "Parameter `message` must not be blank");
    Assert.hasText(errorReferenceCode, "Parameter `errorReferenceCode` must not be blank");

    ApiResult apiResult = new ApiResult();
    apiResult.put(MESSAGE_KEY, message);
    apiResult.put(ERROR_CODE_KEY, errorReferenceCode);
    return apiResult;
  }

  public ApiResult add(final String key, final Object value) {
    Assert.hasText(key, "Parameter `key` must not be blank");
    Assert.notNull(value, "Parameter `value` must not be null");

    this.put(key, value);
    return this;
  }
}
