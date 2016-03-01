package ua.net.forum.customTags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import ua.net.forum.db.DBQuery;
import ua.net.forum.model.Profile;

public class LoggedTag extends TagSupport {

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        int id = Integer.parseInt(pageContext.getRequest().getParameter("id"));
        Profile p = DBQuery.getProfileById(id);
        if (p != null) {
            try {
                out.print(p.getNickName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SKIP_BODY;
    }

    @Override
    public int doStartTag() throws JspException {
        return SKIP_PAGE;
    }

}
