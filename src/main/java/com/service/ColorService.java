package com.service;

import com.dao.ColorsDao;
import com.model.Colors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ColorService {

    private static final Logger log = LoggerFactory.getLogger(ModelService.class);
    private final ColorsDao colorsDao;
    private final Connection connection;

    // 생성자
    public ColorService(Connection connection) {
        this.connection = connection;
        this.colorsDao = new ColorsDao(connection);
    }

    // 모든 색상 조회
    public List<Colors> getAllColors() throws SQLException {
        List<Colors> colors = colorsDao.getAllColors();

        if(colors == null) {
            log.error("색상 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }
        return colorsDao.getAllColors();
    }

    // id로 특정 색상 조회
    public Colors getColorById(int colorId) throws SQLException {
        Colors color = colorsDao.getColorById(colorId);

        if (color == null) {
            throw new IllegalArgumentException("해당 ID의 색상을 찾을 수 없습니다.");
        }
        return color;
    }

    // 색상명으로 특정 색상 조회
    public Colors getColorByName(String colorName) throws SQLException {
        Colors color = colorsDao.getColorByName(colorName);

        if (color == null) {
            throw new IllegalArgumentException("해당 색상명의 색상을 찾을 수 없습니다.");
        }
        return color;
    }

    public int getColorIdByName(String colorName) throws SQLException {
        int colorId = 0;
        Colors color = getColorByName(colorName);
        return colorId = color.getColor_id();
    }


    // 색상 추가
    public boolean addColor(Colors color) throws SQLException {
        // 중복 검사
        List<Colors> existingColors = getAllColors();
        for (Colors c : existingColors) {
            if (c.getColor_name().equals(color.getColor_name())) {
                throw new IllegalArgumentException("이미 존재하는 색상입니다.");
            }
        }
        return colorsDao.addColor(color);
    }
}