package dev.fixfis.ecolim.server;

import android.widget.EditText;

public class EmptiesCalculator {
    public static String getValue(EditText t, boolean isEmpty){
        String s = t.getText().toString();
        if (s.isEmpty()) {
            isEmpty=true;
            t.setError("Nesesitas Llenar este valor");
            t.requestFocus();
            return "1";
        }
        return s;
    }
}
