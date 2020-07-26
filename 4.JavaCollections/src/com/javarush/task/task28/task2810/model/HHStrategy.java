package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    int PAGE_VALUE = 0;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        Document document = null;
        try {
            //коннект к закешированной страничке HH используя метод getDocument, нумерация начинается с 0.
            document = getDocument(searchString, PAGE_VALUE);
            while (true) {
                //Получение списка элементов с атрибутом "vacancy-serp__vacancy". Должно быть до 20 вакансий на странице.
                Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                // если данные есть ,то
                if (elements.size() == 0) {
                    PAGE_VALUE = 0;
                    break;
                }
                for (Element element : elements) {
                    if (element != null) {
                        //создаем  вакансию и заполняем  все ее данные, получив данные из текущего элемента.
                        Vacancy vac = new Vacancy();
                        vac.setTitle(element.getElementsByAttributeValueContaining("data-qa", "title").text());
                        vac.setCity(element.getElementsByAttributeValueContaining("data-qa", "address").text());
                        vac.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                        vac.setSiteName(URL_FORMAT);

                        String urlPage = element.getElementsByAttributeValueContaining("data-qa", "title").attr("href");
                        vac.setUrl(urlPage);
                        //urlPage= URL_FORMAT;
                        //Если тег с зарплатой присутствует, то заполнить и поле salary, иначе инициализировать поле пустой строкой.
                        String salary = element.getElementsByAttributeValueContaining("data-qa", "compensation").text();
                        vac.setSalary(salary.length() == 0 ? "" : salary);
                        result.add(vac);

                    }
                }
                ++PAGE_VALUE;
                document = getDocument(searchString, PAGE_VALUE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document doc = null;
        try {
            doc = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
                .referrer("")
                .get();
            String s = doc.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
