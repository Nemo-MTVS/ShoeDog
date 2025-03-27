package com.view;

import com.model.Model;
import com.service.ModelService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ModelView {
    private final ModelService modelService;
    private final Scanner scanner;

    // 생성자
    public ModelView(Connection connection) {
        this.modelService = new ModelService(connection);
        this.scanner = new Scanner(System.in);
    }

    // 사용자에게 crud 메뉴 제공
    public void showMenu() {
        while (true) {
            System.out.println("\n===== 사용자 관리 시스템 =====");
            System.out.println("1. 전체 모델 조회");
            System.out.println("2. 단일 모델 조회 (ID)");
            System.out.println("3. 단일 모델 조회 (모델명)");
            System.out.println("4. 새로운 신발 모델 등록");
            System.out.println("5. 모델 정보 수정");
            System.out.println("0. 뒤로가기");
            System.out.print("선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1 -> getAllModels();
                case 2 -> getModelById();
                case 3-> getModelByName();
                case 4 -> addModel();
                case 5 -> updateModel();
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
            List<Model> models = modelService.getAllModels();

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

    // ID로 특정 모델 조회
    private void getModelById() {
        System.out.print("조회할 모델 ID를 입력하세요 : ");
        int modelId = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 처리

        try {
            Model model = modelService.getModelById(modelId);
            System.out.println("\n===== 모델 정보 =====");
            System.out.println(model);
        } catch (SQLException e) {
            System.out.println("모델 조회 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 모델 이름으로로 특정 모델 조회
    private void getModelByName() {
        System.out.print("조회할 모델 이름을 입력하세요 : ");
        String modelname = scanner.nextLine();

        try {
            Model model = modelService.getModelByName(modelname);
            System.out.println("\n===== 모델 정보 =====");
            System.out.println(model);
        } catch (SQLException e) {
            System.out.println("모델 조회 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 모델 등록
    private void addModel() {

        System.out.print("추가할 신발의 브랜드 명 : ");
        String brandname = scanner.nextLine();

        System.out.print("추가할 신발의 모델 명 : ");
        String modelname = scanner.nextLine();

        System.out.print("추가할 신발의 가격 : ");
        int price = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 처리

        System.out.print("추가할 신발의 간단한 설명 : ");
        String description = scanner.nextLine();

        Model model = new Model(brandname, modelname, price, description);
        try {
            boolean success = modelService.addModel(model);
            if (success) {
                System.out.println("새로운 신발 모델이 성공적으로 등록되었습니다.");
            } else {
                System.out.println("새로운 신발 모델 등록에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("새로운 신발 모델 등록 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 모델 수정
    private void updateModel() {
        System.out.print("수정할 신발의 모델 이름을 입력하세요 : ");
        String modelname = scanner.nextLine();

        Model newModel = new Model();
        // 모델 이름으로 모델 검색
        try {
            Model model = modelService.getModelByName(modelname);
            newModel.setId(model.getId());
            System.out.println("\n===== 모델 변경 =====");

            System.out.println("새로운 브랜드 명을 입력해주세요. (기존의 브랜드 명을 계속 사용하시려면 0 입력)");
            System.out.print(model.getBrandname());
            System.out.print(" -> ");
            String newbrandname = scanner.nextLine();
            if(newbrandname.equals("0")){newbrandname = model.getBrandname();}
            newModel.setBrandname(newbrandname);

            System.out.println("새로운 모델 명을 입력해주세요. (기존의 모델 명을 계속 사용하시려면 0 입력)");
            System.out.print(model.getModelname());
            System.out.print(" -> ");
            String newmodelname = scanner.nextLine();
            if(newmodelname.equals("0")){newmodelname = model.getModelname();}
            newModel.setModelname(newmodelname);

            System.out.println("새로운 가격을 입력해주세요. (기존의 가격을 계속 사용하시려면 0 입력)");
            System.out.print(model.getListprice());
            System.out.print(" -> ");
            int newprice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리
            if(newprice == 0){newprice = model.getListprice();}
            newModel.setListprice(newprice);

            System.out.println("새로운 신발의 설명을 입력해주세요. (기존의 신발 설명을 계속 사용하시려면 0 입력)");
            System.out.print(model.getDescription());
            System.out.print(" -> ");
            String newdescription = scanner.nextLine();
            if(newdescription.equals("0")){newdescription = model.getDescription();}
            newModel.setDescription(newdescription);

        } catch (SQLException e) {
            System.out.println("모델 조회 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


        try {
            boolean success = modelService.updateModel(newModel);
            if (success) {
                System.out.println("신발 모델 정보가 성공적으로 수정되었습니다.");
            } else {
                System.out.println("신발 모델 정보 수정에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("신발 모델 정보 수정 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
