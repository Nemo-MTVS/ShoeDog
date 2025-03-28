package com.view;

import com.model.Stock;
import com.model.Model;
import com.model.Colors;

import com.service.StockService;
import com.service.ModelService;
import com.service.ColorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StockView {
    private static final Logger log = LoggerFactory.getLogger(StockView.class);
    private final StockService stockService;
    private final ModelService modelService;
    private final ColorService colorService;
    private final Scanner scanner;

    public StockView(Connection connection) {
        this.stockService = new StockService(connection);
        this.modelService = new ModelService(connection);
        this.colorService = new ColorService(connection);
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n===== 재고 관리 시스템 =====");
            System.out.println("1. 전체 재고 조회");
            System.out.println("2. 재고 등록");
            System.out.println("3. 재고 조회 (ID)");
            System.out.println("4. 재고 수정");
            
            System.out.println("0. 종료");
            System.out.print("선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            try {
                switch (choice) {
                    case 1 -> System.out.print(getAllStocks());
                    case 2 -> addNewStock();
                    case 3 -> System.out.print(getStockById());
                    case 4 -> updateStockQuantity();
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

    private String getAllStocks() throws SQLException {
        List<Stock> stocks = stockService.getAllStocks();

        if (stocks.isEmpty()) {
            return "등록된 재고가 없습니다.";
        } else {
            StringBuilder sb = new StringBuilder("\n===== 전체 재고 목록 =====\n");
            for (Stock stock : stocks) {
                sb.append(stock.toString()).append("\n");
            }
            return sb.toString();
        }
    }

    private String getStockById() throws SQLException {
        System.out.print("\n조회할 재고 ID를 입력하세요: ");
        int stockId = scanner.nextInt();
        
        Stock stock = stockService.getStockById(stockId);
        
        if (stock != null) {
            return String.format("\n===== 재고 정보 =====\n%s\n", stock);
        } else {
            return "해당 ID의 재고를 찾을 수 없습니다.\n";
        }
    }

    private void addNewStock() throws SQLException {
        System.out.println("\n===== 새 재고 등록 =====");
        
        System.out.print("모델 명 : ");
        String modelname = scanner.nextLine();
        int modelId = modelService.getModelIdByName(modelname);
        if (modelId == 0) {
            System.out.println("해당 모델을 찾을 수 없습니다.");
            return;
        }

        System.out.print("색상 : ");
        String color = scanner.nextLine();
        int colorId = colorService.getColorIdByName(color);
        if (colorId == 0) {
            System.out.println("해당 색상을 찾을 수 없습니다.");
            return;
        }

        System.out.print("사이즈 : ");
        int size = scanner.nextInt();
        if (!StockService.isValidSize(size)) {
            System.out.println("유효하지 않은 사이즈입니다. 220부터 300까지 10씩 증가하는 사이즈만 가능합니다.");
            return;
        }

        int stockId = stockService.getStockIdByModelIdColorSize(modelId, colorId, size);
        if(stockId != 0) {
            int quantity = stockService.getStockById(stockId).getQuantity();
            System.out.println("현재 해당 재고는 " + quantity + " 보유 중입니다.");
            return;
        } else {
            System.out.println("해당 재고는 최초로 등록됩니다.");
        }

        System.out.print("재고 수량 : ");
        int quantity = scanner.nextInt();
        
        Stock newStock = new Stock(0, modelId, colorId, size, quantity);
        
        stockService.addStock(newStock);
        System.out.println("재고가 성공적으로 등록되었습니다.");
    }

    private void updateStockQuantity() throws SQLException {
        System.out.print("\n수정할 재고 ID를 입력하세요: ");
        int stockId = scanner.nextInt();
        
        Stock existingStock = stockService.getStockById(stockId);
        if (existingStock == null) {
            System.out.println("해당 ID의 재고를 찾을 수 없습니다.");
            return;
        }

        System.out.println("현재 재고 정보:");
        System.out.println(existingStock);
        
        System.out.print("\n새로운 재고 수량을 입력하세요: ");
        int newQuantity = scanner.nextInt();
        
        if (stockService.updateStockQuantity(stockId, newQuantity)) {
            System.out.println("재고가 성공적으로 수정되었습니다.");
        } else {
            System.out.println("재고 수정에 실패했습니다.");
        }
    }
}
