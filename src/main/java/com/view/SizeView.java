//package com.view;
//
//import com.model.Size;
//import com.service.SizeService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Scanner;
//
//public class SizeView {
//    private static final Logger log = LoggerFactory.getLogger(SizeView.class);
//    private final SizeService sizeService;
//    private final Scanner scanner;
//
//    // Constructor
//    public SizeView(Connection connection) {
//        this.sizeService = new SizeService(connection);
//        this.scanner = new Scanner(System.in);
//    }
//
//    // CRUD for size
//    public void showMenu() {
//        while (true) {
//            System.out.println("\n===== 사이즈 관리 시스템 =====");
//            System.out.println("1. 전체 사이즈 조회");
//            System.out.println("2. 단일 사이즈 조회 (ID)");
//            System.out.println("3. 단일 사이즈 조회 (원하는 사이즈)");
//            System.out.println("4. 사이즈 등록");
//            System.out.println("0.  뒤로가기");
//            System.out.print("선택하세요: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1 -> getAllSize();
//                case 2 -> getSizeById();
//                case 3 -> getSizeByName();
//                case 4 -> addSize();
//                case 0 -> {
//                    System.out.println("프로그램종료");
//                    return;
//                }
//                default -> System.out.println("잘못된 입력");
//            }
//        }
//    }
//    // 전체 사이즈 조회
//    private void getAllSize() {
//        try {
//            List<Size> size = SizeService.getAllSize();
//
//            if (size.isEmpty()) {
//                System.out.println("등록된 사이즈이 없습니다.");
//            } else {
//                System.out.println("\n===== 전체 사이즈 목록 =====");
//                size.forEach(size -> System.out.println(size));
//            }
//        } catch (SQLException e) {
//            System.out.println("사이즈 목록을 조회하는 중 오류가 발생했습니다.");
//        }
//    }
//    // ID 로 특정 사이즈 조회
//    private void getSizeById() {
//        System.out.print("조회할 사이즈 ID를 입력하세요: ");
//        int sizeId = scanner.nextInt();
//        scanner.nextLine(); // 개행 문자 처리
//
//        try {
//            Size size = sizeService.getSizeById(sizeId);
//            System.out.println("\n===== 사이즈 정보 =====");
//            System.out.println(size);
//        } catch (SQLException e) {
//            System.out.println("사이즈 조회 중 오류가 발생했습니다.");
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // 사이즈명으로 특정 사이즈 조회
//    private void getSizeByName() {
//        System.out.print("조회할 사이즈명을 입력하세요: ");
//        String sizeName = scanner.nextLine();
//
//        try {
//            Size size = sizeService.getSizeByName(sizeName);
//            System.out.println("\n===== 사이즈 정보 =====");
//            System.out.println(size);
//        } catch (SQLException e) {
//            System.out.println("사이즈 조회 중 오류가 발생했습니다.");
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // 새로운 사이즈 등록
//    private void addSize() {
//        System.out.print("추가할 사이즈 : ");
//        String sizename = scanner.nextLine();
//
//        Size size = new Size(sizename);
//        try {
//            boolean success = sizeService.addSize(size);
//            if (success) {
//                System.out.println("새로운 사이즈가 성공적으로 등록되었습니다.");
//            } else {
//                System.out.println("새로운 사이즈 등록에 실패하였습니다.");
//            }
//        } catch (SQLException e) {
//            System.out.println("새로운 사이즈 등록 중 오류가 발생했습니다.");
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//}
