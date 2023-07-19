import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {

    @BeforeAll
    static void config() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillRegistrationFormTest() {
        open("/automation-practice-form");
        // код для скрытия рекламы и футер
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //вводим значения в строки
        $("#firstName").setValue("Kate");
        $("#lastName").setValue("Hell");
        $("#userEmail").setValue("kate@test.ru");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("0999660235");

        //дата рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("May");
        $(".react-datepicker__year-select").selectOptionContainingText("1998");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();

        //ввод subject
        $("#subjectsContainer input").setValue("Art").pressEnter();

        //выбор хобби
        $("#hobbiesWrapper").$(byText("Music")).click();

        //загрузка изображения
        $("#uploadPicture").uploadFromClasspath("hw1.JPG");

        //ввод текущего адреса
        $("#currentAddress").setValue("Lenina 666");

        //выбор штата и города
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();

        //отправка формы
        $("#submit").click();

        //проверка формы
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Kate Hell"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("kate@test.ru"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("0999660235"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("30 May,1998"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Art"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("hw1.JPG"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Lenina 666"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Noida"));
    }
}