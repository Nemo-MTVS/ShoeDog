package com.view;

import com.model.Model;
import com.service.ModelManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ModelView {
    private final ModelManager modelManager;
    private final Scanner scanner;

    // 생성자
    public ModelView(Connection connection) {
        this.modelManager = new ModelManager(connection);
        this.scanner = new Scanner(System.in);
    }

    // 사용자에게 crud 메뉴 제공
    public void showMenu() {
        while (true) {
            System.out.println("\n===== 사용자 관리 시스템 =====");
            System.out.println("1. 전체 모델 조회");
            System.out.println("2.  등록");
            System.out.println("3.  조회 (ID)");
            System.out.println("4.  수정");
            System.out.println("5.  삭제");
            System.out.println("0. 종료");
            System.out.print("선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1 -> getAllModels();
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }


    // 전체 모델 조회
    private void getAllModels() {
        try {
            List<Model> models = modelManager.getAllModels();

            if (models.isEmpty()) {
                System.out.println("등록된 모델이 없습니다.");
            } else {
                System.out.println("\n===== 전체 사용자 목록 =====");
                models.forEach(model -> System.out.println(model));
            }
        } catch (SQLException e) {
            System.out.println("모델 목록을 조회하는 중 오류가 발생했습니다.");
        }
    }
}
