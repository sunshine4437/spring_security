package com.example.demo.service;

import com.example.demo.dao.AuditDAO;
import com.example.demo.dto.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditDAO auditDAO;

    @Autowired
    public AuditServiceImpl(AuditDAO auditDAO) {
        this.auditDAO = auditDAO;
    }

    @Override
    public int insertAudit(Audit auditDTO) {
        return this.auditDAO.insertAudit(auditDTO);
    }

    @Override
    public List<Audit> selectAuditAll(Audit auditDTO) {
        return null;
    }
}
