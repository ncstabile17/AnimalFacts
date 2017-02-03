package nickinc.listapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class InfoActivity extends AppCompatActivity {

    String [] animals = {"Octopus", "Crab", "Platypus"};
    ArrayList<Fact> octFacts = new ArrayList<>();
    ArrayList<Fact> crabFacts = new ArrayList<>();
    ArrayList<Fact> platFacts = new ArrayList<>();
//    ArrayList<Fact> slothFacts = new ArrayList<>();
//    ArrayList<Fact> blobFacts = new ArrayList<>();

    Animal crab;
    Animal octopus;
    Animal platypus;
//    Animal sloth;
//    Animal blobfish;

    TextView timerText;
    TextView correctText;
    TextView questionText;
    TextView scoreText;
    Button playAgain;
    Button trueButton;
    Button falseButton;
    Button startButton;
    TextView infoText;

    CountDownTimer timer;

    int pos;
    int totalQ;
    int score;

    boolean start;

    Fact currentFact;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        pos = intent.getIntExtra("Location", -1);

        TextView nameText = (TextView) findViewById(R.id.animalName);
        timerText = (TextView) findViewById(R.id.timerText);
        correctText = (TextView) findViewById(R.id.correctText);
        questionText = (TextView) findViewById(R.id.questionText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        infoText = (TextView) findViewById(R.id.infoText);
        trueButton = (Button) findViewById(R.id.trueButton);
        falseButton = (Button) findViewById(R.id.falseButton);
        playAgain = (Button) findViewById(R.id.playAgain);
        startButton = (Button) findViewById(R.id.startButton);

        playAgain.setVisibility(View.INVISIBLE);
        questionText.setVisibility(View.INVISIBLE);
        trueButton.setVisibility(View.INVISIBLE);
        falseButton.setVisibility(View.INVISIBLE);


        nameText.setText("Name: " + animals[pos]);

        String name = animals[pos].toLowerCase();

        ImageView imageView = (ImageView) findViewById(R.id.animalImage);
        int resId = getResources().getIdentifier(name, "drawable", getPackageName());
        imageView.setImageResource(resId);

        trueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        playAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playAgain();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { startGame();}
        });

        makeFacts();
        crab = new Animal("Crab", crabFacts.size());
        platypus = new Animal("Platypus", platFacts.size());
//        sloth = new Animal("Sloth", slothFacts.size());
//        blobfish = new Animal("Blobfish", blobFacts.size());
        octopus = new Animal("Octopus", octFacts.size());

        octopus.setFacts(octFacts);
        crab.setFacts(crabFacts);
        platypus.setFacts(platFacts);
