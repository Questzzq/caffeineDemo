package com.eric.caffeinedemo.service.configCompare.comparator;

/**
 * <p>
 * 支持的比较类型
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-12-03
 */
public enum Action {

    DIFF_KEY("diff-key"),
    DIFF_VALUE("diff-value");

    private String value;

    Action(String value) {
        this.value = value;
    }

    public static boolean supports(String action) {
        Action actionType = parseFrom(action);
        return actionType != null;
    }

    public static Action parseFrom(String action) {
        for (Action actionType : Action.values()) {
            if (actionType.getValue().equalsIgnoreCase(action)) {
                return actionType;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
