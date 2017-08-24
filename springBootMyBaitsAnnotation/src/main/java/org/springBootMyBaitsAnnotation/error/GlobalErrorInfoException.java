package org.springBootMyBaitsAnnotation.error;

/***
 * 统一的异常
 * @author Administrator
 *
 */
public class GlobalErrorInfoException extends Exception{

	private ErrorInfoInterface infoInterface;

	public ErrorInfoInterface getInfoInterface() {
		return infoInterface;
	}

	public void setInfoInterface(ErrorInfoInterface infoInterface) {
		this.infoInterface = infoInterface;
	}

	public GlobalErrorInfoException(ErrorInfoInterface infoInterface) {
		this.infoInterface = infoInterface;
	}
	
}
