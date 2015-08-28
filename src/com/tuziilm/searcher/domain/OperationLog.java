package com.tuziilm.searcher.domain;

import com.tuziilm.searcher.common.LoginContext;
import com.tuziilm.searcher.common.OperationLogType;

public class OperationLog extends Id {
    private Integer userId;
    private Integer type;
    private String msg;

    public static OperationLog valueOf(OperationLogType operationLogType, String msg) {
        OperationLog operationLog = new OperationLog();
        operationLog.type = operationLogType.getValue();
        operationLog.msg = msg;
        operationLog.userId= LoginContext.getUid();
        return operationLog;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
