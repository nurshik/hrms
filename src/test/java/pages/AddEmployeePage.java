package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {
    @FindBy(xpath ="//input[@name='firstName']")
    public WebElement firstName;
    @FindBy (id ="middleName")
    public WebElement middleName;
    @FindBy (id ="lastName")
    public WebElement lastName;
    @FindBy (id ="employeeId")
    public WebElement employeeId;
    @FindBy(id="photofile")
    public WebElement photograph;
    @FindBy (id ="btnSave")
    public WebElement saveBtn;

    @FindBy (id ="chkLogin")
    public WebElement createLoginCheckBox;

    @FindBy (id ="user_name")
    public WebElement userNameCreate;

    @FindBy (id ="user_password")
    public WebElement passwordCreate;

    @FindBy (id ="re_password")
    public WebElement passwordConfirm;

    public AddEmployeePage (){
        PageFactory.initElements(driver,this);
    }
}

