package jp.co.nichiwasystem;

import java.text.DateFormat;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.CharacterEncodingFilter;

@Controller
@EnableAutoConfiguration
public class SearchController
{
//	private final Integer month;
//	private final Integer day;
//
//	public void MonthAndDate(Integer month, Integer day) {
//		this.month = month;
//		this.day = day;
//	}

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello, Spring Boot Sample Application!";
    }

    // an entry point
    public static void main( String[] args )
    {
        SpringApplication.run(SearchController.class, args);
    }

    @RequestMapping("/search")
    public String search(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "search";
    }

    // input formを表示
    @RequestMapping("/input")
    public String input() {
        return "input"; // input form
    }

    // inputフォームから受け取ってhello.htmlへ
    @RequestMapping("/send")
    public String send(Model model, @RequestParam("name") String name) {
      model.addAttribute("name", name);
      return "search";    // View file is templates/hello.html
    }

    // 日本語文字化け対策
    @Bean
    public Filter characterEncodingFilter() {
      CharacterEncodingFilter filter = new CharacterEncodingFilter();
      filter.setEncoding("UTF-8");
      filter.setForceEncoding(true);
      return filter;
    }

    /*
     * 日付
     *
     */

    @Autowired
    DaysRepository repository;

    @RequestMapping("/days-view")
    public String daysView(Model model) {
      Iterable<Days> list = repository.findAll();
      model.addAttribute("results", list);
      return "days-view";
    }

    @RequestMapping(value="/post", method=RequestMethod.POST)
    public String daysSearch(Model model,
      @RequestParam("month") Integer month,
      @RequestParam("day") Integer day,
      @RequestParam("name") String name,
      @RequestParam("description") String description) {

    	if (DateCheck(month, day) == false) {
    		//バリデートエラーメッセージを表示

    	}

    	Days days = new Days(month, day, name, description);
        repository.saveAndFlush(days);
        Iterable<Days> list = repository.findAll();
        model.addAttribute("results", list);
        return "days-view";
    }

    //日付妥当性チェック
    public static Boolean DateCheck(Integer month, Integer day) {
	    DateFormat format = DateFormat.getDateInstance();
	    format.setLenient(false);
	    try {
	        format.parse("2016" + "/" + String.format("%1$02d", month) + "/" + String.format("%1$02d", day));
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}


    /*
     * 人物
     *
     */

    /*    @Autowired
    PersonRepository repository;

    @RequestMapping("/person-view")
    public String personView(Model model) {
      Iterable<Person> list = repository.findAll();
      model.addAttribute("results", list);
      return "person-view";
    }

    @RequestMapping(value="/post", method=RequestMethod.POST)
    public String personSearch(Model model,
      @RequestParam("name") String name,
      @RequestParam("tel") String tel,
      @RequestParam("mail") String mail,
      @RequestParam("description") String description) {

        Person person = new Person(name, tel, mail, description);
        repository.saveAndFlush(person);
        Iterable<Person> list = repository.findAll();
        model.addAttribute("results", list);
        return "person-view";
    }

    @RequestMapping(value="/find", method=RequestMethod.POST)
    public String find(Model model,  @RequestParam("category") String category , @RequestParam("str") String str) {

      Iterable<Person> list = null;

      if (category.equals("name")) {
        list = repository.findByName(str);
      }
      else if (category.equals("tel")) {
        list = repository.findByTel(str);
      }
      else if (category.equals("mail")) {
        list = repository.findByMail(str);
      }
      else if (category.equals("description")) {
        list = repository.findByDescription(str);
      }
      else {
        list = null;
      }

      model.addAttribute("results", list);
      return "person-view";
    }
    */

}
