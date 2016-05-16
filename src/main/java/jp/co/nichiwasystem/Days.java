package jp.co.nichiwasystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Days {
    @Id
    @GeneratedValue
    protected Integer id;
    protected Integer month;
    protected Integer day;
    protected String name;
    protected String description;

    public Days() {
      super();
    }

    public Days(Integer month, Integer day,  String name, String description) {

      super();
      this.month = month;
      this.day = day;
      this.name = name;
      this.description = description;
    }

    // for debug
    public String toString() {
      return "[month:" + month + "day:" + day + ", name:" + name + ", description:" + description + "]";
    }
}
