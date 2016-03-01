package ua.net.forum.customTags;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.net.forum.model.Profile;
import ua.net.forum.model.Section;
import ua.net.forum.service.ISectionService;
import ua.net.forum.service.ServiceFactory;
import ua.net.forum.view.SectionForView;

public class SectionsTag extends SimpleTagSupport {

    private String attrName;

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    @Override
    public void doTag() throws JspException, IOException {
        ServiceFactory factory = ServiceFactory.DEFAULT;
        ISectionService service = factory.getSectionService();
        Collection<Section> sections = service.getAllEntites();

        List<SectionForView> sectionsForView = new ArrayList<SectionForView>();
        for (Section section : sections) {
            int id = section.getId();
            String name = section.getName();
            int topicCount = service.getTopicCount(section);
            int msgCount = service.getMsgCount(section);
            Timestamp lastMsgDate = service.getLastMsgDate(section);
            Profile p = service.getLastMsgProfile(section);
            String lastMsgNickname = null;
            int lastMsgProfileId = 0;
            if (p != null) {
                lastMsgNickname = p.getNickName();
                lastMsgProfileId = p.getId();
            }
            SectionForView sectionForView = new SectionForView(id, name, topicCount, msgCount, lastMsgDate, lastMsgNickname, lastMsgProfileId,section.getProfile().getId(),section.getProfile().getNickName());
            sectionsForView.add(sectionForView);
        }

        getJspContext().setAttribute(attrName, sectionsForView);
    }


}