//        sloth.setFacts(slothFacts);
//        blobfish.setFacts(blobFacts);




    }//onCreate

    public void questionTimer() {
        start = true;
        timerText.setText("60S");
        correctText.setVisibility(View.VISIBLE);
        timer = new CountDownTimer(60100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(""+ (millisUntilFinished/1000)+"S");
            }
            @Override
            public void onFinish() {
                // resultTextView.setText(""+correct+"/"+total_problems);
                correctText.setText("Done");
                timerText.setText("0S");
                start = false;
                correctText.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                trueButton.setVisibility(View.INVISIBLE);
                falseButton.setVisibility(View.INVISIBLE);
                questionText.setText("Time's Up! Click the button to play again!");
            }
        }.start();
    }//questionTimer

    public void generateFact() {

        Random rand = new Random();
        if (pos == 0) {
            if (!(octopus.facts.isEmpty())) {
                currentFact = octopus.getFact();
                questionText.setText(currentFact.fact);
                octopus.facts.remove(currentFact);
            }
            else {
                questionText.setText("You've answered all the questions!");
                trueButton.setVisibility(View.INVISIBLE);
                falseButton.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                timer.cancel();

            }
        }

        else if (pos == 1) {
            if (!(crab.facts.isEmpty())) {
                currentFact = crab.getFact();
                questionText.setText(currentFact.fact);
                crab.facts.remove(currentFact);
            }
            else {
                questionText.setText("You've answered all the questions!");
                trueButton.setVisibility(View.INVISIBLE);
                falseButton.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                timer.cancel();

            }
        }

        else if (pos == 2) {
            if (!(platypus.facts.isEmpty())) {
                currentFact = platypus.getFact();
                questionText.setText(currentFact.fact);
                platypus.facts.remove(currentFact);
            }
            else {
                questionText.setText("You've answered all the questions!");
                trueButton.setVisibility(View.INVISIBLE);
                falseButton.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                timer.cancel();

            }
        }


    } //generateFact

    public void checkAnswer(boolean bool) {
        if (start) {
            totalQ++;
            boolean boolCheck = currentFact.torf;
            if (boolCheck == bool) {
                score++;
                correctText.setText("Correct");
                scoreText.setText("" + score + "/" + totalQ);
            } else {
                correctText.setText("Incorrect");
                scoreText.setText("" + score + "/" + totalQ);
            }
            generateFact();
        }

    }//checkAnswer

    public void makeFacts() {
        octFacts.clear();
        octFacts.add(new Fact("Octopuses have 5 hearts", false));
        octFacts.add(new Fact("Octopuses die soon after mating", true));
        octFacts.add(new Fact("Octopuses are the first invertebrates to be seen using tools, such as using coconut shells to hide from potential predators", true));
        octFacts.add(new Fact("Octopuses have only been found in the Pacific and Indian Oceans", false));
        octFacts.add(new Fact("Octopuses reach full size just 3 days after birth", false));
        octFacts.add(new Fact("Octopuses communicate with each other using their ink to write patterns in the water", false));
        octFacts.add(new Fact("There are around 300 recognized octopus species, which is over one-third of the total number of known cephalopod species", true));
        octFacts.add(new Fact("Octupuses have no internal or external skeletons", true));
        octFacts.add(new Fact("Octopuses have been used by the military to infiltrate enemy submarines", false));
        octFacts.add(new Fact("Octopuses have 12 tentacles", false));
        octFacts.add(new Fact("When frightened octopuses shoot poison darts at their enemies", false));
        octFacts.add(new Fact("You've never seen an octopus like this", true));

        crabFacts.clear();
        crabFacts.add(new Fact("Crabs also use their pincers to comfort their young", false));
        crabFacts.add(new Fact("When crabs die, their families bury them in the sand", false));
        crabFacts.add(new Fact("The collective term for a group of crabs is a cast", true));
        crabFacts.add(new Fact("Crabs are decapods, or crustaceans with 10 limbs", true));
        crabFacts.add(new Fact("When I was 7 I got pinched by a crab on the toe when I was playing in the ocean", true));
        crabFacts.add(new Fact("Crabs will often seek out humans to pinch them because they find it enjoyable", false));
        crabFacts.add(new Fact("Crabs were the first animals discovered to have no noses", false));
        crabFacts.add(new Fact("The largest crab in the world is the giant Japanese Spider Crab, which can measure up to 13 feet across", true));
        crabFacts.add(new Fact("Horseshoe crabs are not crabs at all. They are not even crustaceans", true));
        crabFacts.add(new Fact("Crabs communicate by flapping their pincers or drumming their claws", true));
        crabFacts.add(new Fact("Crabs are really just the same thing as lobsters, but restaurants convince you otherwise so they can charge more for lobster", false));
        crabFacts.add(new Fact("Crabs have eyes that are set on eyestalks. Eyestalks can move in different directions and allow a crab to see all around", true));

        platFacts.clear();
        platFacts.add(new Fact("Platypuses are one of only two mammals that lay eggs", true));
        platFacts.add(new Fact("Platypus eggs are a delicacy in many countries and have led to platypuses becoming an endangered species", false));
        platFacts.add(new Fact("The platypus is only found in eastern Australia in small rivers and streams within the states of Queensland, New South Wales, Victoria and Tasmania", true));
        platFacts.add(new Fact("Platypuses are known for being extremely aggressive and attacking any humans that get within striking distance", false));
        platFacts.add(new Fact("The back foot ankle spur of a male platypus contains a venom that is powerful enough to kill small animals", true));
        platFacts.add(new Fact("The diet of platypuses consists mainly of insects and different water plants", false));
        platFacts.add(new Fact("The platypus finds food by feel and by detecting electrical signals", true));
        platFacts.add(new Fact("Platypuses are closely related and believed to be descendants of the ancient megalodon", false));
        platFacts.add(new Fact("The platypus is an excellent swimmer, diving under water on average for around 30 seconds to forage for food before coming up for air", true));
        platFacts.add(new Fact("Platypuses' only natural predator is the crocodile", false));
        platFacts.add(new Fact("When descriptions, drawings and live specimens of platypus were first taken back for study by British scientists many believed the animal was a hoax", true));
        platFacts.add(new Fact("Platypuses are able to breathe underwater and on land through the use of specialized lung/gill hybrids", false));


    }

    public void playAgain() {
        score = 0;
        totalQ = 0;
        start = true;
        scoreText.setText("0/0");
        correctText.setText("");
        timer.cancel();
        playAgain.setVisibility(View.INVISIBLE);

        makeFacts();
        octopus.setFacts(octFacts);
        crab.setFacts(crabFacts);
        platypus.setFacts(platFacts);

        trueButton.setVisibility(View.VISIBLE);
        falseButton.setVisibility(View.VISIBLE);
        generateFact();
        questionTimer();

    }

    public void startGame() {
        trueButton.setVisibility(View.VISIBLE);
        falseButton.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        infoText.setVisibility(View.INVISIBLE);
        questionTimer();
        generateFact();
        questionText.setVisibility(View.VISIBLE);
    }
}
