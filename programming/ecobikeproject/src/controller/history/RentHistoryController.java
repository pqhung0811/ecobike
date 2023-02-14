package controller.history;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import entities.history.ItemHistory;

public class RentHistoryController {

  @FXML
  private ImageView img;
  @FXML
  private Label bikeName;

  @FXML
  private Label money;

  @FXML
  private Label time;
  private ItemHistory item;

  public void initItem(String name, String t, String m, Image image) {
    bikeName.setText(name);
    time.setText(t);
    money.setText(m);
    img.setImage(image);
  }

  public ItemHistory getItem() {
    return item;
  }

  public void setItem(ItemHistory item) {
    this.item = item;
  }
}


