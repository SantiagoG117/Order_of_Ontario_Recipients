package DataAccess;

import TransferObjects.Recipients;


import java.util.ArrayList;
import java.util.List;

public interface CRUD {
    ArrayList<Recipients> storeAllInstances();
    Recipients getInstanceByID(int winnerID);
    void addInstance (Recipients winner);
    void updateInstance(Recipients winner);
    void deleteInstance(Recipients winner);
}
