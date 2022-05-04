package filtros;

import javax.inject.Inject;

public class PlatformException extends Exception{

    private String message;
    private String errorCode;

    @Inject
    public PlatformException(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public PlatformException(String message, String errorCode, Throwable throwable) {
        super(throwable);
        this.message = message;
        this.errorCode = errorCode;
    }
}
