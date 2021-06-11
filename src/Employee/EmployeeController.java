package Employee;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import main.SQLConnection;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
// Employee Controller
public class EmployeeController implements Initializable {

    @FXML
    private Rectangle table1;
    @FXML
    private Rectangle table2;
    @FXML
    private Rectangle table3;
    @FXML
    private Rectangle table4;
    @FXML
    private Rectangle table5;
    @FXML
    private Rectangle table6;
    @FXML
    private Rectangle table7;
    @FXML
    private Rectangle table8;
    @FXML
    private Rectangle table9;

    private SQLConnection dc;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new SQLConnection();
    }
}
