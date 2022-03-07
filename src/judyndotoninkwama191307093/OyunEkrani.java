package judyndotoninkwama191307093;
//Aşağıda eklediğim pakette kelimeler ile ilgili bütüm işlerler yaptım burada hazın halde ekledim 
import paketlerim.KelimeListesi;

import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author 191307093judyNdotoniNkwama
 */

public class OyunEkrani extends JPanel{
    
    private final char[] inciHarflare = {'I','İ'}; 
    private final char[] ortaHarflare = {'F','R','N','H','U','E','A','Ü','K','L','Y','Ş','J','V','S'};
    //private final char[] kalinHarflare = {'G','Ğ','O','D','Q','P','W','T','M','X','Ö','C','Ç','Z','B'};
    private final ArrayList inciHarflare2 = new ArrayList();
    private final ArrayList ortaHarflare2 = new ArrayList();
    
    private final int defaultMarginX = 253;
    private final int defaultMarginY = 300;
    
    private int marX = -750;//Animesiyon için
    private int sunucuX = 900;
    public int baskaAnlamSure = 20;
    
    private String sunucuMessage = "";
    
    //Bir kelime ile ilgili değikenler
    public static Graphics2D gOut;
    private ArrayList verilmisHarflar = new ArrayList();//harfIste metodu için
    public KelimeListesi[] tumKelimeler = KelimeListesi.values();
    private ArrayList istenilenHarfChar = new ArrayList();
    private ArrayList istenilenHarfIndex = new ArrayList();
    private String sorulmusKelimeler = "";
    private KelimeListesi ActifKelime;
    private int gosterilmisAnlamSayisi = 1;
    public int actifAnlam = 0;
    Pencere ae;
    
    public void setIstenilenHarfChar(ArrayList istenilenHarfChar) {
        this.istenilenHarfChar = istenilenHarfChar;
    }
    public ArrayList getVerilmisHarflar() {
        return verilmisHarflar;
    }
    public ArrayList getIstenilenHarfChar() {
        return istenilenHarfChar;
    }
    public ArrayList getIstenilenHarfIndex() {
        return istenilenHarfIndex;
    }
    public KelimeListesi getActifKelime() {
        return ActifKelime;
    }
    public int getGosterilmisAnlamSayisi() {
        return gosterilmisAnlamSayisi;
    }
    public void setIstenilenHarfIndex(ArrayList istenilenHarfIndex) {
        this.istenilenHarfIndex = istenilenHarfIndex;
    }
    public void setActifKelime(KelimeListesi ActifKelime) {
        this.ActifKelime = ActifKelime;
    }
    public void setGosterilmisAnlamSayisi(int gosterilmisAnlamSayisi) {
        this.gosterilmisAnlamSayisi = gosterilmisAnlamSayisi;
    }
    
    //Bir Oyun Roundü ile igili değişkenler
    public String oyuncuAdi;
    private int skore = 0;
    private int harfIstemeHakiSayisi = 12;// Oyuna göre varsayilan olara 11 harf istenebilir
    
    public void setSkore(int skore) {
        this.skore = skore;
    }
    public void setHarfIstemeHakiSayisi(int harfIstemeHakiSayisi) {
        this.harfIstemeHakiSayisi = harfIstemeHakiSayisi;
    }
    public int getSkore() {
        return skore;
    }
    public int getHarfIstemeHakiSayisi() {
        return harfIstemeHakiSayisi;
    }
    
    public GirisBolge girisBolge;
    //others
    Color customColor = new Color(5, 0, 100);
    Color customColor2 = new Color(11, 95, 197);
    GradientPaint gp = new GradientPaint(0, 0, customColor, 0, 20, customColor2, true);
    
    public boolean isInAnimation = false;
    public boolean isSunucuInAnimation = false;
    
    public double level = -0.5;
    //Yapıcı metot--------------------------------
    
    public OyunEkrani(Pencere AnaEkran){
        super();
        this.level = -0.5;
        this.oyuncuAdi = AnaEkran.oyuncuAdi;
        this.setSkore(AnaEkran.skore);
        this.ae = AnaEkran;
        
        for(int i=0; i < inciHarflare.length ; i++){
            inciHarflare2.add(inciHarflare[i]);
        }
        for(int i=0; i < ortaHarflare.length ; i++){
            ortaHarflare2.add(ortaHarflare[i]);
        }
        
        GirisBolge oyunKontrol = new GirisBolge(this, AnaEkran);
        this.girisBolge = oyunKontrol;
        this.add(this.girisBolge);
    }
     
