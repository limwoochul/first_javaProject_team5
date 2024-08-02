package shopping;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Map;
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
    private ItemManager itemManager;
    private Map<String, User> userMap;

    public AdminManager(ItemManager itemManager, Map<String, User> userMap) {
    	this.itemManager = itemManager;
    	this.userMap = userMap;
    }
    
    @Override
    public void run() {
        int menu;

        do {
            printMenu();
            menu = nextInt();
            try {
                runMenu(menu);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (menu != 6);
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
                viewAllItems();
                break;
            case 5:
                viewAllUsers();
                break;
            case 6:
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
            "---관리자 메뉴---\n" +
            "1. 상품 등록\n" +
            "2. 상품 삭제\n" +
            "3. 상품 업데이트\n" + 
            "4. 등록된 상품 조회\n" +
            "5. 등록된 회원 조회\n" +
            "6. 이전으로\n" +
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
    	printBar();
        scan.nextLine(); // clear buffer

        String itemName = getItemNameInput();
        if (itemName == null) return;

        String itemPrice = getItemPriceInput();
        if (itemPrice == null) return;

        int itemInventory = getItemInventoryInput();
        if (itemInventory == -1) return;

        int itemNumber = itemManager.getNextItemNumber();
        Item newItem = new Item(itemNumber, itemName, itemPrice);
        newItem.setItemInventory(itemInventory);
        itemManager.addItem(newItem);

        System.out.println("상품이 등록되었습니다: " + newItem);
    }

    private String getItemNameInput() {
        System.out.print("상품 이름 (한글과 영어만 가능): ");
        String itemName = scan.nextLine();

        if (!Pattern.matches("^[a-zA-Z가-힣 ]+$", itemName)) {
            System.out.println("상품 이름은 한글과 영어만 가능합니다.");
            return null;
        }

        return itemName;
    }

    private String getItemPriceInput() {
        System.out.print("상품 가격 (숫자만 가능, 예: 100,000): ");
        String itemPrice = scan.nextLine();
        
        if (!isValidPrice(itemPrice)) {
            System.out.println("상품 가격은 숫자만 가능하며, 쉼표로 단위 구분이 가능합니다.");
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
            return -1;
        }

        return Integer.parseInt(inventoryInput);
    }

    private void deleteItem() {
    	printBar();
        System.out.print("삭제할 상품 번호: ");
        int itemNumber = nextInt();
        Item itemToRemove = itemManager.getItemNumber(itemNumber);
   
        if (itemToRemove != null) {
            itemManager.removeItem(itemToRemove);
            System.out.println("상품이 삭제되었습니다: " + itemToRemove);
        } else {
            System.out.println("해당 상품을 찾을 수 없습니다.");
        }
    }

    private void updateItem() {
    	printBar();
        System.out.print("수정할 상품 번호: ");
        int itemNumber = nextInt();
        Item itemToUpdate = itemManager.getItemNumber(itemNumber);

        if (itemToUpdate == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.");
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
    }
    
    private void viewAllItems() {
        printBar();
        System.out.println("등록된 상품 조회");
        for (Item item : itemManager.getItemList()) {
            System.out.println(item.getItemNumber() + ". " + item.getItemName() + " / " + item.getItemPrice() + "원 / " + item.getItemInventory() + "개");
        }
    }

    private void viewAllUsers() {
        printBar();
        System.out.println("등록된 회원 조회");
        int index = 1;
        for (User user : userMap.values()) {
            System.out.println(index + ". " + user.getId() + " / " + user.getName() + " / " + user.getAddress() + " / " + user.getPhoneNumber());
            index++;
        }
    }

    private void backMenu() {
        System.out.println("이전 메뉴로 돌아갑니다.");
    }

}
