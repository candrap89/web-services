/*
 * To change this template, choose Tools / Templates
 * and open the template in the editor.
 */
package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import tool.KoneksiORACLE;
import tool.Utility;

/**
 *
 * @author hp
 */
@WebService()
public class webservice {

    public String inquiryresponsecode = "00";
    public String inquiryamount = "0";
    public String bayarresponsecode = "00";
    public String bayaryamount = "0";
    public String reversalresponsecode = "00";
    public String reversalamount = "0";

    /**
     * Web service operation
     */
    @WebMethod(operationName = "inquiry")
    public String inquiry(@WebParam(name = "i") String policenum) {
        KoneksiORACLE ko = new KoneksiORACLE();
        String nama = "";
        String Acct_id = "";
        List<String> jumlah = new ArrayList<String>();
        List<String> penalty = new ArrayList<String>();
        List<String> bill_period = new ArrayList<String>();
        // String hasilakhir = "";
        int total = 0;
        int totalpinalty = 0;
        int totalamount = 0;
        int totalbulanbelumbayar = 0;
        try {
            System.out.println(policenum);
            //  ko.getStatement().executeQuery("begin");
            ResultSet r = ko.getStatement().executeQuery("select count(*) as count from bill_dwl_stg where acct_id = '" + policenum.trim() + "' and reff_no is null");
            if (r.next()) {
                r.beforeFirst();
                while (r.next()) {
                    totalbulanbelumbayar = Integer.valueOf(r.getString(1));
                }
            }
            r.close();
            if (totalbulanbelumbayar <= 4 & totalbulanbelumbayar != 1) {
                ResultSet rs = ko.getStatement().executeQuery("select * from bill_dwl_stg where acct_id = '" + policenum.trim() + "' and reff_no is null order by bill_period desc");
                if (rs.next()) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        nama = rs.getString(2);
                        Acct_id = rs.getString(1);
                        bill_period.add(rs.getString(3));

                        jumlah.add(rs.getString(4));
                        penalty.add(rs.getString(5));

                        total = total + Integer.parseInt(rs.getString(4));
                        totalpinalty = totalpinalty + Integer.parseInt(rs.getString(5));
                        totalamount = totalamount + Integer.parseInt(rs.getString(4)) + Integer.parseInt(rs.getString(5));


                    }
                    inquiryamount = String.valueOf(totalamount);
                    rs.close();
                    Utility u = new Utility();
                    inquiryresponsecode = "00";
                    //  return Acct_id + "/" + nama + "/" + String.valueOf(total) + "/" + String.valueOf(totalpinalty) + "/" + jumlah.get(jumlah.size()-1) + "/" + penalty.get(penalty.size()-1) + "/" + u.bill_period(bill_period.get(bill_period.size() - 1)) + "-" + u.bill_period(bill_period.get(0)) + "/     /                   /                ";
                    return Acct_id + "/" + nama + "/" + String.valueOf(totalamount) + "/" + jumlah.get(jumlah.size() - 1) + "/" + u.bill_period(bill_period.get(bill_period.size() - 1)) + "-" + u.bill_period(bill_period.get(0)) + "/     /                   /                ";


                } else {
                    ResultSet rsi = ko.getStatement().executeQuery("select * from bill_dwl_stg where acct_id = '" + policenum.trim() + "'");
                    if (rsi.next()) {
                        inquiryresponsecode = "88";
                        rsi.close();
                        return "/SUDAH LUNAS";
                    }
                    inquiryresponsecode = "14";
                    rsi.close();
                    return "/TIDAK ADA TAGIHAN";
                }

            } else if (totalbulanbelumbayar == 1) {
                ResultSet rs = ko.getStatement().executeQuery("select * from bill_dwl_stg where acct_id = '" + policenum.trim() + "' and reff_no is null order by bill_period desc");
                if (rs.next()) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        nama = rs.getString(2);
                        Acct_id = rs.getString(1);
                        bill_period.add(rs.getString(3));




                        total = total + Integer.parseInt(rs.getString(4));
                        totalpinalty = totalpinalty + Integer.parseInt(rs.getString(5));
                        totalamount = totalamount + Integer.parseInt(rs.getString(4)) + Integer.parseInt(rs.getString(5));


                    }
                    inquiryamount = String.valueOf(totalamount);
                    rs.close();
                    ko.close();
                    Utility u = new Utility();
                    inquiryresponsecode = "00";
                    //     return Acct_id + "/" + nama + "/" + String.valueOf(total) + "/" + String.valueOf(totalpinalty) + "/000000000000/000000000000/" + u.bill_period(bill_period.get(0)) + "/     /                   /                ";
                    return Acct_id + "/" + nama + "/" + String.valueOf(totalamount) + "/000000000000/" + u.bill_period(bill_period.get(0)) + "/     /                   /                ";

                } else {
                    ResultSet rsi = ko.getStatement().executeQuery("select * from bill_dwl_stg where acct_id = '" + policenum.trim() + "'");
                    if (rsi.next()) {
                        inquiryresponsecode = "88";
                        rsi.close();
                        return "/SUDAH LUNAS";
                    }
                    inquiryresponsecode = "14";
                    rsi.close();
                    return "/TIDAK ADA TAGIHAN";
                }




                //  return hasilakhir.get(hasilakhir.size()-1);
            } else {
                inquiryresponsecode = "05";
                return "/HARAP BAYAR DI KANTOR CABANG AETRA";
            }
            //   ko.getStatement().executeQuery("end;");

        } catch (SQLException e) {
            inquiryresponsecode = "68";
            return "/" + e.getMessage();
        }

    }

    @WebMethod(operationName = "bayar")
    public String bayar(@WebParam(name = "acct_id") String acct_id, @WebParam(name = "nama") String nama, @WebParam(name = "tipe") String tipe, @WebParam(name = "refnum") String refnum, @WebParam(name = "totalamount") String totalamount, @WebParam(name = "lastamount") String totalakhir, @WebParam(name = "bill_period") String bill_period) {


//        System.out.println(acct_id);
//        System.out.println(tipe);

        KoneksiORACLE ko = new KoneksiORACLE();
        String hasil = "";
        try {
            if ("LUNAS".equals(tipe)) {
                // ko.getStatement().executeUpdate("insert into bill_upd_stg(acct_id , bill_period , pay_date, total_pay, reff_no, bank_id, chnl_id, bill_id, bill_sw)values ('"+acct_id+"','"+bill_period+"','"+new Utility().paydate() +"','"+total+"','"+refnum+"','002','6011','','Y') ");
                ResultSet rs = ko.getStatement().executeQuery("select * from bill_dwl_stg where acct_id = '" + acct_id.trim() + "' and (reff_no = '' or reff_no is null)");
                if (rs.next()) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        System.out.println(rs.getString(1));
                        ko.getStatement().executeUpdate("insert into bill_upd_stg(acct_id , bill_period , pay_date, total_pay, reff_no, bank_id, chnl_id, bill_id, bill_sw)values ('" + rs.getString(1) + "','" + rs.getString(3) + "','" + new Utility().paydate() + "','" + new Utility().jumlah(rs.getString(4), rs.getString(5)) + "','" + refnum + "','002','6011','" + rs.getString(9) + "','Y') ");
                        //System.out.println(rs.getString(1) );
                    }
                    ko.getStatement().executeUpdate("update bill_dwl_stg set reff_no = '" + refnum.trim() + "' where acct_id = '" + acct_id.trim() + "' and (reff_no = '' or reff_no is null) ");
//                   bayaryamount = String.valueOf(Integer.valueOf(totalamount) + Integer.valueOf(totalpenalty));
//                    hasil = acct_id + "/" + nama + "/" + totalamount + "/" + totalpenalty + "/" + totalakhir + "/" + penaltyterakhir + "/" + bill_period + "/LUNAS/" + new Utility().paydate() + "/" + refnum + "";
                    bayaryamount = String.valueOf(Integer.valueOf(totalamount));
                    hasil = acct_id + "/" + nama + "/" + totalamount + "/" + totalakhir + "/" + bill_period + "/LUNAS/" + new Utility().paydate() + "/" + refnum + "";

                    bayarresponsecode = "00";
                } else {
                    bayarresponsecode = "14";

                    hasil = "/TIDAK ADA TAGIHAN";
                }
                rs.close();
            } else {
                String bill_id = "";
//                System.out.println(new Utility().bill_periodtodb(bill_period));
//                System.out.println(acct_id);
                ResultSet r = ko.getStatement().executeQuery("select bill_id from bill_dwl_stg where acct_id = '" + acct_id.trim() + "' and bill_period = '" + new Utility().bill_periodtodb(bill_period) + "' ");
                if (r.next()) {
                    r.beforeFirst();
                    while (r.next()) {
                        bill_id = r.getString(1);
                    }

                    ko.getStatement().executeUpdate("update bill_dwl_stg set reff_no = '" + refnum.trim() + "' where acct_id = '" + acct_id.trim() + "' and bill_period = '" + new Utility().bill_periodtodb(bill_period) + "' ");
                    //        ko.getStatement().executeUpdate("insert into bill_upd_stg(acct_id , bill_period , pay_date, total_pay, reff_no, bank_id, chnl_id, bill_id, bill_sw)values ('" + acct_id.trim() + "','" + new Utility().bill_periodtodb(bill_period) + "','" + new Utility().paydate() + "','" + tot + "','" + refnum.trim() + "','002','6011','" + bill_id + "','Y') ");
                    ko.getStatement().executeUpdate("insert into bill_upd_stg(acct_id , bill_period , pay_date, total_pay, reff_no, bank_id, chnl_id, bill_id, bill_sw)values ('" + acct_id.trim() + "','" + new Utility().bill_periodtodb(bill_period) + "','" + new Utility().paydate() + "','" + totalakhir + "','" + refnum.trim() + "','002','6011','" + bill_id + "','Y') ");

                    //bayaryamount = String.valueOf(Integer.valueOf(totalamount) + Integer.valueOf(totalpenalty));
//                    hasil = acct_id + "/" + nama + "/" + totalamount + "/" + totalpenalty + "/" + totalakhir + "/" + penaltyterakhir + "/" + bill_period + "/AWAL/" + new Utility().paydate() + "/" + refnum + "";
//                    bayaryamount = String.valueOf(Integer.valueOf(totalamount) + Integer.valueOf(totalpenalty));
//                    hasil = acct_id + "/" + nama + "/" + totalamount + "/" + totalpenalty + "/" + totalakhir + "/" + penaltyterakhir + "/" + bill_period + "/AWAL/" + new Utility().paydate() + "/" + refnum + "";

                    bayaryamount = String.valueOf(Integer.valueOf(totalamount));
                    hasil = acct_id + "/" + nama + "/" + totalamount + "/" + totalakhir + "/" + bill_period + "/AWAL/" + new Utility().paydate() + "/" + refnum + "";

                    bayarresponsecode = "00";

                } else {
                    bayarresponsecode = "92";
                    hasil = "/TIDAK ADA TAGIHAN AWAL";

                }
            }


            ko.close();


        } catch (SQLException e) {
            bayarresponsecode = "91";
            return "/" + e.getMessage();
        }



        return hasil;

    }

    @WebMethod(operationName = "getinquiryResponCode")
    public String getinquiryResponcode() {
        return inquiryresponsecode;
    }

    @WebMethod(operationName = "getinquiryAmmount")
    public String getinquiryAmmount() {
        return inquiryamount;
    }

    @WebMethod(operationName = "getbayarResponCode")
    public String getbayarResponcode() {
        return bayarresponsecode;
    }

    @WebMethod(operationName = "getbayarAmmount")
    public String getbayarAmmount() {
        return bayaryamount;
    }

    @WebMethod(operationName = "getreversalResponCode")
    public String getreversalResponcode() {
        return reversalresponsecode;
    }

    @WebMethod(operationName = "getreversalAmmount")
    public String getreversalAmmount() {
        return reversalamount;
    }

    @WebMethod(operationName = "reversalbayar")
    public String reversalbayar(@WebParam(name = "acct_id") String acct_id, @WebParam(name = "nama") String nama, @WebParam(name = "tipe") String tipe, @WebParam(name = "refnum") String refnum, @WebParam(name = "totalamount") String totalamount, @WebParam(name = "lastamount") String totalakhir, @WebParam(name = "bill_period") String bill_period) {
        String hasil = "";

        try {
            KoneksiORACLE ko = new KoneksiORACLE();
            if ("LUNAS".equals(tipe)) {
                // ko.getStatement().executeUpdate("insert into bill_upd_stg(acct_id , bill_period , pay_date, total_pay, reff_no, bank_id, chnl_id, bill_id, bill_sw)values ('"+acct_id+"','"+bill_period+"','"+new Utility().paydate() +"','"+total+"','"+refnum+"','002','6011','','Y') ");
                //ResultSet rs = ko.getStatement().executeQuery("select * from bill_dwl_stg where acct_id = '" + acct_id + "' and reff_no = '"+refnum+"'");
                // if (rs.next()) {
                //   rs.beforeFirst();
                //    while (rs.next()) {
                //       System.out.println(rs.getString(1));
                ko.getStatement().executeUpdate("Delete from bill_upd_stg where acct_id = '" + acct_id.trim() + "' and reff_no = '" + refnum.trim() + "'");
                //System.out.println(rs.getString(1) );
                //   }
                ko.getStatement().executeUpdate("update bill_dwl_stg set reff_no = '' where acct_id = '" + acct_id.trim() + "' and reff_no = '" + refnum.trim() + "'  ");
//                reversalamount = String.valueOf(Integer.valueOf(totalamount) + Integer.valueOf(totalpenalty));
//                hasil = acct_id + "/" + nama + "/" + totalamount + "/" + totalpenalty + "/" + totalakhir + "/" + penaltyterakhir + "/" + bill_period + "/LUNAS/" + new Utility().paydate() + "/" + refnum + "";
                reversalamount = String.valueOf(Integer.valueOf(totalamount));
                hasil = acct_id + "/" + nama + "/" + totalamount + "/" + totalakhir + "/" + bill_period + "/LUNAS/" + new Utility().paydate() + "/" + refnum + "";


                // } else {
                //      responsecode = "14";
                reversalresponsecode = "00";
                //      hasil = "/data not found";
                //  }
                //  rs.close();
            } else {
                String bill_id = "";
//                System.out.println(new Utility().bill_periodtodb(bill_period));
//                System.out.println(acct_id);
                // ResultSet r = ko.getStatement().executeQuery("select bill_id from bill_dwl_stg where acct_id = '" + acct_id + "' and bill_period = '" + new Utility().bill_periodtodb(bill_period) + "' ");
                // if (r.next()) {
                //     r.beforeFirst();
                //     while (r.next()) {
                //          bill_id = r.getString(1);
                //      }
                try{
                ko.getStatement().executeUpdate("update bill_dwl_stg set reff_no = '' where acct_id = '" + acct_id.trim() + "' and bill_period = '" + new Utility().bill_periodtodb(bill_period) + "' ");
                ko.getStatement().executeUpdate("delete from bill_upd_stg where acct_id = '" + acct_id.trim() + "' and bill_period = '" + new Utility().bill_periodtodb(bill_period) + "' ");
//                reversalamount = String.valueOf(Integer.valueOf(totalamount) + Integer.valueOf(totalpenalty));
//                hasil = acct_id + "/" + nama + "/" + totalamount + "/" + totalpenalty + "/" + totalakhir + "/" + penaltyterakhir + "/" + bill_period + "/AWAL/" + new Utility().paydate() + "/" + refnum + "";
                
                
                }catch(SQLException e){
                 reversalresponsecode = "92";
                }
                reversalamount = String.valueOf(Integer.valueOf(totalamount));
                hasil = acct_id + "/" + nama + "/" + totalamount + "/"  + totalakhir + "/"  + bill_period + "/AWAL/" + new Utility().paydate() + "/" + refnum + "";


                //  } else {
                //     responsecode = "14";

                //     hasil = "/data not found";
                reversalresponsecode = "00";
                //    }
            }


            ko.close();


        } catch (SQLException e) {
            reversalresponsecode = "91";
            return "/" + e.getMessage();
        }



        return hasil;
    }
}
