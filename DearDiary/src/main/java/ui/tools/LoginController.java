package ui.tools;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.dear.diary.user.model.User;

import ui.model.UserProfile;
import util.common.constants.IConstants;

public class LoginController {

	public static UserProfile setUserProfile(HttpSession session, User user) throws Exception{
		UserProfile up = new UserProfile();
		BeanUtils.copyProperties(up, user);
		session.setAttribute(IConstants.USERPROFILE_KEY, up);
		return up;
	}
	
	public static UserProfile getUserProfile(HttpSession session) {
		return (UserProfile) session.getAttribute(IConstants.USERPROFILE_KEY);
	}
}
