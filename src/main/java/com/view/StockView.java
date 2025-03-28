package com.view;

import com.model.Model;
import com.model.Stock;
import com.model.StockInfo;
import com.service.ModelService;
import com.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StockView {
    private static final Logger log = LoggerFactory.getLogger(StockView.class);
    private final StockService stockService;
    private final Scanner scanner;

    public StockView(Connection connection) {
        this.stockService = new StockService(connection);
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=====  홈  =>  신발 재고 관리 시스템  =====");
            System.out.println("1. 전체 재고 조회");
            System.out.println("2. 재고 등록");
            System.out.println("3. 항목별 재고 조회");
            System.out.println("0. 종료");
            System.out.print("선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            try {
                switch (choice) {
                    case 1 -> getAllStocks();
                    case 2 -> updateStockQuantity();
                    case 3 -> getStockByItem();
                    case 0 -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
                }
            } catch (SQLException e) {
                System.out.println("데이터베이스 작업 중 오류가 발생했습니다: " + e.getMessage());
                log.error("Database error:", e);
            }
        }
    }

    // 전체 재고 조회
    private void getAllStocks() throws SQLException {
        try {
            List<StockInfo> stocks = stockService.getAllStocks();

            if (stocks.isEmpty()) {
                System.out.println( "등록된 재고가 없습니다.");
            } else {
                System.out.println("\n===== 전체 재고 목록 =====");
                stocks.forEach(stock -> {
                    System.out.println(stock);
                });

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("재고 목록을 조회하는 중 오류가 발생했습니다.");
        }
    }

    // 항목별 재고 조회
    private void getStockByItem() throws SQLException {
        String modelname = getValidModelName();
        String color = getColorInput();
        int size = getSizeInput();

        StockInfo newStock = new StockInfo(modelname, color, size);
        StockInfo existStock = stockService.isStockExist(newStock);

        if (existStock == null) {
            System.out.println("현재 재고 수량은 0 입니다.");
        } else {
            System.out.println("현재 재고 수량은 " + existStock.getQuantity() + " 입니다.");
        }
    }

    // 재고 등록
    private void updateStockQuantity() throws SQLException {
        System.out.println("\n===== 재고 등록 =====");

        String modelname = getValidModelName();
        String color = getColorInput();
        int size = getSizeInput();

        StockInfo newStock = new StockInfo(modelname, color, size);
        StockInfo existStock = stockService.isStockExist(newStock);

        System.out.print("변경할 재고 수량 : ");
        int quantity = scanner.nextInt();
        newStock.setQuantity(quantity);

        if (existStock == null) {
            System.out.println("현재 재고 수량은 0 입니다.");
            stockService.addStock(newStock);
            System.out.println("재고가 성공적으로 등록되었습니다.");
        } else {
            System.out.println("현재 재고 수량은 " + existStock.getQuantity() + " 입니다.");
            stockService.updateStockQuantity(newStock);
            System.out.println("재고가 성공적으로 변경되었습니다.");
        }

    }

    // 모델명 입력과 유효성 검사
    private String getValidModelName() throws SQLException {
        System.out.print("모델 명 : ");
        String modelname = scanner.nextLine();
        while (!stockService.isModelExist(modelname)) {
            System.out.print("존재하지 않는 모델입니다. \n올바른 모델명을 다시 입력해 주세요\n");
            System.out.print("모델 명 : ");
            modelname = scanner.nextLine();
        }
        return modelname;
    }

    // 색상 입력
    private String getColorInput() {
        System.out.print("색상 : ");
        return scanner.nextLine();
    }

    // 사이즈 입력
    private int getSizeInput() {
        System.out.print("사이즈 : ");
        return scanner.nextInt();
    }
}

