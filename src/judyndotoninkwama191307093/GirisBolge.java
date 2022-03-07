package judyndotoninkwama191307093;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author 191307093judyNdotoniNkwama
 */
class Dugmem extends JButton {
    private String name;
    public Dugmem(String str) {
        super(str);
        this.name = str;
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Color customColor = new Color(5, 0, 100);
        Color customColor2 = new Color(11, 95, 197);
        GradientPaint gp = new GradientPaint(0, 0, customColor, 0, 20, customColor2, true);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setFont(new Font("Arial",Font.BOLD, 15 ));
        g2d.setColor(Color.white);
        g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth() / 2 / 4), (this.getHeight() / 2) + 5);
    }
}

class SureZone extends JPanel{
    private int kalanVakti = 240;
    public int getKalanVakti(){
        return kalanVakti;
    }
    public void setKalanVakti(int kalanVakti) {
        this.kalanVakti = kalanVakti;
    }
    public Timer mytimer = new Timer();
    public TimerTask mytimertask = new TimerTask(){@Override public void run(){}};
    private Pencere anaEkran; 
    public SureZone(Pencere AnaEkran){
        SureZone sz = this;
        this.anaEkran = AnaEkran;
        TimerTask MyTimerTask = new TimerTask(){
            @Override public void run(){
                if(kalanVakti > 0){
                    kalanVakti--;
                    sz.repaint();
                }
            }
        };
        this.mytimertask = MyTimerTask;
        this.mytimer.scheduleAtFixedRate(MyTimerTask, 1000, 1000);
    }
    
    public void timerStop(){
        this.mytimer.cancel();
    }
    private boolean geriSayimMesajiGonderildi = false;
      
    public void timerStart(){
        
        SureZone sz = this;
        Pencere ae = this.anaEkran;
             
        this.mytimer.cancel();
        this.mytimer = new Timer();
        this.mytimertask = new TimerTask(){
           
            @Override public void run(){

                if(kalanVakti > 0){
                    /*if((kalanVakti <= 5) && (ae.oyunPanosu.getHarfIstemeHakiSayisi() > 0) && !sz.geriSayimMesajiGonderildi){
                        sz.geriSayimMesajiGonderildi = true;
                        ae.oyunPanosu.sunucuAnimasiyon("harf isteyerek geri sayımı durdurabilirsiniz ");
                        ae.oyunPanosu.sunucuCiksin(3500);
                    }*/
                    kalanVakti--;
                    sz.repaint(); 
                }else{
                    sz.timerStop();
                    ae.oyunPanosu.sunucuAnimasiyon("Vaktınız bitti!");
                    ae.oyunPanosu.sunucuCiksin(2000);
                    Timer bitiTimir = new Timer();
                    TimerTask bittiTimerTask = new TimerTask(){
                        @Override public void run(){
                            ae.oyunPanosu.oyunBitisi(ae);
                            ae.layoutYonetici.show(sz.anaEkran.anaKontener, "cikis");
                        }
                    };
                    bitiTimir.schedule(bittiTimerTask, 1500);
                }
            }
        };
        this.mytimer.scheduleAtFixedRate(this.mytimertask, 1000, 1000);
    }
    
    @Override public void paintComponent(Graphics g){ 
        Graphics2D g2D = (Graphics2D) g;
        int kalanDakkika = (int)Math.ceil(kalanVakti / 60);
        int kalanSaniye = kalanVakti % 60;   
        String sure = (kalanSaniye>=10) ? ("0" + kalanDakkika + ":" + kalanSaniye): ("0" + kalanDakkika + ":0" + kalanSaniye);
        
        g2D.setColor(Color.white);
        g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        Color customColor = new Color(5, 0, 100);
        Color customColor2 = new Color(11, 95, 197);
        GradientPaint gp = new GradientPaint(0, 0, customColor, 0, 20, customColor2, true);
        g2D.setColor(new Color(5, 0, 100));
        g2D.setFont(new Font("courier", Font.BOLD, 22));
        g2D.setPaint(gp);
        g2D.drawString(sure, 60, 23);
    }
}

class GirisBolge extends JPanel{
    public SureZone surezone;
    public GirisBolge(OyunEkrani ekrani, Pencere AnaEkran){
        
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(900, 50));
        
        JPanel metniKabulBolge = new JPanel();
        metniKabulBolge.setPreferredSize(new Dimension(400, 50));
        metniKabulBolge.setBackground(new Color(231,230,229));
        
        JPanel harIsteBolge = new JPanel();
        harIsteBolge.setPreferredSize(new Dimension(400, 30));
        harIsteBolge.setBackground(Color.white);
        harIsteBolge.setLayout(new BorderLayout());
        
