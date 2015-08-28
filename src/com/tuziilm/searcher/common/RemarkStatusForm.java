package com.tuziilm.searcher.common;

import com.tuziilm.searcher.domain.RemarkStatusId;

/**
 * ����remark, status�ı�����
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
public abstract class RemarkStatusForm<T extends RemarkStatusId> extends RemarkForm<T>{
    protected Byte status;

    @Override
    public T toObj() {
        T t= super.toObj();
        t.setStatus(status);
        return t;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
