package meetingrooms;

import org.springframework.dao.EmptyResultDataAccessException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MeetingRoomsController {

    private Scanner sc = new Scanner(System.in);
    private MeetingRoomsService service = new MeetingRoomsService(new MariaDbMeetingRoomsRepository());

    public static void main(String[] args) {
        MeetingRoomsController controller = new MeetingRoomsController();
        controller.init();
    }

    public void init() {
        service.deleteAll();
        System.out.println("Mennyi tárgyalót akar felvenni?");
        int numberOfMeetingRooms = Integer.parseInt(sc.nextLine());
        String name;
        int length;
        int width;
        for (int i = 0; i < numberOfMeetingRooms; i++) {
            System.out.println("Adja meg a(z) " + (i + 1) + ". tárgyaló nevét");
            name = sc.nextLine();
            System.out.println("Adja meg a(z) " + (i + 1) + ". tárgyaló hosszúságát méterben");
            length = Integer.parseInt(sc.nextLine());
            System.out.println("Adja meg a(z) " + (i + 1) + ". tárgyaló szélességét méterben");
            width = Integer.parseInt(sc.nextLine());

            service.save(name, length, width);
        }
        printMenu();
    }

    public void printMenu() {
        System.out.println("1. Tárgyalók sorrendben\n" +
                "2. Tárgyalók visszafele sorrendben\n" +
                "3. Minden második tárgyaló\n" +
                "4. Területek\n" +
                "5. Keresés pontos név alapján\n" +
                "6. Keresés névtöredék alapján\n" +
                "7. Keresés terület alapján\n" +
                "8. Kilépés");
        runMenu();
    }

    public void runMenu() {
        System.out.printf("Adja meg a választott menüpont számát:");
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (
                NumberFormatException ex) {
            System.out.println("Csak számokat írjon be");
            cont();
        }
        switch (choice) {
            case 1 -> System.out.println(service.meetingRoomsByABC());
            case 2 -> System.out.println(service.meetingRoomsReversed());
            case 3 -> System.out.println(service.meetingRoomsEverySecond());
            case 4 -> System.out.println(service.meetingRoomsByArea());
            case 5 -> {
                System.out.println("Írja be a keresendő pontos nevet");
                String name = sc.nextLine();
                try {
                    System.out.println(service.exactSearch(name));
                } catch (IllegalArgumentException | EmptyResultDataAccessException e){
                    System.out.println("No result with term: " + name);
                }
            }
            case 6 -> {
                System.out.println("Írja be a keresendő kifejezést");
                String part = sc.nextLine();
                System.out.println(service.partSearch(part));
            }
            case 7 -> {
                System.out.println("Írja be a keresendő méretet");
                int area = Integer.parseInt(sc.nextLine());
                System.out.println(service.searchByArea(area));
            }
            case 8 -> System.exit(0);
            default -> printMenu();
        }
        cont();
    }

    private void cont() {
        System.out.println("Folytatáshoz nyomja meg az ENTER-t");
        sc.nextLine();
        printMenu();
    }


}
