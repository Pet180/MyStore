package cn.tedu.store.service.exc;

public class PasswordNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public PasswordNotMatchException() {
	}
	public PasswordNotMatchException(String message) {
		super(message);
	}
}
