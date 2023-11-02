package BusinessAccess;

import DataAccess.CRUD;
import DataAccess.RecipientsImplementation;
import TransferObjects.Recipients;

import java.util.ArrayList;

public class WinnersBusinessLogic {
    private CRUD instance = null;

    //? Construction injection
    public WinnersBusinessLogic() {
        instance = new RecipientsImplementation();
    }

    public void addWinner(Recipients recipient){
        instance.addInstance(recipient);
    }

    public void removeWinner(Recipients recipient){
        instance.deleteInstance(recipient);
    }

    public ArrayList<Recipients> storeWinners (){
        return instance.storeAllInstances();
    }
}
