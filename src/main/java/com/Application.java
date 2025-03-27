package com;

import com.config.JDBCConnection;
import com.view.ColorsView;
import com.view.ModelView;
import com.view.StockView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCConnection.getConnection();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== 신발 재고 관리 시스템 =====");
            System.out.println("1. 모델(Model) 관리");
            System.out.println("2. 색상(Color) 관리");
            System.out.println("3. 재고(Stock) 관리");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1 -> startModelManagement(connection);
                case 2 -> startColorManagement(connection);
                case 3 -> startStockManagement(connection);
                case 0 -> {
                    connection.close();
                    System.out.println("🚀 프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("❌ 잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    // 모델 관리
    private static void startModelManagement(Connection connection) {
        ModelView modelView = new ModelView(connection);
        modelView.showMenu();
    }

    // 색상 관리
    private static void startColorManagement(Connection connection) {
        ColorsView colorsView = new ColorsView(connection);
        colorsView.showMenu();
    }

    // 재고 관리
    private static void startStockManagement(Connection connection) {
        StockView stockView = new StockView(connection);
        stockView.showMenu();
    }
}