        JTextField metniGiris = new JTextField();
        metniGiris.setPreferredSize(new Dimension(200, 30));
        metniGiris.setFont(new Font("Arial", Font.BOLD, 15));
        
        metniGiris.setForeground(new Color(5, 0, 100));
        metniKabulBolge.add(metniGiris);
        
        //Sure bolgenin JPaneli
        SureZone sureBolge = new SureZone(AnaEkran);
        sureBolge.setPreferredSize(new Dimension(190, 30));
        this.surezone = sureBolge;
        
        Dugmem tamamDugme = new Dugmem("Cevapla");
        tamamDugme.setPreferredSize(new Dimension(170, 30));
        metniKabulBolge.add(tamamDugme);
        
        SureZone sz = this.surezone;
        tamamDugme.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                    
                if(metniGiris.getText().toUpperCase().replace("İ", "I").equals( ekrani.getActifKelime().getKelime().toUpperCase().replace("İ", "I"))){
                    // Skoru günceliyoruz
                    ekrani.setSkore( ekrani.getSkore() + (100 * ekrani.getActifKelime().getKelime().length()) - (ekrani.getIstenilenHarfChar().size() * 100));
                    
                    //Timer durdurulmus durumda ise başlatiyoruz
                    sz.timerStart();
                    //Kelime giriş bölgeyi boşaltiyoruz
                    metniGiris.setText("");
                    
                    //On affiche un autre mot. Mais avant, on affiche le mot trouvé
                    //On actualisi juste le istinilenHarf tablosu Sonra yeniden boyalıyoruz
                    String ak = ekrani.getActifKelime().getKelime().toUpperCase();
                    ArrayList ekHC = new ArrayList();
                    ArrayList ekHI = new ArrayList(); 
                    for(int i = 0; i < ak.length(); i++){
                        ekHC.add(ak.charAt(i));
                        ekHI.add(i);
                    }
                    ekrani.setIstenilenHarfChar(ekHC);
                    ekrani.setIstenilenHarfIndex(ekHI);
                    
                    ekrani.repaint();
                    
                    Timer tamKelimeTimer = new Timer();
                    tamKelimeTimer.schedule(new TimerTask(){
                        @Override public void run(){
                            ekrani.yeniKelimeIcinSifirlama();
                            ekrani.kelimeGirisAnimasiyon();
                        }
                    }, 1500);
                    
                    //ekrani.sunucuCiksin(1);
                   
                }else{
                    if(metniGiris.getText().length() > 0){
                        ekrani.sunucuAnimasiyon("Yanlış! İyice düşünün");
                        ekrani.sunucuCiksin(3500); 
                    }else{
                        ekrani.sunucuAnimasiyon("Bir şeyler deneyin. Boş bırakmayın");
                        ekrani.sunucuCiksin(3500);
                    }
                }
            }
        });
        
        Dugmem harfIsteDugme = new Dugmem("Harf Al");
        harfIsteDugme.setPreferredSize(new Dimension(200, 30));
        harfIsteDugme.addActionListener(new ActionListener(){
            
        public void actionPerformed(ActionEvent event){
                
                if( 
                    (sz.getKalanVakti() > 0) && (ekrani.getHarfIstemeHakiSayisi() > 0) &&
                    !(ekrani.getActifKelime().getKelime().length() <= ekrani.getIstenilenHarfChar().size())
                ){
                    ekrani.harfIste(ekrani.getActifKelime());
                    ekrani.setHarfIstemeHakiSayisi(ekrani.getHarfIstemeHakiSayisi() - 1 ); 
                       
                    /*if((sz.getKalanVakti() <= 5) && (ekrani.getHarfIstemeHakiSayisi() > 0)){
                        sz.timerStop();
                    }*/
                }else{
                    //le type viendra dire que le Candidat n'a plus le droit de demander un harf
                    if((sz.getKalanVakti() <= 0) || (ekrani.getHarfIstemeHakiSayisi() <= 0)){
                        ekrani.sunucuAnimasiyon("Harf alma hakkınız kalmadı");
                        ekrani.sunucuCiksin(3000);
                    }else if((ekrani.getActifKelime().getKelime().length() <= ekrani.getIstenilenHarfChar().size())){
                        ekrani.sunucuAnimasiyon("Bütün harflar aldınız zaten. Metin gişişina yazın, \"Cevapla\" basın");
                        ekrani.sunucuCiksin(5000);
                    }
                } 
            }
        });
       
        harIsteBolge.add(this.surezone, BorderLayout.WEST);
        harIsteBolge.add(harfIsteDugme, BorderLayout.EAST);
        
        this.add(metniKabulBolge);
        this.add(harIsteBolge);
    }
}