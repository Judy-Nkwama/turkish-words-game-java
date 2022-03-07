package judyndotoninkwama191307093;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 *
 * @author JudyNdotoniNkwama191307093
 */

class LogoPanosu extends JPanel{   
    public LogoPanosu(){
        super();
    }
    @Override public void paintComponent(Graphics g){       
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.white);
        g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
        try {
            Image img = ImageIO.read(new File("kelimebg.png"));
            g2D.drawImage(img, 273, -10,350,150, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class GirisPanosu extends JPanel{
    
    public GirisPanosu(Pencere AnaEkran){
        super();
        
        JPanel nothing = new JPanel();
        nothing.setPreferredSize(new Dimension(900, 140));
        nothing.setBackground(Color.white);

        JLabel baslik1 = new JLabel();
        JLabel baslik2 = new JLabel();
        JLabel baslik3 = new JLabel();
        
        baslik1.setPreferredSize(new Dimension(455, 20));
        baslik1.setFont(new Font("Arial", Font.BOLD, 15));
        baslik1.setForeground(new Color(5, 0, 100));
        baslik1.setText("                       KOÜ - Teknoloji Fakültesi - Güz 2021");
        
        baslik2.setPreferredSize(new Dimension(455, 20));
        baslik2.setFont(new Font("Arial", Font.BOLD, 15));
        baslik2.setForeground(new Color(5, 0, 100));
        baslik2.setText("                Nesne Yönelimli Programlama - Final Projesi");
        
        baslik3.setPreferredSize(new Dimension(455, 20));
        baslik3.setFont(new Font("Arial", Font.BOLD, 15));
        baslik3.setForeground(new Color(5, 0, 100));
        baslik3.setText("                          191307093 - Judy Ndotoni Nkwama");
        
        nothing.add(baslik1);
        nothing.add(baslik2);
        nothing.add(baslik3);
        
        LogoPanosu nothing2 = new LogoPanosu();
        nothing2.setPreferredSize(new Dimension(900, 140));
        nothing2.setBackground(Color.white);
        
        JPanel nothing3 = new JPanel();
        nothing3.setPreferredSize(new Dimension(900, 140));
        nothing3.setBackground(Color.white);        
        
        JPanel nothing31 = new JPanel();
        nothing31.setPreferredSize(new Dimension(200, 140));
        nothing31.setBackground(Color.white);
        JPanel nothing32 = new JPanel();
        nothing32.setPreferredSize(new Dimension(210, 140));
        nothing32.setBackground(Color.white);
        JPanel nothing33 = new JPanel();
        nothing33.setPreferredSize(new Dimension(200, 140));
        nothing33.setBackground(Color.white);
        nothing3.add(nothing31);
        nothing3.add(nothing32);
        nothing3.add(nothing33);
                  
        JTextField metniGiris = new JTextField();
        metniGiris.setPreferredSize(new Dimension(200, 30));
        metniGiris.setFont(new Font("Arial", Font.BOLD, 15));
        metniGiris.setForeground(new Color(5, 0, 100));
        
        Dugmem baslatDugme = new Dugmem("Başlat");
        baslatDugme.setPreferredSize(new Dimension(200, 30)); 
        
        JLabel oyuncuAdi = new JLabel();
        oyuncuAdi.setPreferredSize(new Dimension(90, 30));
        oyuncuAdi.setFont(new Font("Arial", Font.BOLD, 15));
        oyuncuAdi.setForeground(new Color(5, 0, 100));
        oyuncuAdi.setText("Adınızı Girin");
        
        nothing32.add(oyuncuAdi);
        nothing32.add(metniGiris);
        nothing32.add(baslatDugme);
        
        baslatDugme.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent event){
                AnaEkran.oyunPanosu.yeniKelimeIcinSifirlama();
                AnaEkran.layoutYonetici.show(AnaEkran.anaKontener, "oyunSahnesi");
                AnaEkran.oyunPanosu.kelimeGirisAnimasiyon();
                AnaEkran.oyuncuAdi = metniGiris.getText().toUpperCase().length() > 0 ? metniGiris.getText().toUpperCase() : "Bilinmeyen";
                AnaEkran.oyunPanosu.oyuncuAdi = AnaEkran.oyuncuAdi;
                AnaEkran.oyunPanosu.girisBolge.surezone.setKalanVakti(240);
                metniGiris.setText("");
                AnaEkran.oyunPanosu.girisBolge.surezone.timerStart();
                AnaEkran.oyunPanosu.repaint();
            }
        });
         
        this.add(nothing);
        this.add(nothing2);
        this.add(nothing3);
    }
    
    @Override public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}

class Nothing2 extends JPanel{
    public String oyuncu;
    public int skore;
    Nothing2(String poyuncu, int pskore){
        this.oyuncu = poyuncu;
        this.skore = pskore;
    }
    @Override public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.white);
        g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g2D.setFont(new Font("Arial", Font.BOLD, 20));
        g2D.setColor(new Color(5, 0, 100));
        
        g2D.drawString("Oyuncu : " + oyuncu , 355, 40);
        g2D.drawString("Skor : " + skore , 390, 70);
        g2D.drawString("En iyi Oyuncu : YYYY (15424)" , 300, 100); 
    }   
}

