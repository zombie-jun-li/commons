package toddler.common.poi.excel;

import org.junit.Test;
import toddler.common.util.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by jun.
 */
public class WorkbookTest {

    @Test
    public void testWorkbook() throws IOException {
        List<Row> rows = Lists.newArrayList(new Row("jun", "zombie.jun.li@aliyun.com"));
        Rows sheet = Rows.newBuilder(Row.class)
                .rows(rows)
                .start(1)
                .build();
        String filename = this.getClass().getResource("/poi.excel/sheet.xls").getFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir") + "/target/sheet.xls"))) {
            new Workbook(new FileInputStream(filename)).addRows(sheet).addRows(sheet).write(fileOutputStream);
        }
    }

    @Test
    public void testWorkbookMultiSheet() throws IOException {
        List<Row> rows = Lists.newArrayList(new Row("jun", "zombie.jun.li@aliyun.com"));
        Rows sheet = Rows.newBuilder(Row.class)
                .rows(rows)
                .start(1)
                .index(0)
                .build();

        Rows sheet2 = Rows.newBuilder(Row.class)
                .rows(rows)
                .start(1)
                .index(1)
                .build();
        String filename = this.getClass().getResource("/poi.excel/multi-sheet.xls").getFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir") + "/target/multi-sheet.xls"))) {
            new Workbook(new FileInputStream(filename)).addRows(sheet).addRows(sheet2).write(fileOutputStream);
        }
    }


    public static class Row {
        @Cell(index = 0, title = "name")
        private String name;

        @Cell(index = 1, title = "mail")
        private String mail;

        public Row(String name, String mail) {
            this.name = name;
            this.mail = mail;
        }
    }
}
