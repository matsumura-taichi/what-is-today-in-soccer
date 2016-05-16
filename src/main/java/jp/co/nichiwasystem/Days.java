package jp.co.nichiwasystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Days {
    @Id
    @GeneratedValue
    protected Integer id;
    protected String date;
    protected String name;
    protected String description;

    public Days() {
      super();
    }

    public Days(String date, String name, String description) {

      super();
      this.date = date;
      this.name = name;
      this.description = description;
    }

    // for debug
    public String toString() {
      return "[date:" + date + ", name:" + name + ", description:" + description + "]";
    }
}
