package cs654.sunil.dac;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends ActionBarActivity {

    Button div, mul, sub, del, seven, eight, nine, add, four, five, six, one, two, three, equal, xero, dot, blank;
    TextView o1, o2, opr;
    EditText et1, et2;
    int flag = 0;//to check for the edittext
    ProgressDialog pdialog;
    GridLayout gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        div = (Button) findViewById(R.id.divide);
        mul = (Button) findViewById(R.id.multiply);
        sub = (Button) findViewById(R.id.subtract);
        del = (Button) findViewById(R.id.delete);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        add = (Button) findViewById(R.id.add);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        equal = (Button) findViewById(R.id.equal);
        xero = (Button) findViewById(R.id.xero);
        dot = (Button) findViewById(R.id.dot);
        blank = (Button) findViewById(R.id.blank);


        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        opr = (TextView) findViewById(R.id.opr);

        gl = (GridLayout) findViewById(R.id.gl);

        int xh = (height * 10) / 85;
        int xw = width * 10 / 40;

        et1.setRawInputType(InputType.TYPE_CLASS_TEXT);
        et1.setTextIsSelectable(true);
        et2.setRawInputType(InputType.TYPE_CLASS_TEXT);
        et2.setTextIsSelectable(true);


        ViewGroup.LayoutParams divp = div.getLayoutParams();
        divp.height = xh;
        divp.width = xw;
        div.setLayoutParams(divp);

        ViewGroup.LayoutParams mulp = mul.getLayoutParams();
        mulp.height = xh;
        mulp.width = xw;
        mul.setLayoutParams(mulp);

        ViewGroup.LayoutParams subp = sub.getLayoutParams();
        subp.height = xh;
        subp.width = xw;
        sub.setLayoutParams(subp);

        ViewGroup.LayoutParams delp = del.getLayoutParams();
        delp.height = xh;
        delp.width = xw;
        del.setLayoutParams(delp);

        ViewGroup.LayoutParams sevenp = seven.getLayoutParams();
        sevenp.height = xh;
        sevenp.width = xw;
        seven.setLayoutParams(sevenp);

        ViewGroup.LayoutParams eightp = eight.getLayoutParams();
        eightp.height = xh;
        eightp.width = xw;
        eight.setLayoutParams(eightp);

        ViewGroup.LayoutParams ninep = nine.getLayoutParams();
        ninep.height = xh;
        ninep.width = xw;
        nine.setLayoutParams(ninep);

        ViewGroup.LayoutParams fourp = four.getLayoutParams();
        fourp.height = xh;
        fourp.width = xw;
        four.setLayoutParams(fourp);

        ViewGroup.LayoutParams fivep = five.getLayoutParams();
        fivep.height = xh;
        fivep.width = xw;
        five.setLayoutParams(fivep);

        ViewGroup.LayoutParams sixp = six.getLayoutParams();
        sixp.height = xh;
        sixp.width = xw;
        six.setLayoutParams(sixp);

        ViewGroup.LayoutParams onep = one.getLayoutParams();
        onep.height = xh;
        onep.width = xw;
        one.setLayoutParams(onep);

        ViewGroup.LayoutParams twop = two.getLayoutParams();
        twop.height = xh;
        twop.width = xw;
        two.setLayoutParams(twop);

        ViewGroup.LayoutParams threep = three.getLayoutParams();
        threep.height = xh;
        threep.width = xw;
        three.setLayoutParams(threep);

        ViewGroup.LayoutParams xerop = xero.getLayoutParams();
        xerop.height = xh;
        xerop.width = xw;
        xero.setLayoutParams(xerop);

        ViewGroup.LayoutParams blankp = blank.getLayoutParams();
        blankp.height = xh;
        blankp.width = xw;
        blank.setLayoutParams(blankp);

        ViewGroup.LayoutParams dotp = dot.getLayoutParams();
        dotp.height = xh;
        dotp.width = xw;
        dot.setLayoutParams(dotp);


        ViewGroup.LayoutParams addp = add.getLayoutParams();
        addp.height = 2 * xh;
        addp.width = xw;
        add.setLayoutParams(addp);

        ViewGroup.LayoutParams equalp = equal.getLayoutParams();
        equalp.height = 2 * xh;
        equalp.width = xw;
        equal.setLayoutParams(equalp);


        int xhet = height / 10;
        ViewGroup.LayoutParams et1p = et1.getLayoutParams();
        et1p.height = xhet;
        et1.setLayoutParams(et1p);

        ViewGroup.LayoutParams et2p = et2.getLayoutParams();
        et2p.height = xhet;
        et2.setLayoutParams(et2p);

        ViewGroup.LayoutParams oprp = opr.getLayoutParams();
        oprp.height = xhet;
        opr.setLayoutParams(oprp);

        int etw = width / 50;
        int bm = (width * 5) / 135;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(etw, 0, etw, 0);//l t r b
        et1.setLayoutParams(params);
        et2.setLayoutParams(params);
        opr.setLayoutParams(params);
        gl.setLayoutParams(params);

//        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(0, etw, 0, 0);//l t r b
//        et1.setLayoutParams(params);

//        GridLayout.Spec row1 = GridLayout.spec(0, 1);
//        GridLayout.Spec col1 = GridLayout.spec(0, 1);
//        GridLayout.LayoutParams param2 = new GridLayout.LayoutParams(row1,col1 );
//        param2.setMargins(0, 0, bm, 0);//l t r b
//        div.setLayoutParams(param2);
//        mul.setLayoutParams(param2);
//        sub.setLayoutParams(param2);


        //button functionality

        et1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    flag = 0;
//                    Selection.setSelection((Editable) et1.getText(), et2.getSelectionStart());
                    et1.requestFocus();
                    // Do what you want
                    return true;
                }
                return false;
            }
        });

        et2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    flag = 1;
