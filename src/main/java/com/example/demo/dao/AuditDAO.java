package com.example.demo.dao;

import com.example.demo.dto.Audit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuditDAO {
    public int insertAudit(Audit audit);

    public List<Audit> selectAuditList(Audit audit);
}
