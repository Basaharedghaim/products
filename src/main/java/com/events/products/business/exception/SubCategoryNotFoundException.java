package com.events.products.business.exception;

public class SubCategoryNotFoundException extends RuntimeException {
  public SubCategoryNotFoundException(String message) {
    super(message);
  }
}