package ua.net.forum.customTags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import model.Profile;

public class LoggedTag extends TagSupport {

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		Profile loggedProfile = (Profile) pageContext.getSession().getAttribute("loggedProfile");
		if (loggedProfile != null) {
			try {
				out.print(loggedProfile.getNickName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return SKIP_BODY;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return SKIP_PAGE;
	}
	
}
