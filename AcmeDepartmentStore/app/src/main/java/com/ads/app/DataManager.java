package com.ads.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataManager {
    // Singleton
    private static DataManager _instance = null;

    private DataManager() { }

    public static DataManager getInstance() {
        if(_instance == null) {
            _instance = new DataManager();
            _instance.initializeDepartments();
            _instance.initializePopular();
            _instance.initializeRecommended();
        }
        return _instance;
    }

    // Properties
    private List<Department> Departments = new ArrayList<>();
    private List<Item> PopularItems = new ArrayList<>();
    private List<Item> RecommendedItems = new ArrayList<>();

    // Getters
    public List<String> DepartmentNames() {
        List<String> tempList = new ArrayList<>();
        for(int i = 0; i < Departments.size(); i++) {
            tempList.add(Departments.get(i).Name());
        }
        return tempList;
    }

    public List<Department> Departments() {
        return Departments;
    }

    public List<Item> PopularItems() {
        return PopularItems;
    }

    public List<Item> RecommendedItems() {
        return RecommendedItems;
    }

    // Public Methods
    public List<Item> getDepartmentItems(Department department) {
        return Departments.get(Departments.indexOf(department)).Items();
    }

    // Create In Memory Data Set
    private void initializeDepartments() {
        List<String> departmentNames = new ArrayList<>();
        departmentNames.add("Apparel");
        departmentNames.add("Books");
        departmentNames.add("Electronics");
        departmentNames.add("Grocery");
        departmentNames.add("HBA");
        departmentNames.add("Office");

        for(int i = 0; i < departmentNames.size(); i++) {
            initializeDepartment(departmentNames.get(i));
        }
    }

    private void initializeDepartment(String departmentName) {
        Random rand = new Random();
        List<Item> items = new ArrayList<>();
        for(int i = 0; i < rand.nextInt(5) + 1; i++) {
            items.add(new Item(
                    String.format("%s000%d", departmentName.charAt(0), i),
                    String.format("%s Item 0%d", departmentName, i),
                    rand.nextInt(10) + 0.99,
                    rand.nextBoolean(),
                    rand.nextBoolean()
            ));
        }
        Departments.add(new Department(departmentName, items));
    }

    // Loop through each Item in each Department, if Popular add to PopularItems
    private void initializePopular() {
        for(int i = 0; i < Departments.size(); i++) {
            for(int j = 0; j < Departments.get(i).Items().size(); j++) {
                if(Departments.get(i).Items().get(j).IsPopular()) {
                    PopularItems.add(Departments.get(i).Items().get(j));
                }
            }
        }
    }

    // Loop through each Item in each Department, if Recommended add to RecommendedItems
    private void initializeRecommended() {
        for(int i = 0; i < Departments.size(); i++) {
            for(int j = 0; j < Departments.get(i).Items().size(); j++) {
                if(Departments.get(i).Items().get(j).IsRecommended()) {
                    RecommendedItems.add(Departments.get(i).Items().get(j));
                }
            }
        }
    }
}