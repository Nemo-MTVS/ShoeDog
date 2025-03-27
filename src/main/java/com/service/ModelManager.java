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

    // 모든 모델 조회
    public List<Model> getAllModels() throws SQLException {
        List<Model> models = modelDao.getAllModels();

        if(models == null) {
            log.error("모델 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return modelDao.getAllModels();
    }


    // ID로 특정 모델 조회
    public Model getModelById(int modelId) throws SQLException {
        Model model = modelDao.getModelById(modelId);

        if (model == null) {
            throw new IllegalArgumentException("해당 ID의 모델을 찾을 수 없습니다.");
        }
        return model;
    }

    // 모델 이름으로 특정 모델 조회
    public Model getModelByName(String modelname) throws SQLException {
        Model model = modelDao.getModelByName(modelname);

        if (model == null) {
            throw new IllegalArgumentException("해당 모델명의 모델을 찾을 수 없습니다.");
        }
        return model;
    }

    // 모델 등록
    public boolean addModel(Model model) throws SQLException {
        // 중복 검사
        List<Model> existingModels = getAllModels();
        for (Model m : existingModels) {
            if (m.getModelname().equals(model.getModelname())) {
                throw new IllegalArgumentException("이미 존재하는 모델입니다.");
            }
        }
        return modelDao.addModel(model);
    }

    // 모델 수정
    public boolean updateModel(Model model) throws SQLException {

        boolean result = modelDao.updateModel(model);
        if (!result) {
            throw new SQLException("수정하는 과정에서 오류가 발생되었습니다.");
        }

        // 3️⃣ 업데이트 수행
        return result;
    }
}
