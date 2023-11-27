
package mainPkg.Controllers.user;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SignUpController implements Initializable {


    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private DatePicker birthdayDP;
    @FXML
    private TextField addressTF;
    @FXML
    private Button signUpButton;
    @FXML
    private ComboBox<?> languageCB;
    @FXML
    private RadioButton maleRB;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton femaleRB;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private PasswordField confirmPasswordTF;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void signUpOnMouseClick(ActionEvent event) {
    }

}