class CikisPanosu extends JPanel{
    public Pencere anaEkran;
    public Nothing2 nthg2;
    public CikisPanosu(Pencere AnaEkran){
        super();
        
        this.anaEkran = AnaEkran;
        
        JPanel nothing = new JPanel();
        nothing.setPreferredSize(new Dimension(900, 140));
        nothing.setBackground(Color.white);

        JLabel baslik = new JLabel();
        baslik.setPreferredSize(new Dimension(200, 60));
        baslik.setFont(new Font("Arial", Font.BOLD, 30));
        baslik.setForeground(new Color(5, 0, 100));
        baslik.setText("OYUN BİTTİ!");
        nothing.add(baslik);

        Nothing2 nothing2 = new Nothing2(this.anaEkran.oyuncuAdi , this.anaEkran.skore);
        this.nthg2 = nothing2;
        this.nthg2.setPreferredSize(new Dimension(900, 140));
        this.nthg2.setBackground(Color.white);
        
        JPanel nothing3 = new JPanel();
        nothing3.setPreferredSize(new Dimension(900, 140));
        nothing3.setBackground(Color.white);        
        
        JPanel nothing31 = new JPanel();
        nothing31.setPreferredSize(new Dimension(200, 140));
        nothing31.setBackground(Color.white);
        JPanel nothing32 = new JPanel();
        nothing32.setPreferredSize(new Dimension(210, 140));
        nothing32.setBackground(Color.white);
        JPanel nothing33 = new JPanel();
        nothing33.setPreferredSize(new Dimension(200, 140));
        nothing33.setBackground(Color.white);
        nothing3.add(nothing31);
        nothing3.add(nothing32);
        nothing3.add(nothing33);
                
        JTextField metniGiris = new JTextField();
        metniGiris.setPreferredSize(new Dimension(200, 30));
        metniGiris.setFont(new Font("Arial", Font.BOLD, 15));
        metniGiris.setForeground(new Color(5, 0, 100));
        
        Dugmem yenidenOyaDugme = new Dugmem("   Oyna");
        yenidenOyaDugme.setPreferredSize(new Dimension(200, 30));
        
        Dugmem cikisDugme = new Dugmem("   Çikiş");
        cikisDugme.setPreferredSize(new Dimension(200, 30));
        
        nothing32.add(yenidenOyaDugme);
        nothing32.add(cikisDugme);
        
        yenidenOyaDugme.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent event){
                AnaEkran.oyunPanosu.yeniKelimeIcinSifirlama();
                AnaEkran.oyunPanosu.level = -0.5;
                AnaEkran.layoutYonetici.show(AnaEkran.anaKontener, "oyunSahnesi");
                AnaEkran.oyunPanosu.kelimeGirisAnimasiyon();
                AnaEkran.oyunPanosu.oyuncuAdi = AnaEkran.oyuncuAdi;
                AnaEkran.oyunPanosu.girisBolge.surezone.setKalanVakti(240);
                AnaEkran.oyunPanosu.girisBolge.surezone.timerStart();
                AnaEkran.oyunPanosu.setSkore(0);
                AnaEkran.oyunPanosu.repaint();
            }
        });
        
        cikisDugme.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent event){
                AnaEkran.oyunPanosu.yeniKelimeIcinSifirlama();
                AnaEkran.oyunPanosu.level = -0.5;
                AnaEkran.oyunPanosu.setSkore(0);
                AnaEkran.layoutYonetici.show(AnaEkran.anaKontener, "hosgeldiniz");
            }
        });
        
        this.add(nothing);
        this.add(this.nthg2);
        this.add(nothing3);
    }
    
    @Override public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.white);
        g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.nthg2.skore = anaEkran.oyunPanosu.getSkore();
        this.nthg2.oyuncu = anaEkran.oyunPanosu.oyuncuAdi;
        this.nthg2.repaint();
    }
}

class Pencere extends JFrame{
    
    public CardLayout layoutYonetici;
    public JPanel anaKontener;
    public int skore = 0;
    public String oyuncuAdi = "Bilinmeyen";
    public OyunEkrani oyunPanosu;
    public GirisPanosu girisPanosu;
    public CikisPanosu cikisPanosu;

    public Pencere(){
        this.layoutYonetici = new CardLayout();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Kelime Oyunu - 191307093judyNdotoniNkwama");
        this.setSize(900,550);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        JPanel kontener = new JPanel();
        this.anaKontener = kontener;
        GirisPanosu hosgeldiniz = new GirisPanosu(this);
        this.girisPanosu = hosgeldiniz;
        OyunEkrani oyunSahnesi = new OyunEkrani(this);
        this.oyunPanosu = oyunSahnesi;
        CikisPanosu cikis = new CikisPanosu(this);
        this.cikisPanosu = cikis;
        
        this.anaKontener.setLayout(this.layoutYonetici);
        this.anaKontener.add(this.girisPanosu, "hosgeldiniz");
        this.anaKontener.add(this.oyunPanosu, "oyunSahnesi");
        this.anaKontener.add(this.cikisPanosu, "cikis");
                
        this.getContentPane().add(this.anaKontener, BorderLayout.CENTER);   
        this.setVisible(true);
    }

}

public class JudyNdotoniNkwama191307093 {
    
    public static void main(String[] args) {
        
        Pencere anaPencerem = new Pencere(); 
     
    }
    
}