    //-----------------------------------------------------------------------
    
    //OYUN BİTİŞİ
    public void oyunBitisi(Pencere AnaEkran){
        AnaEkran.cikisPanosu.anaEkran = AnaEkran;
        AnaEkran.cikisPanosu.repaint();
        //this.ActifKelime = null;
        this.setIstenilenHarfChar(new ArrayList());
        this.setIstenilenHarfIndex(new ArrayList());
        this.sorulmusKelimeler = "";
        this.setHarfIstemeHakiSayisi(12);
    }
    
    public void arkaPlanBoya(Graphics2D g2D){
        if(g2D != null){
            Color customColor = new Color(255, 255, 255);
            g2D.setColor(customColor);
            g2D.fillRect(0,0,this.getWidth(), this.getHeight());
        }
    }
    
    private Timer animTimer = new Timer();
    private TimerTask animTimerTask = new TimerTask(){@Override public void run(){}};
    
    public void kelimeGirisAnimasiyon(){

        this.anlamlarGosterme();
        
        this.marX = -750;
        this.isInAnimation = true;
        this.animTimer.cancel();
        this.animTimer = new Timer();        
        OyunEkrani oe = this;
        this.animTimer.scheduleAtFixedRate(new TimerTask(){
            @Override public void run(){
                if(oe.marX < 0){
                    oe.marX += 5;
                    oe.repaint();
                }else{
                    oe.isInAnimation = false;
                    oe.animTimer.cancel();
                }
            }
        }, 0, 5);    
    }
    
    //Sunucu Animasiyon
    
    private Timer sunucuAnimTimer = new Timer();
    private TimerTask sunucuAnimTimerTask = new TimerTask(){@Override public void run(){}};
    
    public void sunucuAnimasiyon(String pMessage){
        this.sunucuX = 900;
        this.isSunucuInAnimation = true;
        this.sunucuAnimTimer.cancel();
        this.sunucuAnimTimer = new Timer();       
        OyunEkrani oe = this;
        oe.sunucuAnimTimer.scheduleAtFixedRate(new TimerTask(){
            @Override public void run(){
                if(oe.sunucuX > 700){
                    oe.sunucuX -= 5;
                    oe.repaint();
                }else{
                    oe.sunucuMessage = pMessage;
                    oe.isSunucuInAnimation = false;
                    oe.sunucuAnimTimer.cancel();
                }
            }
        }, 0, 5);
    }
    
    public void sunucuCiksin(int pDelay){ 
        
        OyunEkrani oe = this;
        //this.sunucuAnimTimer.cancel();
        Timer tamKelimeTimer = new Timer();
        
        tamKelimeTimer.schedule(new TimerTask(){
            @Override public void run(){
                oe.sunucuX = 900;
                oe.sunucuMessage = "";
                oe.repaint();
            }
        }, pDelay);
    }
    
    private Timer aGT  = new Timer();
    
    public void anlamlarGosterme(){
        
        //---------- bu fonksiyon "this.actifAnlam"i degistirecektir -------//

        //aGT.cancel();
        /*OyunEkrani oe = this;
        this.aGT = new Timer();        
        this.aGT.scheduleAtFixedRate(new TimerTask(){
            int aa = 20;
            int showAnlam = 1;
            @Override public void run(){
                if(!(oe.getActifKelime().getAnlamlarTablosu().length > showAnlam )){
                    oe.aGT.cancel();
                }
                aa--;
                if((aa <= 0) && ( oe.getActifKelime().getAnlamlarTablosu().length > showAnlam ) ){
                    oe.sunucuAnimasiyon(oe.getActifKelime().getAnlamlarTablosu()[showAnlam]);
                    System.out.println("shown anlam : " + oe.getActifKelime().getAnlamlarTablosu()[showAnlam]);
                    oe.sunucuCiksin(15000);
                    showAnlam++;
                    aa = 20;
                }
                //oe.sunucuMessage = "";
                //oe.repaint();
            }
        }, 1000, 1000);*/
    }
    
    
    //Oyuncu bir harf istediğinde harfi gönderen metot----------------------------
    
