

//Labwork #3.1 Butenko Vladyslav IO-72


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    private long startTime;
    private long endTime;

    @Override
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);

        Button calcButton = (Button) findViewById(R.id.calcButtonl1);

        final EditText numbEditText = (EditText) findViewById(R.id.numbEditText);
        final TextView resTextView = (TextView) findViewById(R.id.resTextViewl1);
        final TextView popUpView = (TextView) findViewById(R.id.popUpView);

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogBuilder builder = new AlertDialog.Builder(Main.this)
                builder.setCancelable(true);
                builder.setTitle("Total Execution Time");
                try {
                    int number = Integer.parseInt(numbEditText.getText().toString());
                    if (isPrime(number)) resTextView.setText("Prime");
                    else if (number % 2 == 0) resTextView.setText("Even");
                    else {
                        startTime = System.nanoTime();
                        int resArray[] = calculateFerma(number);
                        endTime = (System.nanoTime() - startTime)/1_000;
                        builder.setMessage("Total execution time: " + endTime + " ms");
                        resTextView.setText(String.format("A = %d, B = %d\nA * B = %d", resArray[0], resArray[1], number));
                        builder.show();
                    }
                } catch (Exception e) {
                    resTextView.setText("Unsigned integer must be entered!");
                }
            }
        });
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private int[] calculateFerma(int number) {
        int[] result = new int[2];
        int k = 0;
        int x;
        double y;
        do {
            x = (int) (Math.sqrt(number) + k);
            y = Math.sqrt(Math.pow(x, 2) - number);
            k++;
        } while (y % 1 != 0);
        result[0] = (int) (x + y);
        result[1] = (int) (x - y);
        return result;
    }
}
