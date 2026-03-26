package exceptions;

public class EShopException extends RuntimeException {
  public EShopException(String message)
  {
    super(message);
  }

  public EShopException(String message, Throwable cause) {
    super(message, cause);
  }
}

