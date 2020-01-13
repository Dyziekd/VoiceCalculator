package com.example.voicecalculator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voicecalculator.History.HistoryActivity;
import com.example.voicecalculator.History.HistoryDatabaseHelper;

import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private TextView expressionField, auxiliaryField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initResources();
    }

    // inflate toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // add listeners to toolbar buttons
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // start recording
            case R.id.toolbar__start_recording_button:
                {
                startRecording();
                return true;
            }

            // open history activity
            case R.id.toolbar__show_history_button: {
                Intent startHistoryActivity = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(startHistoryActivity);
                return true;
            }

            // open settings activity
            case R.id.toolbar__show_settings_button: {
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // get voice recording result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case 10:
                if (resultCode == RESULT_OK && data != null)
                {
                    // get results
                    ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String result = ExpressionParser.parse(results.get(0));
                    expressionField.setText(result);
                    calculateExpression(null);
                }
                break;
        }
    }

    // start voice recording
    private void startRecording()
    {
        // create voice recognize intent
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Enter the expression by voice...");

        // start recording or show proper message
        if(intent.resolveActivity(getPackageManager())!=null)
            startActivityForResult(intent, 10);
        else
            Toast.makeText(getApplicationContext(), "Your device does not support microphone", Toast.LENGTH_LONG).show();
    }

    // init resources
    private void initResources()
    {
        expressionField = findViewById(R.id.expression_field);
        auxiliaryField = findViewById(R.id.auxiliary_field);
    }

    // add expression and result to the history database
    private void addCalculationsToHistory(String expression, String result)
    {
        // connect to database
        HistoryDatabaseHelper dbHelper = new HistoryDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // prepare values to insert
        ContentValues values = new ContentValues();
        values.put(HistoryDatabaseHelper.EXPRESSION_FIELD_NAME, expression);
        values.put(HistoryDatabaseHelper.RESULT_FIELD_NAME, result);

        // insert content to database
        long id = db.insert(HistoryDatabaseHelper.HISTORY_TABLE_NAME, null, values);
    }




    // --------------------------------------------------------- CALCULATOR BUTTONS -------------------------------------------------------
    // add "0" to the expression
    public void append0(View view)
    {
        if(!expressionField.getText().toString().equals("0"))
            expressionField.append("0");
    }

    // add "1" to the expression
    public void append1(View view)
    {
        appendChar('1');
    }

    // add "2" to the expression
    public void append2(View view)
    {
        appendChar('2');
    }

    // add "3" to the expression
    public void append3(View view)
    {
        appendChar('3');
    }

    // add "4" to the expression
    public void append4(View view)
    {
        appendChar('4');
    }

    // add "5" to the expression
    public void append5(View view)
    {
        appendChar('5');
    }

    // add "6" to the expression
    public void append6(View view)
    {
        appendChar('6');
    }

    // add "7" to the expression
    public void append7(View view)
    {
        appendChar('7');
    }

    // add "8" to the expression
    public void append8(View view)
    {
        appendChar('8');
    }

    // add "9" to the expression
    public void append9(View view)
    {
        appendChar('9');
    }

    // add "." to the expression
    public void appendDot(View view)
    {
        appendSymbol('.');
    }

    // add "^" to the expression
    public void appendCaret(View view)
    {
        appendSymbol('^');
    }

    // add "+" to the expression
    public void appendPlus(View view)
    {
        appendSymbol('+');
    }

    // add "-" to the expression
    public void appendMinus(View view)
    {
        appendSymbol('-');
    }

    // add "×" to the expression
    public void appendMultiplication(View view)
    {
        appendSymbol('×');
    }

    // add "÷" to the expression
    public void appendDivison(View view)
    {
        appendSymbol('÷');
    }

    // add "(" to the expression
    public void appendOpenningBracket(View view)
    {
        char lastChar = expressionField.getText().toString().charAt(expressionField.length() - 1);

        if(lastChar != '.')
            appendChar('(');
    }

    // add ")" to the expression
    public void appendClosingBracket(View view)
    {
        char lastChar = expressionField.getText().toString().charAt(expressionField.length() - 1);

        if(lastChar != '.' && lastChar != '(' && expressionField.getText().toString().contains("("))
            appendChar(')');
    }

    // add passed char to the expression
    private void appendChar(char c)
    {
        if(expressionField.getText().toString().equals("0"))
            expressionField.setText(String.valueOf(c));
        else
            expressionField.append(String.valueOf(c));
    }

    // add passed symbol to the expression (append new symbol only if last char is not: +, -, *, ÷, ^, ( or .)
    private void appendSymbol(char c)
    {
        char lastChar = expressionField.getText().toString().charAt(expressionField.length() - 1);

        if(lastChar != '+' && lastChar != '-' && lastChar != '×' && lastChar != '÷' && lastChar != '^' && lastChar != '(' && lastChar != '.')
            expressionField.append(String.valueOf(c));
    }

    // delete last char from expression
    public void backspace(View view)
    {
        String currentExpression = expressionField.getText().toString();

        if(currentExpression.length() == 1)
            expressionField.setText("0");
        else
        {
            String newExpression = currentExpression.substring(0, currentExpression.length() - 1);
            expressionField.setText(newExpression);
        }
    }

    // clear expression
    public void clearExpression(View view)
    {
        expressionField.setText("0");
        auxiliaryField.setText("");
    }

    // calculates entered expression
    public void calculateExpression(View view)
    {
        try
        {
            // get expression
            String expression = expressionField.getText().toString();

            // replace multiplication and division symbols for compatibility with exp4j library
            String fixedExpression = expression.replaceAll("×|x", "*").replaceAll("÷", "/");

            // calculate expression
            ExpressionBuilder expressionBuilder = new ExpressionBuilder(fixedExpression);
            Double res = expressionBuilder.build().evaluate();
            String result = new DecimalFormat("0.#").format(res);

            // set expression and result field
            auxiliaryField.setText(expression + "=");
            expressionField.setText(new DecimalFormat("0.#").format(res));

            // save calculations to the history
            addCalculationsToHistory(expression, result);
        }
        catch(ArithmeticException ex)
        {
            auxiliaryField.setText("Do not divide by 0!");
        }
        catch(Exception ex)
        {
            auxiliaryField.setText("Invalid expression!");
        }
    }

}


