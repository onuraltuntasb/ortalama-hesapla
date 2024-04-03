package com.example.ortalamahesapla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.yeniderslayout.view.*

class MainActivity : AppCompatActivity() {

    private val DERSLER= arrayOf("Matematik","Türkçe","Fizik","Edebiyat","Algoritma","Tarih")

    private var tumDerslerinBilgileri:ArrayList<Dersler> = ArrayList(5)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       var adapter=ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,DERSLER)

        eTxtDersAd.setAdapter(adapter)

        if(rootLayout.childCount==0){
            btnHesapla.visibility=View.INVISIBLE
        }
        else btnHesapla.visibility=View.VISIBLE

        btnDersEkle.setOnClickListener {

            if(!eTxtDersAd.text.isNullOrEmpty()){
                var inflater=LayoutInflater.from(this)

                var yeniDersView=inflater.inflate(R.layout.yeniderslayout,null)

                yeniDersView.eTxtYeniDersAd.setAdapter(adapter)

                var dersadi=eTxtDersAd.text.toString()
                var dersKredi=spnDersKredi.selectedItem.toString()
                var dersHarf=spnDersNot.selectedItem.toString()

                yeniDersView.eTxtYeniDersAd.setText(dersadi)
                yeniDersView.spnYeniDersKredi.setSelection(spinnerDegerIndexFind(spnDersKredi,dersKredi))
                yeniDersView.spnYeniDersNot.setSelection(spinnerDegerIndexFind(spnDersNot,dersHarf))

                if(rootLayout.childCount==0){
                    btnHesapla.visibility=View.INVISIBLE
                }
                else btnHesapla.visibility=View.VISIBLE

                yeniDersView.btnDersSil.setOnClickListener {
                    rootLayout.removeView(yeniDersView)

                    if(rootLayout.childCount==0){
                        btnHesapla.visibility=View.INVISIBLE
                    }
                    else btnHesapla.visibility=View.VISIBLE
                }


                rootLayout.addView(yeniDersView)



            }else{
                Toast.makeText(this,"Ders Adını Giriniz", Toast.LENGTH_LONG).show()
            }


        }

    }



    fun spinnerDegerIndexFind(spinner: Spinner, aranacakDeger:String) :Int{

        var index=0
        for (i in 0..spinner.count){
            if(spinner.getItemAtPosition(i).toString().equals(aranacakDeger)){
                index= i
                break
            }
        }
        return index
    }



    fun ortalamaHesapla(view: View){

        var toplamNot:Double=0.0
        var toplamKredi:Double=0.0



        for (i in 0..rootLayout.childCount -1){

            var singleline= rootLayout.getChildAt(i)
            var geciciDers=Dersler( singleline.eTxtYeniDersAd.text.toString(),((singleline.spnYeniDersKredi.selectedItemPosition)+1).toString(),singleline.spnYeniDersNot.selectedItem.toString())

            tumDerslerinBilgileri.add(geciciDers)

        }

        for (oankiDers in tumDerslerinBilgileri){

            toplamNot+= harfiNotaCevir(oankiDers.dersHarfNot)*(oankiDers.dersKredi.toDouble())
            toplamKredi+= oankiDers.dersKredi.toDouble()

        }
        Toast.makeText(this,"ORTALAMA : "+(toplamNot/toplamKredi),Toast.LENGTH_LONG).show()
        tumDerslerinBilgileri.clear()

    }



    fun harfiNotaCevir(gelenNotHarfDegeri:String):Double{

        var deger=0.0

        when(gelenNotHarfDegeri){
            "AA"->deger=4.0
            "BA"->deger=3.5
            "BC"->deger=2.5
            "CC"->deger=2.0
            "DC"->deger=1.5
            "DD"->deger=1.0
            "FF"->deger=0.0

        }
        return deger

    }
}
