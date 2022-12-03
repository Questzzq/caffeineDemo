package com.eric.caffeinedemo.service.configCompare.comparator;

import java.util.List;

/**
 * <p>
 * 比较结果集
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-12-03
 */
public class CompareResult {

    /**
     * 新增的keys
     */
    private List<String> addedKeys;

    /**
     * 删除的keys
     */
    private List<String> deletedKeys;

    /**
     * 变化的信息
     */
    private List<ChangedDetail> changedDetails;

    public List<String> getAddedKeys() {
        return addedKeys;
    }

    public void setAddedKeys(List<String> addedKeys) {
        this.addedKeys = addedKeys;
    }

    public List<String> getDeletedKeys() {
        return deletedKeys;
    }

    public void setDeletedKeys(List<String> deletedKeys) {
        this.deletedKeys = deletedKeys;
    }

    public List<ChangedDetail> getChangedDetails() {
        return changedDetails;
    }

    public void setChangedDetails(List<ChangedDetail> changedDetails) {
        this.changedDetails = changedDetails;
    }

    @Override
    public String toString() {
        return "CompareResult{" +
                "addedKeys=" + addedKeys +
                ", deletedKeys=" + deletedKeys +
                ", changedDetails=" + changedDetails +
                '}';
    }

    /**
     * 变化明细
     */
    public static class ChangedDetail {

        /**
         * 键
         */
        private String key;

        /**
         * 变化之前的值
         */
        private Object valueBefore;

        /**
         * 变化之后的值
         */
        private Object valueAfter;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValueBefore() {
            return valueBefore;
        }

        public void setValueBefore(Object valueBefore) {
            this.valueBefore = valueBefore;
        }

        public Object getValueAfter() {
            return valueAfter;
        }

        public void setValueAfter(Object valueAfter) {
            this.valueAfter = valueAfter;
        }

        @Override
        public String toString() {
            return "ChangedDetail{" +
                    "key='" + key + '\'' +
                    ", valueBefore=" + valueBefore +
                    ", valueAfter=" + valueAfter +
                    '}';
        }
    }

}
