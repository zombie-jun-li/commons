package toddler.common.poi.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import toddler.common.util.Lists;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jun.
 */
public class WorkbookTest {

    @Test
    public void testWorkbook() throws IOException {
        List<Row> rows = Lists.newArrayList(new Row("jun", "zombie.jun.li@aliyun.com"));
        Sheet sheet = Sheet.newBuilder(Row.class)
                .name("test-sheet")
                .outputHeader(true)
                .rows(rows)
                .build();
        HSSFWorkbook hssfWorkbook = new Workbook().addSheet(sheet).build();

        hssfWorkbook.write(new File(System.getProperty("user.dir") + "/target/sheets.xls"));
    }

    @Test
    public void testWorkbookMultiSheet() throws IOException {
        List<Row> rows = Lists.newArrayList(new Row("jun", "zombie.jun.li@aliyun.com"));
        Sheet sheet = Sheet.newBuilder(Row.class)
                .name("test-sheet")
                .outputHeader(false)
                .rows(rows)
                .build();

        Sheet sheet2 = Sheet.newBuilder(Row.class)
                .name("test-sheet2")
                .outputHeader(true)
                .rows(rows)
                .build();
        HSSFWorkbook hssfWorkbook = new Workbook().addSheet(sheet).addSheet(sheet2).build();

        hssfWorkbook.write(new File(System.getProperty("user.dir") + "/target/multi-sheets.xls"));
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
