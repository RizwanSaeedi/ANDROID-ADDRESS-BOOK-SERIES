package com.saeedisoft.myapplication.Datasource;

import com.saeedisoft.myapplication.Models.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static List<ContactModel> contactList;

    public static List<ContactModel> getContactList() {
        if (contactList == null) {

            contactList = new ArrayList<>();
        }
        return contactList;
    }

    public static int getListSize() {
        return getContactList().size();
    }

    public static void addToList(ContactModel model) {
        getContactList().add(model);
    }

    public static List<ContactModel> searchContactModelByName(String inputName) {
        List<ContactModel> dataList = new ArrayList<>();
        for (ContactModel model :
                getContactList()) {
            if (model.getName().toLowerCase().contains(inputName.toLowerCase())) {
                dataList.add(model);
            }
        }
        return dataList;
    }

    public static void deleteByName(String inputName) {
        for (ContactModel model :
                getContactList()) {
            if (model.getName().toLowerCase().equals(inputName.toLowerCase())) {
                getContactList().remove(model);
                break;
            }
        }
    }
}
