package toddler.common.poi.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import toddler.common.util.Lists;
import toddler.common.util.Reflects;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Workbook {

    private final List<Rows> sheets = Lists.newArrayList();

    private final InputStream stream;

    public Workbook(InputStream stream) {
        this.stream = stream;
    }

    public Workbook addRows(Rows sheet) {
        sheets.add(sheet);
        return this;
    }

    public void write(OutputStream stream) {
        try (HSSFWorkbook workbook = new HSSFWorkbook(this.stream)) {
            sheets.stream().forEach(rows -> buildSheet(workbook, rows));
            workbook.write(stream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void buildSheet(HSSFWorkbook workbook, Rows rows) {
        HSSFSheet hssfSheet = workbook.getSheetAt(rows.getIndex());
        IntStream.range(0, rows.getRows().size())
                .forEach(index -> buildSheetRow(hssfSheet, index + rows.getStart(), rows.getRows().get(index)));
    }

    private void buildSheetRow(HSSFSheet hssfSheet, int rowNumber, Object data) {
        HSSFRow hssfRow = hssfSheet.createRow(rowNumber);
        Arrays.stream(data.getClass().getDeclaredFields())
                .forEach(field -> buildSheetCellContent(hssfRow, field, data));
    }

    private void buildSheetCellContent(HSSFRow hssfRow, Field field, Object data) {
        Cell cell = field.getAnnotation(Cell.class);
        HSSFCell hssfCell = hssfRow.createCell(cell.index());
        hssfCell.setCellValue(String.valueOf(Reflects.getField(field, data)));
    }
}
