package toddler.common.poi.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import toddler.common.util.Lists;
import toddler.common.util.Reflects;
import toddler.common.util.Strings;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Created by jun.
 */
public class Workbook {

    private static final int FIRST_ROW_INDEX = 0;

    private final List<Sheet> sheets = Lists.newArrayList();

    public Workbook addSheet(Sheet sheet) {
        sheets.add(sheet);
        return this;
    }

    public HSSFWorkbook build() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        sheets.stream().forEach(sheet -> buildSheet(workbook, sheet));
        return workbook;
    }

    private void buildSheet(HSSFWorkbook workbook, Sheet sheet) {
        HSSFSheet hssfSheet = Strings.isNull(sheet.getName()) ? workbook.createSheet() : workbook.createSheet(sheet.getName());
        int skipRows = 0;
        if (sheet.isOutputHeader()) {
            buildSheetCellHeader(workbook, hssfSheet, hssfSheet.createRow(0), sheet.getClazz());
            skipRows++;
        }
        if (!Optional.ofNullable(sheet.getRows()).isPresent()) {
            return;
        }
        final int finalSkipRows = skipRows;
        IntStream.range(0, sheet.getRows().size())
                .forEach(idx -> buildSheetRow(hssfSheet, idx + finalSkipRows, sheet.getRows().get(idx)));
    }

    private void buildSheetRow(HSSFSheet hssfSheet, int rowNumber, Object data) {
        HSSFRow hssfRow = hssfSheet.createRow(rowNumber);
        Arrays.stream(data.getClass().getDeclaredFields())
                .forEach(field -> buildSheetCellContent(hssfSheet, hssfRow, field, data));
    }

    private void buildSheetCellHeader(HSSFWorkbook workbook, HSSFSheet hssfSheet, HSSFRow hssfRow, Class<?> dataClass) {
        Field[] fields = dataClass.getDeclaredFields();
        IntStream.range(0, fields.length)
                .forEach(index -> {
                    Cell cell = fields[index].getAnnotation(Cell.class);
                    hssfRow.createCell(cell.index());
                    HSSFCell hssfCell = hssfRow.createCell(cell.index());
                    hssfCell.setCellValue(cell.title());
                    HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
                    hssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    hssfCellStyle.setFillForegroundColor(cell.background().getIndex());
                    hssfCell.setCellStyle(hssfCellStyle);
                    buildSheetStyle(hssfSheet, hssfRow, cell);
                });
    }

    private void buildSheetCellContent(HSSFSheet hssfSheet, HSSFRow hssfRow, Field field, Object data) {
        Cell cell = field.getAnnotation(Cell.class);
        HSSFCell hssfCell = hssfRow.createCell(cell.index());
        hssfCell.setCellValue(String.valueOf(Reflects.getField(field, data)));
        buildSheetStyle(hssfSheet, hssfRow, cell);
    }

    private void buildSheetStyle(HSSFSheet hssfSheet, HSSFRow hssfRow, Cell cell) {
        if (FIRST_ROW_INDEX != hssfRow.getRowNum()) return;
        hssfSheet.setColumnWidth(cell.index(), cell.size() * 256);
    }
}
