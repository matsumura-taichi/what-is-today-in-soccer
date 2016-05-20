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

    	Boolean errFlg = false;

    	if (DateCheck(month, day) == false) {
    		errFlg = true;
    		//エラーメッセージを表示
    		model.addAttribute("date_error", "存在しない日付です");
    	}

    	if (NameCheck(name) == false) {
    		errFlg = true;
    		//エラーメッセージを表示
    		model.addAttribute("name_error", "何の日が未入力です");
    	}

    	if (errFlg == false) {
    		//入力内容をDBに登録
    		Days days = new Days(month, day, name, description);
    		repository.saveAndFlush(days);
    	}

		Iterable<Days> list = repository.findAll();
		model.addAttribute("results", list);
        return "days-view";
    }

    @RequestMapping(value="/find", method=RequestMethod.POST)
    public String find(Model model,  @RequestParam("find_month") Integer find_month , @RequestParam("find_day") Integer find_day) {

    	Object count = FindDataCount(find_month, find_day);
        String countStr = count.toString();
        Integer countInt = new Integer(countStr).intValue();

    	if (countInt < 1) {
    		//エラーメッセージを表示
    		model.addAttribute("count_error", "登録データが見つかりません");
    	} else {
    		model.addAttribute("results", FindData(find_month, find_day));
    	}

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

    //何の日かの名称チェック
    public static Boolean NameCheck(String name) {
    	if (name == null || name.trim().length() == 0) {
    		return false;
    	} else {
    		return true;
    	}
	}

    // 検索対象データ件数取得
	public Object FindDataCount(Integer month, Integer day) {
		return repository.findByMonthAndDay(month, day).size();
	}

	// 検索対象データ取得
	public Object FindData(Integer month, Integer day) {
		return repository.findByMonthAndDay(month, day);
	}

}
