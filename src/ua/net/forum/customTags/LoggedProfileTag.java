package ua.net.forum.customTags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import exceptions.ServiceException;
import service.IProfileService;
import service.ISectionService;
import service.ServiceFactory;
import ua.net.forum.db.DBQuery;
import model.Profile;

public class LoggedProfileTag extends SimpleTagSupport {

	private int id;
	
	public void setProfileId(int id) {
		this.id = id;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		IProfileService profileService = ServiceFactory.DEFAULT.getProfileService();
		Profile p = profileService.getEntityById(id);
		getJspContext().setAttribute("profile", p);
	}
	
}
