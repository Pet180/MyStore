package cn.tedu.store.service.exc;

public class UserNameAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserNameAlreadyExistException() {

	}
	public UserNameAlreadyExistException(String message) {
		super(message);
	}
}
