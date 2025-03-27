package com.service;

import com.dao.ShoeModelDao;
import com.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ModelManager {

    private static final Logger log = LoggerFactory.getLogger(ModelManager.class);
    private final ShoeModelDao modelDao;
    private final Connection connection;

    // 생성자
    public ModelManager(Connection connection) {
        this.connection = connection;
        this.modelDao = new ShoeModelDao(connection);
    }

    // 모든 사용자 조회
    public List<Model> getAllModels() throws SQLException {
        List<Model> models = modelDao.getAllModels();

        if(models == null) {
            log.error("모델 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return modelDao.getAllModels();
    }


}