    public void harfYaz(char c, int position, Graphics2D g2D){
        int mY = this.defaultMarginY;
        int mX = this.defaultMarginX + (position * 50) + (int)(position * 1.5);
        
        if(inciHarflare2.contains(c)){
            mX += 6;
        }else if(ortaHarflare2.contains(c)){
            mX += 3;
        }
        
        //Harflar yazma işlemi
        Font style = new Font("courier", Font.BOLD, 25);
        g2D.setColor(Color.BLACK);
        g2D.setFont(style);
        g2D.drawString(String.valueOf(c) , (int)mX , mY);
        
    }
    
    //------------------------------------------------------------------------------
    
    //Kelime kutumarında tam kelimeyi yazdiran metot-------------
    
    public void kelimeYaz(KelimeListesi kelime){
        String kelimeStr = kelime.getKelime().toUpperCase();
        int kelimeSize = kelimeStr.length();
        this.istenilenHarfChar = new ArrayList();
        this.istenilenHarfIndex = new ArrayList();       
        //Kelime yazdirma işlemi
        for(int i = 0 ; i < kelimeSize; i++){
            istenilenHarfChar.add(kelimeStr.charAt(i));
            istenilenHarfIndex.add(i);
        }
    }
    //-----------------------------------------------------
    
    //ipucu harf gönderen metot
    public void harfIste(KelimeListesi kelime){
        int indeks = kelime.harfIndilsiIste();
        
        if(!(this.verilmisHarflar.size() == kelime.getKelime().length())){
            if(!this.verilmisHarflar.contains(indeks)) {
                this.verilmisHarflar.add(indeks);
                //System.out.println(kelime.getKelime().toUpperCase().charAt(indeks));
                this.istenilenHarfChar.add(kelime.getKelime().toUpperCase().charAt(indeks));
                this.istenilenHarfIndex.add(indeks);
            } else {
                harfIste(kelime);
            }
        }
        this.repaint();
    }
    //-----------------------------------------------------

    //Yeni bir soru üreten metot yani anlam vererek kelimeyi isteyen metot
    
    public void soruUret(Graphics2D g2D){
        
        String kelimeStr = this.ActifKelime.getKelime().toUpperCase();
        int kelimeSize = kelimeStr.length();
       
        String anlam = this.ActifKelime.anlamGonder(this.actifAnlam).toUpperCase();
        int anlamSize = anlam.length();
         
        //Anlam Metni satirlara bölme işleme
        int satirSayisi = (int) anlamSize / 50;
        String duzguzYazilmisAnlam = "\"";
        String a = anlam;
        do{
            if(a.length() > 50){
                String b = a.substring(0,49);
                String c = b.substring(0, (int)b.lastIndexOf(" "));
                duzguzYazilmisAnlam += c + "\n";
                a = a.replace(c, "");
            }else{
                duzguzYazilmisAnlam += a + "\"";
                a = a.replace(a, "");
            }
        }while(a.length() > 0);
        
        //Anlam yazdırma işlemi
        Font style = new Font("courier", Font.BOLD, 15); 
        g2D.setColor(Color.white);
        g2D.setFont(style);
        
        String[] lines = duzguzYazilmisAnlam.split("\n"); 
        
        if(!isInAnimation){
            for(int i = 0; i < lines.length; i++){
                g2D.drawString(lines[i], 100, 387 + (g2D.getFontMetrics().getHeight() * i));
            }
        }
        
        //Boş Harf kutuları saklama işlemi
        //Boş Harf kutulari saklayan bölgenin varsayılan yatay offsetleri
        int saklaBolgeX = (kelimeSize > 4) ? 441 + ( 50 * (kelimeSize - 4)) + (kelimeSize - 4) : 441;
        /* Oyunda en kısa kelime 4 harli olduğu için varsayılan olarak 4 harf (441px) açık bırakıyoruz
           50px = 1 harf kutusu
        */
        g2D.setColor(Color.WHITE);
        g2D.fillRect(saklaBolgeX ,265, 310,50);
       
    }
    
