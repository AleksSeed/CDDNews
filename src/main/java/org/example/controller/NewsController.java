package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.news.News;
import org.example.news.NewsType;
import org.example.repository.NewsRepository;
import org.example.repository.NewsTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class NewsController {

    private NewsTypeRepository newsTypeRepository;

    private NewsRepository newsRepository;

    /**    CRUD НОВОСТЕЙ    */
   //Создание новост(и/ей)  (CREATE)
    @PostMapping("/news_add")
    public String addingNews(Model model) {
        try{
            Iterable<NewsType> newsTypes = newsTypeRepository.findAll();
            model.addAttribute("newsTypes", newsTypes);
        }catch(NullPointerException e){}
        return "news_add";
    }

    @PostMapping("/news/add")
    public String addNews(@RequestParam String name, @RequestParam String shortDescript, @RequestParam String fullDescript, @RequestParam Long type, Model model) {
        try {
            NewsType newsType = newsTypeRepository.findById(type).orElseThrow(() -> new NoSuchElementException(""));
            News news = new News(name, shortDescript, fullDescript, newsType);
            newsRepository.save(news);
        }catch(NullPointerException e){}
        return "redirect:/news";
    }

    //Чтение новост(и/ей)   (READ)
    @GetMapping("/")
    public String news(Model model) {
        try {
            Iterable<News> newsList = newsRepository.findAll();
            model.addAttribute("newsList", newsList);
        }catch(NullPointerException e){}
        return "news";
    }

    @GetMapping("/newstypes/{id}/list")//Список определённого типа новостей
    public String newsTypeList(@PathVariable(value = "id") Long id, Model model) {
        NewsType newsType = newsTypeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
        Iterable<News> newsIterable = newsRepository.findAll();
        List<News> newsList = new ArrayList<>();
        for (News news : newsIterable) {
            if (news.getType().equals(newsType)) {
                newsList.add(news);
            }
        }
        model.addAttribute("newsType", newsType);
        model.addAttribute("newsList", newsList);
        return "news_by_type";
    }

    //Обновление новост(и/ей)   (UPDATE)
    @GetMapping("/news/{id}/edit")
    public String editNews(@PathVariable(value = "id") Long id, Model model) {
        try {
            News news = newsRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
            Iterable<NewsType> newsTypes = newsTypeRepository.findAll();
            model.addAttribute("news", news);
            model.addAttribute("newsTypes", newsTypes);
        }catch(NullPointerException e){}
        return "news_edit";
    }

    //Удаление новост(и/ей) (DELETE)
    @PostMapping("/news/{id}/remove")
    public String removeNews(@PathVariable(value = "id") Long id, Model model) {
        try {
            News news = newsRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
            newsRepository.delete(news);
        }catch(NullPointerException e){}
        return "redirect:/news";
    }


    /**    CRUD ТИПОВ НОВОСТЕЙ    */
    //Создание нового типа новостей (CREATE)
    @GetMapping("/newstypes_add")
    public String addingNewsType() {
        return "newstypes_add";
    }

    @PostMapping("/newstypes/add")
    public String addNewsType(@RequestParam String name, @RequestParam String color, Model model) {
        try {
            NewsType newsType = new NewsType(name, color);
            newsTypeRepository.save(newsType);
        }catch(NullPointerException e){e.printStackTrace();}
        return "redirect:/newstypes";
    }

    //Чтение типов новостей (READ)
    @GetMapping("/newstypes")
    public String newsType(Model model) {
        try{
            Iterable<NewsType> newsTypes = newsTypeRepository.findAll();
            model.addAttribute("newsTypes", newsTypes);
        }catch(NullPointerException e){}
        return "newstypes";
    }

    //Обновление типов новостей (UPDATE)
    @GetMapping("/newstypes/{id}/edit")
    public String editNewsType(@PathVariable(value = "id") Long id, Model model) {
        NewsType newsTypes = newsTypeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
        model.addAttribute("newsTypes", newsTypes);
        return "newstypes_edit";
    }

    @PostMapping("/newstypes/{id}/edit")//Сохраниение изменений после редактирование новости
    public String saveNewsType(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String color, Model model) {
        try{
            NewsType newsType = newsTypeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
            newsType.setName(name);
            newsType.setColor(color);
            newsTypeRepository.save(newsType);
        }catch(NullPointerException e){e.printStackTrace();}
        return "redirect:/newstypes";
    }

    //Удаление типа новостей    (DELETE)
    @PostMapping("/newstypes/{id}/remove")
    public String removeNewsType(@PathVariable(value = "id") Long id, Model model) {
        try {
            NewsType newsType = newsTypeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
            newsTypeRepository.delete(newsType);
        }catch(NullPointerException e){}
        return "redirect:/newstypes";
    }


}