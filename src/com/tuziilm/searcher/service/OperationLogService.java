package com.tuziilm.searcher.service;

import com.tuziilm.searcher.domain.OperationLog;
import com.tuziilm.searcher.persistence.OperationLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationLogService extends BaseService<OperationLog> {
	@Autowired
	public void setOperationLogMapper(OperationLogMapper operationLogMapper) {
		this.mapper = operationLogMapper;
	}
}
