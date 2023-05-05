package kr.co.taek.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.taek.beans.UserBean;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean)target;
		
		String beanName = errors.getObjectName();
		/*회원가입 부분에 대해서만 유효성 검사 로그인은 제외 분기처리*/
		if(beanName.equals("joinUserBean")) {
			if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
			
			if(userBean.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}
		}
	}
	
}