    public void yeniKelimeIcinSifirlama(){
        //verilmis harflarin indiks tablosu sıfırlıyoruz önce
        
        
        this.verilmisHarflar = new ArrayList();
        
        if(this.ActifKelime != null){
            this.sorulmusKelimeler += "/" + this.ActifKelime.getKelime();
        }
               
        do{
            this.ActifKelime = this.tumKelimeler[(int)Math.ceil((Math.random() * (this.tumKelimeler.length - 1)))];  
        }while(sorulmusKelimeler.contains(ActifKelime.getKelime()) || (ActifKelime.getKelime().length() != Math.ceil(4 + this.level)));
        
        //System.out.println(ActifKelime.getKelime());
        
        this.istenilenHarfIndex = new ArrayList();
        this.istenilenHarfChar = new ArrayList();
        this.gosterilmisAnlamSayisi = 1;
        this.level += 0.5;
        this.setGosterilmisAnlamSayisi(1);
        this.baskaAnlamSure = 0;
        this.repaint();
    }
    
    //-------------------------------------------------------------------
    
    //Ekrana yazdirma methodu-------------------------
    @Override public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D) g; 
        this.gOut = g2D;
         
        arkaPlanBoya(g2D);
        
        //Arka plan soru ve animasiyonu
        try {
            Image img = ImageIO.read(new File("sorubg.png"));
            g.drawImage(img, this.marX, 230, 750, 220, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        
        for(int i = 0; i < istenilenHarfIndex.size(); i++){
            harfYaz((char)istenilenHarfChar.get(i), (int)istenilenHarfIndex.get(i), g2D);
        } 
        
        //Birinci soru otomatik olarak üretiyoruz      
        if(this.ActifKelime != null){
            soruUret(g2D);
        }
        
        //Sununcu
        try {
            Image img = ImageIO.read(new File("sunucu.png"));
            g.drawImage(img, this.sunucuX, 120, 200, 400, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //sunucu messaj metni satirlara bölme işleme
        int satirSayisi = (int) this.sunucuMessage.length() / 15;
        String duzguzYazilmisAnlam = "\"";
        String a = this.sunucuMessage;
        do{
            if(a.length() > 15){
                String b = a.substring(0,14);
                String c = b.substring(0, (int)b.lastIndexOf(" "));
                duzguzYazilmisAnlam += c + "\n";
                a = a.replace(c, "");
            }else{
                duzguzYazilmisAnlam += a + "\"";
                a = a.replace(a, "");
            }
        }while(a.length() > 0);
        

        String[] lines = duzguzYazilmisAnlam.split("\n"); 
        
        if(!isInAnimation){
            for(int i = 0; i < lines.length; i++){
                g2D.drawString(lines[i], 732, 155 + (g2D.getFontMetrics().getHeight() * i));
            }
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        //Skore yazıyoruz
        String formatedSkore = "";
        for(int i = 0 ; i< ( 5 - String.valueOf(this.getSkore()).length()); i++ ){
            formatedSkore += "0";
        }
        formatedSkore += this.getSkore();
        g2D.setFont(new Font("courier", Font.BOLD, 24));
        g2D.setColor(Color.black);  
        if(!isInAnimation){
            g2D.drawString(formatedSkore, 87, 260);
        }
        
        //kazanabilen Max
        if(this.getActifKelime() != null){
            g2D.setFont(new Font("courier", Font.BOLD, 24));
            String alinacak = String.valueOf(( 100 * this.getActifKelime().getKelime().length()) - (this.getIstenilenHarfChar().size() * 100));
            alinacak = (alinacak.equals("0")) ? alinacak + "00" : alinacak;
            if(!isInAnimation){
                g2D.drawString(alinacak , 100, 298);
            }
        }
        
        //harf isteme hakkı Sayısı
        g2D.setFont(new Font("courier", Font.BOLD, 18));
        g2D.setPaint(gp);
        String harfHakki = (this.harfIstemeHakiSayisi > 9) ? String.valueOf(this.harfIstemeHakiSayisi) : "0" + String.valueOf(this.harfIstemeHakiSayisi);
        g2D.drawString("Harf Hakkı : " + harfHakki , 40, 113);
        g2D.drawString("Oyuncu Adı : " + this.oyuncuAdi , 40, 90);        
    }
    //------------------------------------------------------------------  
}