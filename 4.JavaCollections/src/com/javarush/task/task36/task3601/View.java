package com.javarush.task.task36.task3601;

public class View {

    private Controller controller = new Controller();

    public View() {
    }

    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }

}
