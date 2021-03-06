package jp.co.nichiwasystem;

import java.text.DateFormat;
import java.util.Calendar;

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

    @Autowired
    DaysRepository repository;

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

    // 日本語文字化け対策
    @Bean
    public Filter characterEncodingFilter() {
      CharacterEncodingFilter filter = new CharacterEncodingFilter();
      filter.setEncoding("UTF-8");
      filter.setForceEncoding(true);
      return filter;
    }

    @RequestMapping("/days-view")
    public String daysView(Model model) {
      // 登録データ
      Iterable<Days> list = repository.findAll();
      model.addAttribute("results", list);

      // 現在の月
      model.addAttribute("month", getTodayMonth().toString());

      // 現在の日
      model.addAttribute("day", getTodayDay().toString());

      return "days-view";
    }

    @RequestMapping(value="/post", method=RequestMethod.POST)
    public String daysSearch(Model model,
      @RequestParam("month") Integer month,
      @RequestParam("day") Integer day,
      @RequestParam("name") String name,
      @RequestParam("description") String description) {

    	Boolean errFlg = false;

    	if (dateCheck(month, day) == false) {
    		errFlg = true;
    		//エラーメッセージを表示
    		model.addAttribute("date_error", "存在しない日付です");
    	}

    	if (nameCheck(name) == false) {
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

    	Object count = findDataCount(find_month, find_day);
        String countStr = count.toString();
        Integer countInt = new Integer(countStr).intValue();

    	if (countInt < 1) {
    		//エラーメッセージを表示
    		model.addAttribute("count_error", "登録データが見つかりません");
    	} else {
    		model.addAttribute("results", findData(find_month, find_day));
    	}

        // 月
        model.addAttribute("month", find_month.toString());
        // 日
        model.addAttribute("day", find_day.toString());

      return "days-view";
    }

    //日付妥当性チェック
    public static Boolean dateCheck(Integer month, Integer day) {
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
    public static Boolean nameCheck(String name) {
    	if (name == null || name.trim().length() == 0) {
    		return false;
    	} else {
    		return true;
    	}
	}

    // 検索対象データ件数取得
	public Object findDataCount(Integer month, Integer day) {
		return repository.findByMonthAndDay(month, day).size();
	}

	// 検索対象データ取得
	public Object findData(Integer month, Integer day) {
		return repository.findByMonthAndDay(month, day);
	}

	// 本日の月を取得
	public Object getTodayMonth() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MONTH) + 1;
	}

	// 本日の日を取得
	public Object getTodayDay() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_MONTH);
	}
}
