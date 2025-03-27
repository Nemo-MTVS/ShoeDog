package com.view;

import com.model.Colors;
import com.service.ColorService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ColorsView {
    private final ColorService colorService;
    private final Scanner scanner;

    // 생성자
    public ColorsView(Connection connection) {
        this.colorService = new ColorService(connection);
        this.scanner = new Scanner(System.in);
    }

    // 사용자에게 crud 메뉴 제공
    public void showMenu() {
        while (true) {
            System.out.println("\n===== 색상 관리 시스템 =====");
            System.out.println("1. 전체 색상 조회");
            System.out.println("2. 단일 색상 조회 (ID)");
            System.out.println("3. 단일 색상 조회 (색상명)");
            System.out.println("4. 색상 등록");
            System.out.println("0.  뒤로가기");
            System.out.print("선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1 -> getAllColors();
                case 2 -> getColorById();
                case 3 -> getColorByName();
                case 4 -> addColor();
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }


    // 전체 색상 조회
    private void getAllColors() {
        try {
            List<Colors> colors = colorService.getAllColors();

            if (colors.isEmpty()) {
                System.out.println("등록된 색상이 없습니다.");
            } else {
                System.out.println("\n===== 전체 색상 목록 =====");
                colors.forEach(color -> System.out.println(color));
            }
        } catch (SQLException e) {
            System.out.println("색상 목록을 조회하는 중 오류가 발생했습니다.");
        }
    }

    // ID 로 특정 색상 조회
    private void getColorById() {
        System.out.print("조회할 색상 ID를 입력하세요: ");
        int colorId = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 처리

        try {
            Colors color = colorService.getColorById(colorId);
            System.out.println("\n===== 색상 정보 =====");
            System.out.println(color);
        } catch (SQLException e) {
            System.out.println("색상 조회 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 색상명으로 특정 색상 조회
    private void getColorByName() {
        System.out.print("조회할 색상명을 입력하세요: ");
        String colorName = scanner.nextLine();

        try {
            Colors color = colorService.getColorByName(colorName);
            System.out.println("\n===== 색상 정보 =====");
            System.out.println(color);
        } catch (SQLException e) {
            System.out.println("색상 조회 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 색상 등록
    private void addColor() {
        System.out.print("추가할 색상 : ");
        String colorname = scanner.nextLine();

        Colors color = new Colors(colorname);
        try {
            boolean success = colorService.addColor(color);
            if (success) {
                System.out.println("새로운 색상이 성공적으로 등록되었습니다.");
            } else {
                System.out.println("새로운 색상 등록에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("새로운 색상 등록 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}


