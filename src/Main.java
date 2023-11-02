import BusinessAccess.WinnersBusinessLogic;
import DataAccess.RecipientsImplementation;
import TransferObjects.Recipients;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WinnersBusinessLogic logic = new WinnersBusinessLogic();
        RecipientsImplementation winners = new RecipientsImplementation();
        winners.selectAllWinners();
        add(logic);
        winners.selectAllWinners();
        delete(logic);
        winners.selectAllWinners();

    }

    private static void add(WinnersBusinessLogic logic) {
        //logic = new RecipientsBusinessLogic(new RecipientImplementation());
        Scanner input = new Scanner(System.in);
        String name;
        int year;
        String city;
        String category;
        Recipients recipient = new Recipients();

        System.out.println("Insert a new Recipient");
        System.out.println("Please write the name of the new recipient in the format Lastname; Firstname: ");
        name = input.nextLine();
        System.out.println("Please write the year of the award: ");
        year = input.nextInt();
        input.nextLine();// Cleans up the input stream
        System.out.println("Please write the city: ");
        city = input.nextLine();
        System.out.println("Please write the category of the award: ");
        category = input.nextLine();

        recipient.setName(name);
        recipient.setYear(year);
        recipient.setCity(city);
        recipient.setCategory(category);
        logic.addWinner(recipient);
    }

    private static void delete (WinnersBusinessLogic logic){
        ArrayList<Recipients> recipientsList = logic.storeWinners();
        Recipients recipient = recipientsList.get(recipientsList.size() - 1);
        logic.removeWinner(recipient);
    }
}