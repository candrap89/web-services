package tool;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Utility {

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String paydate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);

    }

    public String getDateMonthYearOnly() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getDateMonthYearDiPencetakanSIJ() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getDateTimeNama() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getDateTimeModif() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getYearOnly() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getMonthOnly() {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getDayOnly() {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getNewNomor(String lastNumber) {
        String newNomor = "";
        String string[] = lastNumber.trim().split("/");
        String newNumber = String.valueOf(Integer.valueOf(string[2]) + 1);
        switch (newNumber.length()) {
            case 1:
                newNumber = "00" + newNumber;
                break;
            case 2:
                newNumber = "0" + newNumber;
                break;
        }
        for (int i = 0; i < string.length - 1; i++) {
            newNomor = newNomor + string[i] + "/";
        }
        newNomor = newNomor + newNumber;
        return newNomor;
    }

    public double getDecimalFormatDouble(double d) {
        double temp = 0;
        DecimalFormat decimalFormat = new DecimalFormat("0");// just digit no
        // comma
        temp = Double.valueOf(decimalFormat.format(d));
        return temp;
    }

    public long getDecimalFormatLong(double d) {
        long temp = 0;
        DecimalFormat decimalFormat = new DecimalFormat("0");// just digit no
        // comma
        temp = Long.valueOf(decimalFormat.format(d));
        return temp;
    }

    public String bill_period(String period) {
        String month = period.substring(4);
        //  System.out.println(month);
        String year = period.substring(0, 4);
        // System.out.println(year);
        if (month.equals("01")) {
            month = "JAN";
        }
        if (month.equals("02")) {
            month = "FEB";
        }
        if (month.equals("03")) {
            month = "MAR";
        }
        if (month.equals("04")) {
            month = "APR";
        }
        if (month.equals("05")) {
            month = "MAY";
        }
        if (month.equals("06")) {
            month = "JUN";
        }
        if (month.equals("07")) {
            month = "JUL";
        }
        if (month.equals("08")) {
            month = "AUG";
        }
        if (month.equals("09")) {
            month = "SEP";
        }
        if (month.equals("10")) {
            month = "OCT";
        }
        if (month.equals("11")) {
            month = "NOV";
        }
        if (month.equals("12")) {
            month = "DEC";
        }



        return month + year;
    }

    public int jumlah(String str1, String str2) {
        return Integer.valueOf(str1) + Integer.valueOf(str2);
    }

    public String bill_periodrevers(String period) {
        String month = period.substring(0, 3);
        //  System.out.println(month);
        String year = period.substring(3);
        // System.out.println(year);
        if (month.equals("JAN")) {
            month = "01";
        }
        if (month.equals("FEB")) {
            month = "02";
        }
        if (month.equals("MAR")) {
            month = "03";
        }
        if (month.equals("APR")) {
            month = "04";
        }
        if (month.equals("MAY")) {
            month = "05";
        }
        if (month.equals("JUN")) {
            month = "06";
        }
        if (month.equals("JUL")) {
            month = "07";
        }
        if (month.equals("AUG")) {
            month = "08";
        }
        if (month.equals("SEP")) {
            month = "09";
        }
        if (month.equals("OCT")) {
            month = "10";
        }
        if (month.equals("NOV")) {
            month = "11";
        }
        if (month.equals("DEC")) {
            month = "12";
        }



        return year + month;
    }

    public String bill_periodtodb(String bill_period) {
        String period = "";
        String[] periodewhitout = bill_period.split("-");
        if (periodewhitout.length > 1) {
            period = bill_periodrevers(periodewhitout[0]);
        } else {
            period = bill_periodrevers(bill_period);
        }


        return period;
    }
}
