package ua.net.forum.customTags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.net.forum.model.Profile;
import ua.net.forum.service.IProfileService;
import ua.net.forum.service.ServiceFactory;

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
