package pl.agh.kis.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ManagedBean
@SessionScoped
public class SurveyBean implements Serializable{
    private List<Survey> surveys = new LinkedList<>();

    private String actualName;
    private String actualEmail;
    private int actualAge;
    private String actualGender;
    private String actualEducation;
    private int actualHeight;

    private String actualHowMuchMoneyPerMothOptionNumber;
    private String actualHowOftenDoYouBuyClothesOptionNumber;
    private List<String> actualWhatColorsDoYouPreferOptionNumbers;
    private List<String> actualWhatClothesDoYouUsuallyBuy;

    private MaleSurveyDetails maleDetails = new MaleSurveyDetails();
    private FemaleSurveyDetails femaleDetails = new FemaleSurveyDetails();

    public String addNewSurvey() {
        GenderSurveyDetails details;
        if (actualGender.equals("Male"))
            details = maleDetails;
        else
            details = femaleDetails;
        Survey survey = new Survey(actualName, actualEmail, actualAge, actualGender, actualEducation, actualHeight, details, actualHowMuchMoneyPerMothOptionNumber, actualHowOftenDoYouBuyClothesOptionNumber, actualWhatColorsDoYouPreferOptionNumbers, actualWhatClothesDoYouUsuallyBuy);
        surveys.add(survey);
        cleanForm();
        return "index";
    }

    private void cleanForm() {
        actualName = null;
        actualEmail = null;
        actualAge = 0;
        actualGender = null;
        actualEducation = null;
        actualHeight = 0;
        actualHowMuchMoneyPerMothOptionNumber = null;
        actualHowOftenDoYouBuyClothesOptionNumber = null;
        actualWhatColorsDoYouPreferOptionNumbers = null;
        actualWhatClothesDoYouUsuallyBuy = null;
        maleDetails = new MaleSurveyDetails();
        femaleDetails = new FemaleSurveyDetails();
    }

    public void changedGenderListener(ValueChangeEvent e) {
        actualGender = e.getNewValue().toString();
    }

    public boolean checkGender(String gender) {
        if (gender.equals(actualGender))
            return true;
        return false;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getActualEmail() {
        return actualEmail;
    }

    public void setActualEmail(String actualEmail) {
        this.actualEmail = actualEmail;
    }

    public int getActualAge() {
        return actualAge;
    }

    public void setActualAge(int actualAge) {
        this.actualAge = actualAge;
    }

    public String getActualGender() {
        return actualGender;
    }

    public void setActualGender(String actualGender) {
        this.actualGender = actualGender;
    }

    public String getActualEducation() {
        return actualEducation;
    }

    public void setActualEducation(String actualEducation) {
        this.actualEducation = actualEducation;
    }

    public int getActualHeight() {
        return actualHeight;
    }

    public void setActualHeight(int actualHeight) {
        this.actualHeight = actualHeight;
    }

    public MaleSurveyDetails getMaleDetails() {
        return maleDetails;
    }

    public void setMaleDetails(MaleSurveyDetails maleDetails) {
        this.maleDetails = maleDetails;
    }

    public FemaleSurveyDetails getFemaleDetails() {
        return femaleDetails;
    }

    public void setFemaleDetails(FemaleSurveyDetails femaleDetails) {
        this.femaleDetails = femaleDetails;
    }

    public String getActualHowMuchMoneyPerMothOptionNumber() {
        return actualHowMuchMoneyPerMothOptionNumber;
    }

    public void setActualHowMuchMoneyPerMothOptionNumber(String actualHowMuchMoneyPerMothOptionNumber) {
        this.actualHowMuchMoneyPerMothOptionNumber = actualHowMuchMoneyPerMothOptionNumber;
    }

    public String getActualHowOftenDoYouBuyClothesOptionNumber() {
        return actualHowOftenDoYouBuyClothesOptionNumber;
    }

    public void setActualHowOftenDoYouBuyClothesOptionNumber(String actualHowOftenDoYouBuyClothesOptionNumber) {
        this.actualHowOftenDoYouBuyClothesOptionNumber = actualHowOftenDoYouBuyClothesOptionNumber;
    }

    public List<String> getActualWhatColorsDoYouPreferOptionNumbers() {
        return actualWhatColorsDoYouPreferOptionNumbers;
    }

    public void setActualWhatColorsDoYouPreferOptionNumbers(List<String> actualWhatColorsDoYouPreferOptionNumbers) {
        this.actualWhatColorsDoYouPreferOptionNumbers = actualWhatColorsDoYouPreferOptionNumbers;
    }

    public List<String> getActualWhatClothesDoYouUsuallyBuy() {
        return actualWhatClothesDoYouUsuallyBuy;
    }

    public void setActualWhatClothesDoYouUsuallyBuy(List<String> actualWhatClothesDoYouUsuallyBuy) {
        this.actualWhatClothesDoYouUsuallyBuy = actualWhatClothesDoYouUsuallyBuy;
    }
}
