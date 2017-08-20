package toddler.common.poi.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.junit.Test;
import toddler.common.util.Lists;

import java.io.File;
import java.io.IOException;

/**
 * Created by jun.
 */
public class WorkbookTest {

    @Test
    public void testWorkbook() throws IOException {
        Sheet sheet = Sheet.newBuilder(Row.class).name("test-sheet")
                .rows(Lists.newArrayList(new Row("jun", "zombie.jun.li@aliyun.com")))
                .build();
        HSSFWorkbook hssfWorkbook = Workbook.newBuilder().addSheet(sheet).build();

        hssfWorkbook.write(new File(System.getProperty("user.dir") + "/target/sheets.xls"));
    }

    public static class Row {
        @Cell(index = 0, title = "name", size = 30)
        private String name;

        @Cell(index = 1, title = "mail", size = 50)
        private String mail;

        public Row(String name, String mail) {
            this.name = name;
            this.mail = mail;
        }
    }
}
