package pl.agh.kis.soa;

import java.util.List;

public class Survey {
    private String name;
    private String email;
    private int age;
    private String gender;
    private String education;
    private int height;
    private GenderSurveyDetails details;
    private String howMuchMoneyPerMothOptionNumber;
    private String howOftenDoYouBuyClothesOptionNumber;
    private List<String> whatColorsDoYouPreferOptionNumbers;
    private List<String> whatClothesDoYouUsuallyBuy;

    public Survey() {

    }

    public Survey(String name, String email, int age, String gender, String education, int height, GenderSurveyDetails details, String howMuchMoneyPerMothOptionNumber, String howOftenDoYouBuyClothesOptionNumber, List<String> whatColorsDoYouPreferOptionNumbers, List<String> whatClothesDoYouUsuallyBuy) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.education = education;
        this.height = height;
        this.details = details;
        this.howMuchMoneyPerMothOptionNumber = howMuchMoneyPerMothOptionNumber;
        this.howOftenDoYouBuyClothesOptionNumber = howOftenDoYouBuyClothesOptionNumber;
        this.whatColorsDoYouPreferOptionNumbers = whatColorsDoYouPreferOptionNumbers;
        this.whatClothesDoYouUsuallyBuy = whatClothesDoYouUsuallyBuy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GenderSurveyDetails getDetails() {
        return details;
    }

    public void setDetails(GenderSurveyDetails details) {
        this.details = details;
    }

    public String getHowMuchMoneyPerMothOptionNumber() {
        return howMuchMoneyPerMothOptionNumber;
    }

    public void setHowMuchMoneyPerMothOptionNumber(String howMuchMoneyPerMothOptionNumber) {
        this.howMuchMoneyPerMothOptionNumber = howMuchMoneyPerMothOptionNumber;
    }

    public String getHowOftenDoYouBuyClothesOptionNumber() {
        return howOftenDoYouBuyClothesOptionNumber;
    }

    public void setHowOftenDoYouBuyClothesOptionNumber(String howOftenDoYouBuyClothesOptionNumber) {
        this.howOftenDoYouBuyClothesOptionNumber = howOftenDoYouBuyClothesOptionNumber;
    }

    public List<String> getWhatColorsDoYouPreferOptionNumbers() {
        return whatColorsDoYouPreferOptionNumbers;
    }

    public void setWhatColorsDoYouPreferOptionNumbers(List<String> whatColorsDoYouPreferOptionNumbers) {
        this.whatColorsDoYouPreferOptionNumbers = whatColorsDoYouPreferOptionNumbers;
    }

    public List<String> getWhatClothesDoYouUsuallyBuy() {
        return whatClothesDoYouUsuallyBuy;
    }

    public void setWhatClothesDoYouUsuallyBuy(List<String> whatClothesDoYouUsuallyBuy) {
        this.whatClothesDoYouUsuallyBuy = whatClothesDoYouUsuallyBuy;
    }
}
