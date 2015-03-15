package ui.tools;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import ui.model.UserProfile;
import util.common.constants.IConstants;
import dear.diary.user.model.User;

public class LoginController {

	public static void setUserProfile(HttpSession session, User user) throws Exception{
		UserProfile up = new UserProfile();
		BeanUtils.copyProperties(up, user);
		session.setAttribute(IConstants.USERPROFILE_KEY, up);
	}
	
	public static UserProfile getUserProfile(HttpSession session) {
		return (UserProfile) session.getAttribute(IConstants.USERPROFILE_KEY);
	}
}
