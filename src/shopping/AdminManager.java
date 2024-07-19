package shopping;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import program.Program;

// 관리자용 상품관리 클래스
// 1. 상품등록
// 2. 상품삭제
// 3. 상품 업데이트
// 4. 재고 관리 (관리자는 + 가능)
public class AdminManager implements Program {
    private Scanner scan = new Scanner(System.in);
    private List<Item> itemList = new ArrayList<>();
    private int itemNumber = 1; // 상품 번호 초기화

    @Override
    public void run() {
        int menu;

        do {
            printMenu();
            menu = nextInt();
            printBar();
            try {
                runMenu(menu);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (menu != 4);
    }

    @Override
    public void runMenu(int menu) throws Exception {
        switch (menu) {
            case 1:
                insertItem();
                break;
            case 2:
                deleteItem();
                break;
            case 3:
                updateItem();
                break;
            case 4:
                backMenu();
                break;
            default:
                System.out.println("잘못된 메뉴 선택입니다. 다시 선택해주세요.");
                printBar();
                break;
        }
    }

    @Override
    public void printMenu() {
        System.out.print(
            "메뉴\n" +
            "1. 상품 등록\n" +
            "2. 상품 삭제\n" +
            "3. 상품 업데이트\n" +
            "4. 이전으로\n" +
            "메뉴 선택 : "
        );
    }

    public int nextInt() {
        try {
            return scan.nextInt();
        } catch (InputMismatchException e) {
            scan.nextLine();
            return Integer.MIN_VALUE;
        }
    }
    
	public void printBar() {
		System.out.println("--------------");
	}

    private void insertItem() {
        scan.nextLine(); // clear buffer

        String itemName = getItemNameInput();
        if (itemName == null) return;

        String itemPrice = getItemPriceInput();
        if (itemPrice == null) return;

        int itemInventory = getItemInventoryInput();
        if (itemInventory == -1) return;

        Item newItem = new Item(itemNumber, itemName, itemPrice);
        newItem.setItemInventory(itemInventory);
        itemList.add(newItem);
        itemNumber++;

        System.out.println("상품이 등록되었습니다: " + newItem);
        printBar();
    }

    private String getItemNameInput() {
        System.out.print("상품 이름 (한글과 영어만 가능): ");
        String itemName = scan.nextLine();

        if (!Pattern.matches("^[a-zA-Z가-힣 ]+$", itemName)) {
            System.out.println("상품 이름은 한글과 영어만 가능합니다.");
            printBar();
            return null;
        }

        return itemName;
    }

    private String getItemPriceInput() {
        System.out.print("상품 가격 (숫자만 가능, 예: 100,000): ");
        String itemPrice = scan.nextLine();
        
        if (!isValidPrice(itemPrice)) {
            System.out.println("상품 가격은 숫자만 가능하며, 쉼표로 단위 구분이 가능합니다.");
            printBar();
            return null;
        }

        return itemPrice;
    }

    private boolean isValidPrice(String price) {
        try {
            NumberFormat.getNumberInstance(Locale.KOREA).parse(price);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private int getItemInventoryInput() {
        System.out.print("상품 재고 (숫자만 가능): ");
        String inventoryInput = scan.nextLine();

        if (!Pattern.matches("^\\d+$", inventoryInput)) {
            System.out.println("상품 재고는 숫자만 가능합니다.");
            printBar();
            return -1;
        }

        return Integer.parseInt(inventoryInput);
    }

    private void deleteItem() {
        System.out.print("삭제할 상품 번호: ");
        int itemNumber = nextInt();
        Item itemToRemove = null;

        for (Item item : itemList) {
            if (item.getItemNumber() == itemNumber) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            itemList.remove(itemToRemove);
            System.out.println("상품이 삭제되었습니다: " + itemToRemove);
            printBar();
        } else {
            System.out.println("해당 상품을 찾을 수 없습니다.");
            printBar();
        }
    }

    private void updateItem() {
        System.out.print("수정할 상품 번호: ");
        int itemNumber = nextInt();
        Item itemToUpdate = null;

        for (Item item : itemList) {
            if (item.getItemNumber() == itemNumber) {
                itemToUpdate = item;
                break;
            }
        }

        if (itemToUpdate == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.");
            printBar();
            return;
        }

        scan.nextLine(); // clear buffer

        String newName = getItemNameInput();
        if (newName == null) return;

        String newPrice = getItemPriceInput();
        if (newPrice == null) return;

        int newInventory = getItemInventoryInput();
        if (newInventory == -1) return;

        itemToUpdate.setItemName(newName);
        itemToUpdate.setItemPrice(newPrice);
        itemToUpdate.setItemInventory(newInventory);

        System.out.println("상품이 업데이트되었습니다: " + itemToUpdate);
        printBar();
    }

    private void backMenu() {
        System.out.println("이전 메뉴로 돌아갑니다.");
        printBar();
    }

    public static void main(String[] args) {
        AdminManager adminManager = new AdminManager();
        adminManager.run();
    }
}
