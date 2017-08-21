package toddler.common.poi.excel;

import java.util.List;

/**
 * Created by jun.
 */
@SuppressWarnings("PMD.AccessorClassGeneration")
public final class Sheet<T> {
    private final String name;

    private final List<T> rows;

    private final Class<T> clazz;

    private boolean outputHeader = true;


    private Sheet(SheetBuilder sheetBuilder) {
        this.name = sheetBuilder.name;
        this.rows = sheetBuilder.rows;
        this.clazz = sheetBuilder.clazz;
        this.outputHeader = sheetBuilder.outputHeader;
    }

    public static <T> SheetBuilder<T> newBuilder(Class<T> clazz) {
        return new SheetBuilder(clazz);
    }

    public String getName() {
        return name;
    }

    public List<T> getRows() {
        return rows;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public boolean isOutputHeader() {
        return outputHeader;
    }

    public static class SheetBuilder<T> {
        private String name;

        private List<T> rows;

        private final Class<T> clazz;

        private boolean outputHeader;

        public SheetBuilder(Class<T> clazz) {
            this.clazz = clazz;
        }

        public SheetBuilder rows(List<T> rows) {
            this.rows = rows;
            return this;
        }

        public SheetBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SheetBuilder outputHeader(boolean outputHeader) {
            this.outputHeader = outputHeader;
            return this;
        }


        public Sheet<T> build() {
            return new Sheet<>(this);
        }
    }
}
