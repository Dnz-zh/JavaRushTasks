package com.javarush.task.task36.task3601;

import java.util.List;

public class Controller {

    public Model model = new Model();

    public Controller() {
    }

    public List<String> onDataListShow() {
        return model.getStringDataList();
    }

}
