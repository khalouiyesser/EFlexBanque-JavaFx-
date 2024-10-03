package Entities;

public class Notification {
  private int id;
  private int product_id;
  private String content;
  private String date;

  public Notification(int id, int product_id, String content, String date) {
    this.id = id;
    this.product_id = product_id;
    this.content = content;
    this.date = date;
  }

  public Notification() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getProduct_id() {
    return product_id;
  }

  public void setProduct_id(int product_id) {
    this.product_id = product_id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "Notification [id=" + id + ", product_id=" + product_id + ", content=" + content + ", date=" + date + "]";
  }
}
