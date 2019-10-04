import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DepartmentNewsTest {

    DepartmentNews departmentNews;

    @Before
    public void initial() {
        DepartmentNews departmentNews = new DepartmentNews("PM launches 2nd Eisten for Africa summit",  "bfosma bbrfgrtgrg");
    }

    @Test
    public void Announcement_instiateCorrectly() {
        assertEquals(true, departmentNews instanceof DepartmentNews);
    }

    @Test
    public void getHeadline_theTitleisGivenCorrectly_string() {
        assertEquals("PM launched 2nd Eistern for Africa summit", departmentNews.getHeadline());
    }



    @Test
    public void contentisFilledCorrectly_string() {
        assertEquals("bfdssd", departmentNews.getDevelop());
    }

    @Test
    public void equals_returnTrueIfPropertiesAreSame_true() {
        DepartmentNews departmentNews1 = new DepartmentNews("PM launches 2nd Eisten for Africa summit",  "bfosma bbrfgrtgrg");
        assertEquals(departmentNews1, departmentNews.equals(departmentNews1));

    }

    @Test
    public void saveTheNewsInDatabase() {
        departmentNews.save();
        assertEquals(true,News.all().equals(departmentNews));
    }


    @Test
    public void deletesNews() {
        departmentNews.save();
        departmentNews.delete();
        assertEquals(0, departmentNews);
    }
}
