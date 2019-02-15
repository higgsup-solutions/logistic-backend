package com.higgsup.base.service.impl;

import com.higgsup.base.service.ITransactionService;
import com.higgsup.base.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional()
public class TransactionService implements ITransactionService {
}