//                    Selection.setSelection((Editable) et2.getText(), et1.getSelectionStart());
                    et2.requestFocus();
                    // Do what you want
                    return true;
                }
                return false;
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opr.setText("/");
                flag = 1;
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opr.setText("X");
                flag = 1;
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opr.setText("-");
                flag = 1;
            }
        });

        sub.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(flag==0){et1.append("-");}
                else et2.append("-");
                return true;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opr.setText("+");
                flag = 1;
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText("");
                et2.setText("");
                opr.setText("");
                flag = 0;
                Selection.setSelection((Editable) et1.getText(), et2.getSelectionStart());
                et1.requestFocus();
            }
        });

//        et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                flag = 0;
//            }
//        });
//
//        et2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                flag = 1;
//            }
//        });


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    setValue(1, et1);
                } else {
                    setValue(1, et2);
                }

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    setValue(2, et1);
                } else {
                    setValue(2, et2);
                }

            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    setValue(3, et1);
                } else {
                    setValue(3, et2);
                }

            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    setValue(4, et1);
                } else {
                    setValue(4, et2);
                }

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    setValue(5, et1);
                } else {
                    setValue(5, et2);
                }

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    setValue(6, et1);
                } else {
                    setValue(6, et2);
                }

            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    setValue(7, et1);
                } else {
                    setValue(7, et2);
                }

            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    setValue(8, et1);
                } else {
                    setValue(8, et2);
                }

            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    setValue(9, et1);
                } else {
                    setValue(9, et2);
                }

            }
        });
        xero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    setValue(0, et1);
                } else {
                    setValue(0, et2);
                }

            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    if (et1.getText().toString().contains(".")) {
                    } else {
                        et1.append(".");

                    }
                } else {
                    if (et2.getText().toString().contains(".")) {
                    } else {
                        et2.append(".");

                    }
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    double f1 = Double.parseDouble(et1.getText().toString());
//                    double f2 = Double.parseDouble(et2.getText().toString());
//                    String s1=new Double(f1).toString();
//                    String s2=new Double(f2).toString();
                    pdialog = ProgressDialog.show(MainActivity.this, "", "Calculating");
                    String s1 = et1.getText().toString();
                    String s2 = et2.getText().toString();
                    String s3 = URLEncoder.encode(opr.getText().toString(), "UTF-8");

                    if (s1.equals("") || s2.equals("") || s3.equals("")) {
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("Please provide proper input");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        pdialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                        return;
                    }

                    if (opr.getText().toString().equals("/")) {
                        s3 = URLEncoder.encode("#", "UTF-8");
                    }
//                    String s3= URLEncoder.encode("172.20.176.195/cs654/main.php/calc/" + s1 + "/+/" + s2, "UTF-8");
                    String s4 = "http://172.20.176.195/cs654/main.php/calc/" + s1 + "/" + s3 + "/" + s2;
                    Calc cal = new Calc();
                    cal.execute(s4);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private class Calc extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            BufferedReader reader = null;
            StringBuilder sb = new StringBuilder();
            for (String url1 : urls) {
                try {
                    URL url = new URL(url1);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    sb = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    pdialog.dismiss();
                }
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            try {
                JSONObject resp = new JSONObject(result);
                String status = resp.getString("status");
                String res = resp.getString("result");
                if(status.equals("ok")){
                    et1.setText(res.trim());
                    opr.setText("");
                    et2.setText("");
                }
                else if(status.equals("nok")){
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(res);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    pdialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(res);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    pdialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Please provide input within bounds");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                pdialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
//            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//            alertDialog.setTitle("Error");
//            alertDialog.setMessage("Please provide proper input");
//            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                            pdialog.dismiss();
//                        }
//                    });
//            alertDialog.show();
        }
    }

    public void setValue(int val, EditText et1) {
        if (et1 == et2) {
            Selection.setSelection((Editable) et2.getText(), et1.getSelectionStart());
            et2.requestFocus();
        }
        String s = et1.getText().toString();
        int x = et1.getSelectionStart();
        if (x == (s.length())) {
            et1.append("" + val);
        } else {
            s = s.substring(0, x) + val + s.substring(x, s.length());
            et1.setText("");
            et1.append("" + s);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
