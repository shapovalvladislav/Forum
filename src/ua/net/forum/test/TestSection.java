package ua.net.forum.test;
import org.testng.Assert;
import org.testng.annotations.Test;

import model.*;

public class TestSection {
 
	@Test()
	public void testEmailGenerator() {
 
		ua.net.forum.db.DBQuery.generateSection();
		Section section = ua.net.forum.db.DBQuery.testSection("SectionForTestNG");
		
 
		Assert.assertNotNull(section);
		Assert.assertEquals(section.getName(), "SectionForTestNG");
		Assert.assertEquals(section.getDescription(), "Description");
	
	}
 
}

