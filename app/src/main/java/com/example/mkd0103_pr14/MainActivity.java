package com.example.mkd0103_pr14;
//https://github.com/b1narygl1tch/kali-linux-pitail-setup
//https://habr.com/ru/articles/535290/
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.style.SuggestionRangeSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText Name;
    EditText Surname;
    TextView TextStepTwo;
    RadioGroup NumberofLessons;
    CheckBox[] Muscles;
    EditText Height;
    EditText Weight;
    RadioGroup Gender;
    RadioGroup ChoiceGender;
    RadioButton FemaleRadioButton;
    RadioButton oncePerDayRadioButton;
    RadioButton threeTimesPerWeekRadioButton;
    RadioButton twoTimesPerWeekRadioButton;
    RadioButton oncePerWeekRadioButton;


    String StrName = "";
    String StrNumberOfLessons = "";
    String StrMuscles = "";
    String StrHeightNWeight = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void AlertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("OK", (dialog, which) -> dialog.cancel());

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void GoToReg(View view){
        setContentView(R.layout.step_one);
        Name = findViewById(R.id.Name);
        Surname = findViewById(R.id.Surname);
    }


    public void GoToTwo(View view){
        String name = Name.getText().toString();
        String surname = Surname.getText().toString();

        if(name.isEmpty() || surname.isEmpty()){
            AlertDialog("Ошибка!", "Заполните все данные");
            return;
        }
        StrName = "Ваше имя - " + name + "\n";

        setContentView(R.layout.step_two);
        TextStepTwo = findViewById(R.id.TextStepTwo);
        NumberofLessons = findViewById(R.id.NumberofLessons);

        oncePerDayRadioButton = findViewById(R.id.oncePerDayRadioButton);
        threeTimesPerWeekRadioButton = findViewById(R.id.threeTimesPerWeekRadioButton);
        twoTimesPerWeekRadioButton = findViewById(R.id.twoTimesPerWeekRadioButton);
        oncePerWeekRadioButton = findViewById(R.id.oncePerWeekRadioButton);

        TextStepTwo.setText("Отлично, "+ name + "\nКак часто вы готовы заниматься спортом?");
    }

    // Переход к третьему шагу регистрации
    public void GoToThree(View view){
        if(NumberofLessons.getCheckedRadioButtonId() == -1){
            AlertDialog("Ошибка!", "Заполните все данные");
            return;
        }

        if(oncePerDayRadioButton.isChecked())
            StrNumberOfLessons = "\nВы выбрали - Раз в день\n";

        if(threeTimesPerWeekRadioButton.isChecked())
            StrNumberOfLessons = "\nВы выбрали - Три раза в неделю\n";

        if(twoTimesPerWeekRadioButton.isChecked())
            StrNumberOfLessons = "\nВы выбрали - Два раза в неделю\n";

        if(oncePerWeekRadioButton.isChecked())
            StrNumberOfLessons = "\nВы выбрали - Раз в неделю\n";

        setContentView(R.layout.step_three);
        Muscles = new CheckBox[4];
        Muscles[0] = findViewById(R.id.Back);
        Muscles[1] = findViewById(R.id.Biceps);
        Muscles[2] = findViewById(R.id.Triceps);
        Muscles[3] = findViewById(R.id.Calf);
    }

    public void GoToFour(View view){
        if(!Muscles[0].isChecked() & !Muscles[1].isChecked() & !Muscles[2].isChecked() & !Muscles[3].isChecked())
        {
            AlertDialog("Ошибка!", "Необходимо выбрать минимум одну позицию.");
            return;
        }

        StrMuscles = "\nВы выбрали - ";

        if(Muscles[0].isChecked())
            StrMuscles += "спину, ";

        if(Muscles[1].isChecked())
            StrMuscles += "бицепсы, ";

        if(Muscles[2].isChecked())
            StrMuscles += "трицепсы, ";

        if(Muscles[3].isChecked())
            StrMuscles += "икроножные мышцы";

        setContentView(R.layout.step_four);

        Weight = findViewById(R.id.Weight);
        Height = findViewById(R.id.Height);
    }

    public void GoToFive(View view) {
        String weightStr = Weight.getText().toString();
        String heightStr = Height.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            AlertDialog("Ошибка!", "Необходимо заполнить все данные.");
            return;
        }

        try {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);
        } catch (NumberFormatException e) {
            AlertDialog("Ошибка!", "Вес и рост должны быть числовыми значениями.");
            return;
        }

        StrHeightNWeight = "\nВаш вес - " + weightStr + "\nВаш рост - " + heightStr;

        setContentView(R.layout.step_five);
        Gender = findViewById(R.id.ChoiceGender);
        ChoiceGender = findViewById(R.id.ChoiceGender);
        FemaleRadioButton = findViewById(R.id.femaleRadioButton);
    }

    public void GoToMain(View view){
        if (Gender.getCheckedRadioButtonId() == -1) {
            AlertDialog("Ошибка!", "Необходимо указать пол.");
            return;
        }

        setContentView(R.layout.main);
        AlertDialog("Итоговая информация:", StrName + StrNumberOfLessons + StrMuscles + StrHeightNWeight);
    }


    public void SkipToTwo(View view){
        setContentView(R.layout.step_two);
        TextStepTwo = findViewById(R.id.TextStepTwo);
        NumberofLessons = findViewById(R.id.NumberofLessons);

        oncePerDayRadioButton = findViewById(R.id.oncePerDayRadioButton);
        threeTimesPerWeekRadioButton = findViewById(R.id.threeTimesPerWeekRadioButton);
        twoTimesPerWeekRadioButton = findViewById(R.id.twoTimesPerWeekRadioButton);
        oncePerWeekRadioButton = findViewById(R.id.oncePerWeekRadioButton);
        TextStepTwo.setText("Отлично\nКак часто вы готовы заниматься спортом?");
    }

    public void SkipToFive(View view){
        setContentView(R.layout.step_five);
        Gender = findViewById(R.id.ChoiceGender);
        ChoiceGender = findViewById(R.id.ChoiceGender);
        FemaleRadioButton = findViewById(R.id.femaleRadioButton);
    }

    public void SkipToMain(View view)
    {
        setContentView(R.layout.main);
        AlertDialog("Итоговая информация:", StrName + StrNumberOfLessons + StrMuscles + StrHeightNWeight);
    }

    //Видео
    public void GoToBack(View view){
        String youtubeUrl = "https://www.youtube.com/watch?v=qBySr6Bt0B8&ab_channel=UtopiaShow";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
        startActivity(browserIntent);
    }

    public void GoToTriceps(View view){
        String youtubeUrl = "https://www.youtube.com/watch?v=example";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
        startActivity(browserIntent);
    }

    public void GoToBiceps(View view){
        String youtubeUrl = "https://www.youtube.com/watch?v=-HGihUGl5zM&ab_channel=SJBody";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
        startActivity(browserIntent);
    }

    public void GoToCalf(View view){
        String youtubeUrl = "https://www.youtube.com/watch?v=9oiitGOZWbA&ab_channel=ВикторБлуд";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
        startActivity(browserIntent);
    }
}
