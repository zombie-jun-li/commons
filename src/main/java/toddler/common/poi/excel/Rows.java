package toddler.common.poi.excel;

import java.util.List;

/**
 * Created by jun.
 */
@SuppressWarnings("PMD.AccessorClassGeneration")
public final class Rows<T> {

    private final List<T> rows;

    private final Class<T> clazz;

    private final int start;

    private final int index;

    private Rows(RowBuilder rowBuilder) {
        this.rows = rowBuilder.rows;
        this.clazz = rowBuilder.clazz;
        this.start = rowBuilder.start;
        this.index = rowBuilder.index;
    }

    public static <T> RowBuilder<T> newBuilder(Class<T> clazz) {
        return new RowBuilder<>(clazz);
    }

    public List<T> getRows() {
        return rows;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public int getStart() {
        return start;
    }

    public int getIndex() {
        return index;
    }

    public static class RowBuilder<T> {

        private List<T> rows;

        private final Class<T> clazz;

        private int start = 0;

        private int index = 0; // sheet index

        public RowBuilder(Class<T> clazz) {
            this.clazz = clazz;
        }

        public RowBuilder<T> rows(List<T> rows) {
            this.rows = rows;
            return this;
        }

        public RowBuilder<T> start(int start) {
            this.start = start;
            return this;
        }

        public RowBuilder<T> index(int index) {
            this.index = index;
            return this;
        }

        public Rows<T> build() {
            return new Rows<>(this);
        }

    }
}
