package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {

    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document;
        try {
            document = getDocument();


            //Получили элемент, у которого есть класс template.
            Element templateOriginal = document.getElementsByClass("template").first();
            //сделаем  копию этого объекта.
            Element copyTemplate = templateOriginal.clone();
            //удалим из нее атрибут "style" и класс "template"
            copyTemplate.removeAttr("style");
            copyTemplate.removeClass("template");

             /*удаляем  все добавленные ранее вакансии. У них единственный класс "vacancy".
                В файле backup.html это одна вакансия - Junior Java Developer.
                Нужно удалить все теги tr, у которых class="vacancy".
                Но тег tr, у которого class="vacancy template", не удалять.
                Использем метод remove.*/
            document.select("tr[class=vacancy]").remove().not("tr[class=vacancy template");

            //Используем этот элемент в качестве шаблона для добавления новой строки в таблицу вакансий

            for (Vacancy vacancy : vacancies) {
                Element localClone = copyTemplate.clone();// клонируем шаблон тега
                localClone.getElementsByClass("city").first().text(vacancy.getCity());
                localClone.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                localClone.getElementsByClass("salary").first().text(vacancy.getSalary());

                Element link =localClone.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                templateOriginal.before(localClone.outerHtml());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        //Возвращаем html код всего документа в качестве результата работы метода.
        return document.html();
    }

    private void updateFile(String content) {
        try(FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
